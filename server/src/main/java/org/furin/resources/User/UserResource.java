package org.furin.resources.User;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import org.furin.annotations.RequiresPermission;
import org.furin.common.Entity;
import org.furin.common.builders.ResponseBuilder;

import org.furin.entities.organization.Organization;
import org.furin.entities.permission.Permission;
import org.furin.entities.role.Role;
import org.furin.entities.user.User;
import org.furin.services.jwt.JWTService;
import org.furin.services.organization.OrganizationService;
import org.furin.services.roles.RoleService;
import org.furin.services.user.UserService;
import org.furin.utility.TokenUtility;


import java.util.*;


import static org.furin.constants.FormConstants.*;
import static org.furin.constants.FormConstants.CREDS_INVALID;
import static org.furin.constants.GeneralConstants.*;
import static org.furin.constants.OrganizationConstants.*;
import static org.furin.constants.PermissionConstants.*;
import static org.furin.constants.RoleConstants.NO_ROLES_WERE_FOUND;
import static org.furin.constants.UserConstants.*;

@Path("/api/users")
public class UserResource {

    ResponseBuilder responseBuilder=ResponseBuilder.getInstance();
    HashMap<String,Object> response=null;

    @Inject
    UserService userService;

    @Inject
    JWTService jwtService;

    @Inject
    RoleService roleService;

    @Inject
    OrganizationService organizationService;




    @RequiresPermission(action =READ,subject = USERS)
    @GET
    public Response getUsers(@Context HttpHeaders headers){
        String accessToken=TokenUtility.getTokenFromHeader(headers);
        if(accessToken==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,SESSION_EXPIRED);
        }
        JsonWebToken jwt=jwtService.parseAndVerifyToken(accessToken);
        UUID orgId=UUID.fromString(jwt.getClaim(ORGANIZATION_ID));
        if(orgId==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,SESSION_EXPIRED);
        }
        Organization organization=organizationService.getOrganizationById(orgId);
        if(organization==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,SESSION_EXPIRED);
        }
        response=responseBuilder.createResponse();
        response.put(DATA,organization.getUsers());
        return responseBuilder.buildSuccessResponse(response);
    }



    @Path("{userId}/organization")
    @GET
    public Response getOrganizationOfUser(@PathParam("userId") UUID userId){
        response=responseBuilder.createResponse();
        if(!isValidUserId(userId)){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,USER_ID_REQUIRED);
        }
        User user=userService.getUserById(userId);
        if(user==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,NO_USER_FOUND);
        }
        response.put(DATA,user.getOrganization()) ;
        return responseBuilder.buildSuccessResponse(response);
    }

    @Path("{userId}/roles")
    @GET
    public Response getRolesOfUser(@PathParam("userId")UUID userId){
        response=responseBuilder.createResponse();
        if(!isValidUserId(userId)){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, USER_ID_REQUIRED);
        }
        User user=userService.getUserById(userId);
        if(user==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,NO_USER_FOUND);
        }
        response.put(DATA,user.getRoles()) ;
        return responseBuilder.buildSuccessResponse(response);
    }

    @Path("{userId}/roles")
    @DELETE
    public Response removeRolesOfUser(@PathParam("userId")UUID userId, List<Entity> roleIds){
        response=responseBuilder.createResponse();
        if(!isValidUserId(userId)){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,USER_ID_REQUIRED);
        }
        User user=userService.getUserById(userId);
        if(user==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,NO_USER_FOUND);
        }
        List<Role> roles=roleService.getRolesUsingIds(roleIds);
        if(roles.isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,NO_ROLES_WERE_FOUND);
        }
        List<Role> deleteRoleResponse=userService.removeRoles(user,roles);
        if(deleteRoleResponse==null){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,UNEXPECTED_ERROR);
        }
        response.put(DATA,deleteRoleResponse);
        return responseBuilder.buildSuccessResponse(response);
    }


    @Path("{userId}/roles")
    @POST
    public Response assignRolesToUser(@PathParam("userId")UUID userId, List<Entity> roleIds){
        response=responseBuilder.createResponse();
        if(!isValidUserId(userId)){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,USER_ID_REQUIRED);
        }
        User user=userService.getUserById(userId);
        if(user==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,NO_USER_FOUND);
        }
        List<Role> roles=roleService.getRolesUsingIds(roleIds);
        if(roles.isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,NO_ROLES_WERE_FOUND);
        }
        List<Role> deleteRoleResponse=userService.assignRolesToUser(user,roles);
        if(deleteRoleResponse.isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,UNEXPECTED_ERROR);
        }
        response.put(DATA,deleteRoleResponse);
        return responseBuilder.buildSuccessResponse(response);
    }

    @Path("/{email}/existence")
    @GET
    public Response checkExistenceOfUser(@PathParam("email")String email){
        if(email==null){
          return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,EMAIL_REQUIRED);
        }
        User user=userService.getUserByEmail(email);
        if(user==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,USER_DOESNT_EXITS);
        }
        response=responseBuilder.createResponse();
        response.put(MSG,USER_ALREADY_EXISTS);
        return responseBuilder.buildSuccessResponse(response);

    }



    @POST
    @Path("/login")
    public Response signIn(Map<String, String> formData) {
        response =responseBuilder.createResponse();

        String email = formData.get(EMAIL);
        String password = formData.get(PASSWORD);

        // Validate credentials
        if (isInvalidCredentials(email, password)) {
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, CREDS_INVALID);
        }

        // Check if the User already exists!
        User user=userService.getUserByEmail(email);

        if(user==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,NO_USER_FOUND);
        }

        if(!password.equals(user.getPassword())){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, CREDS_INVALID);
        }
        String token=jwtService.generateToken(user);
        return responseBuilder.buildSuccessResponseWithToken(response,token);
    }

    @POST
    @Path("/signup")
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

        //check whether the organization exists or not!
        Organization organizationExists=organizationService.getOrganizationByKey(organization.toLowerCase());
        if(organizationExists!=null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, ORGANIZATION_ALREADY_EXISTS);
        }

        // Create organization
        Organization createdOrganization = organizationService.createOrganization(organization, organization.toLowerCase());
        if (createdOrganization == null) {
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, ORGANIZATION_CREATION_ERROR);
        }
        // Create admin user
        User admin = new User(email, password);
        admin.setOrganization(createdOrganization);
        Role organizationAdminRole = roleService.findRoleByKey(ORGANIZATION_ADMIN);
        admin.setRoles(new ArrayList<>());
        admin.getRoles().add(organizationAdminRole);
        User createdUser = userService.createAdminUser(admin);
        if (createdUser == null) {
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, ADMIN_CREATION_ERROR);
        }

        // Assign admin to organization
        createdOrganization.setUser(createdUser);
        if (!organizationService.assignAdmin(createdOrganization)) {
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED, ADMIN_ASSIGNED_TO_ORGANIZATION);
        }
        // Generate user access token
        response.put(ORGANIZATION, createdOrganization);
        response.put(USER, admin);


        //generate organization admin jwt token
        String token=jwtService.generateToken(admin);
        return responseBuilder.buildSuccessResponseWithToken(response,token);
    }

    @GET
    @Path("/me")
    public Response getCurrentUser(@Context HttpHeaders headers) {
    String accessToken= TokenUtility.getTokenFromHeader(headers);
      if(accessToken==null){
          return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,SESSION_EXPIRED);
      }
      JsonWebToken jwt=jwtService.parseAndVerifyToken(accessToken);

      if(jwt==null){
         return  responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,SESSION_EXPIRED);
      }

      UUID userId=UUID.fromString(jwt.getClaim(USER_ID));
      if(userId==null){
          return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,SESSION_EXPIRED);
      }
      User user=userService.getUserById(userId);
      if(user==null){
          return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,SESSION_EXPIRED);
      }
      response =responseBuilder.createResponse();
      List<Permission> permissions=roleService.getPermissionsOfRoles(user.getRoles());
      response.put(USER,user);
      response.put(PERMISSIONS,permissions);
        return responseBuilder.buildSuccessResponse(response);
    }



    public boolean isValidUserId(UUID userId){
        if(userId==null || userId.equals(" ")){
            return false;
        }
        return true;
    }

    private boolean isInvalidCredentials(String email, String password) {
        return email == null || email.isEmpty() || password == null || password.isEmpty();
    }







}
