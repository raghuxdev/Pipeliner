package org.furin.resources.welcome;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.furin.annotations.RequiresPermission;
import org.furin.common.builders.ResponseBuilder;
import org.furin.entities.organization.Organization;
import org.furin.entities.role.Role;
import org.furin.entities.user.User;
import org.furin.services.jwt.JWTService;
import org.furin.services.organization.OrganizationService;
import org.furin.services.roles.RoleService;
import org.furin.services.user.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.furin.constants.FormConstants.*;
import static org.furin.constants.GeneralConstants.ADMIN_ASSIGNED_TO_ORGANIZATION;
import static org.furin.constants.OrganizationConstants.*;
import static org.furin.constants.PermissionConstants.CREATE;
import static org.furin.constants.RoleConstants.SUPER_ADMIN_ALREADY_EXISTS;
import static org.furin.constants.RoleConstants.SUPER_ADMIN_NOT_EXISTS;
import static org.furin.constants.UserConstants.*;

@Path("/api/admin/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class WelcomeAdmin {

    @Inject
    UserService userService;

    @Inject
    JWTService jwtService;

    ResponseBuilder responseBuilder=ResponseBuilder.getInstance();

    HashMap<String,Object> response;
    @Inject
    RoleService roleService;

    @Inject
    OrganizationService organizationService;



    @Path("/signup")
    @POST
    public Response signUp(Map<String, String> formData) {
        response =responseBuilder.createResponse();
        String email = formData.get(EMAIL);
        String password = formData.get(PASSWORD);
        String organization=formData.get(ORGANIZATION);

        Map<String, Object> response = new HashMap<>();

        // Validate credentials
        if (isInvalidCredentials(email, password)) {
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, CREDS_INVALID);
        }

        // Check if the Super Admin Role exists.
        if(!roleService.isSuperAdminRoleAlreadyExists()){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,SUPER_ADMIN_NOT_EXISTS);
        }

        Role superAdminRole= roleService.findSuperAdminRole();

        // Check if admin already exists
        if (!superAdminRole.getUsers().isEmpty()) {
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, ADMIN_ALREADY_EXISTS);
        }
        Organization organizationExists=organizationService.getOrganizationByKey(organization.toLowerCase());
        if(organizationExists!=null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, ORGANIZATION_ALREADY_EXISTS);
        }
        // Create organization
        Organization masterOrganization = organizationService.createOrganization(organization, organization.toLowerCase());
        if (masterOrganization == null) {
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, ORGANIZATION_CREATION_ERROR);
        }
        // Create admin user
        User admin = new User(email, password);
        admin.setEnabled(true);
        admin.setOrganization(masterOrganization);
        Role adminRole = roleService.findSuperAdminRole();
        admin.setRoles(new ArrayList<>());
        admin.getRoles().add(adminRole);
        User createdUser = userService.createAdminUser(admin);
        if (createdUser == null) {
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, ADMIN_CREATION_ERROR);
        }

        // Assign admin to organization
        masterOrganization.setUser(createdUser);
        if (!organizationService.assignAdmin(masterOrganization)) {
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, ADMIN_ASSIGNED_TO_ORGANIZATION);
        }

        // Generate user access token
        response.put(ORGANIZATION, masterOrganization);
        response.put(USER, admin);
        String token=jwtService.generateToken(admin);
        return responseBuilder.buildSuccessResponseWithToken(response,token);
    }

    @Path("/organization/{name}/existence")
    @GET
    public Response isGivenOrganizationExists(@PathParam("name")String organizationName){
       Organization organization= organizationService.getOrganizationByKey(organizationName.toLowerCase());
       if(organization==null){
           return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ORGANIZATION_DOESNT_EXITS);
       }
       response=responseBuilder.createResponse();
       response.put(MSG,ORGANIZATION_ALREADY_EXISTS);
       return responseBuilder.buildSuccessResponse(response);

    }


    @Path("/SuperAdmin/existence")
    @GET
    public Response isAdminAlreadyExists(){
        response =responseBuilder.createResponse();
        Role superAdminRole= roleService.findSuperAdminRole();
        if(superAdminRole.getUsers().isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,SUPER_ADMIN_NOT_EXISTS);
        }
        response=responseBuilder.createResponse();
        response.put(MSG,SUPER_ADMIN_ALREADY_EXISTS);
        return responseBuilder.buildSuccessResponse(response);
    }

    private boolean isInvalidCredentials(String email, String password) {
        return email == null || email.isEmpty() || password == null || password.isEmpty();
    }


}
