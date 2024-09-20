package org.furin.resources.organization;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.furin.annotations.RequiresPermission;
import org.furin.common.builders.ResponseBuilder;
import org.furin.constants.UserConstants;
import org.furin.dto.OrganizationDto;
import org.furin.entities.organization.Organization;
import org.furin.entities.organization.request.OrganizationEntity;
import org.furin.entities.user.User;
import org.furin.repositories.OrganizationRepository;
import org.furin.services.organization.OrganizationService;
import org.furin.services.user.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.furin.constants.FormConstants.SUCCESS;
import static org.furin.constants.GeneralConstants.DATA;
import static org.furin.constants.GeneralConstants.ERROR_DETAILS;
import static org.furin.constants.OrganizationConstants.*;

@Path("/api/organizations")
public class OrganizationResource {

    ResponseBuilder responseBuilder=ResponseBuilder.getInstance();
    HashMap<String,Object> response=new HashMap<>();
    @Inject
    OrganizationService organizationService;





    @RequiresPermission(action = "read",subject = "organizations")
    @GET
    public Response getOrganizations(){
        response=responseBuilder.createResponse();
        List<Organization> organizations=organizationService.getAll();
        List<OrganizationDto> organizationDtos=organizationService.fromOrganizations(organizations);
        response.put(DATA,organizationDtos);
        return responseBuilder.buildSuccessResponse(response);
    }

    @POST
    public Response createOrganization(OrganizationEntity organization){
        response=responseBuilder.createResponse();
        if(!isKeyPresent(organization.getKey())){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ORGANIZATION_KEY_IS_REQUIRED);
        }
        String organizationKey=organization.getKey();
        Organization existingOrganization= organizationService.getOrganizationByKey(organizationKey);
        if(existingOrganization!=null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ORGANIZATION_ALREADY_EXISTS);
        }

//        Organization createdOrganization=organizationService.createOrganization(organization.getDisplayName(),organizationKey);
        return responseBuilder.buildSuccessResponse(response);
    }

//    public Response createOrganization(OrganizationEntity organization){
//        response=responseBuilder.createResponse();
//        if(!validateOrganizationUserId(organization.getUserId())){
//            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ORGANIZATION_MUST_HAVE_ADMIN);
//        }
//        if(!isKeyPresent(organization.getKey())){
//            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ORGANIZATION_KEY_IS_REQUIRED);
//        }
//        String organizationKey=organization.getKey();
//        Organization existingOrganization= organizationService.getOrganizationByKey(organizationKey);
//        if(existingOrganization!=null){
//            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ORGANIZATION_ALREADY_EXISTS);
//        }
//        User existingUser=userService.getUserById(organization.getUserId());
//        if(existingUser==null){
//            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,VALID_USER_ID_REQUIRED);
//        }
////        Organization createdOrganization=organizationService.createOrganization(organization.getDisplayName(),organizationKey);
//        return responseBuilder.buildSuccessResponse(response);
//    }

    @GET
    @Path("{organizationId}/users")
    public Response getUsersOfOrganization(@PathParam("organizationId") UUID orgId){
        response=responseBuilder.createResponse();
        if(validateOrganizationUserId(orgId)){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,ORAGANIZATION_ID_REQUIRED);
        }
        response.put(DATA,organizationService.getUsersOfOrganization(orgId)) ;
        return responseBuilder.buildSuccessResponse(response);
    }

    public boolean validateOrganizationUserId(UUID userId){
        if(userId==null || userId.equals(" ")){
           return false;
        }
        return true;
    }

    public boolean isKeyPresent(String key){
        if(key==null || key.isEmpty()){
            return false;
        }
        return true;
    }


}
