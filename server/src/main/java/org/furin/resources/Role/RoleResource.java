package org.furin.resources.Role;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.furin.common.Entity;
import org.furin.common.builders.ResponseBuilder;
import org.furin.entities.permission.Permission;
import org.furin.entities.role.Role;
import org.furin.services.permissions.PermissionService;
import org.furin.services.roles.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.furin.constants.FormConstants.ERROR;
import static org.furin.constants.FormConstants.SUCCESS;
import static org.furin.constants.GeneralConstants.*;
import static org.furin.constants.PermissionConstants.NO_PERMISSIONS_WERE_FOUND;
import static org.furin.constants.PermissionConstants.ONE_PERMISSION_REQUIRED;
import static org.furin.constants.RoleConstants.*;

@Path("/api/roles")
@Transactional
public class RoleResource {

    ResponseBuilder responseBuilder=ResponseBuilder.getInstance();
    HashMap<String,Object> response=null;

    @Inject
    RoleService roleService;

    @Inject
    PermissionService permissionService;

    @GET
    public Response getAllRoles(){
        response=responseBuilder.createResponse();
        response.put(DATA,roleService.listAll());
      return responseBuilder.buildSuccessResponse(response);
    }

    @POST
    public Response createRole(@QueryParam("roleName")String roleName,List<Entity> permissionIds){
        response=responseBuilder.createResponse();
        if( roleName==null || roleName.isEmpty() ){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ROLE_NAME_REQUIRED);
        }
        Role existingRole=roleService.findByKey(roleName);
        if(existingRole!=null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ROLE_NAME_ALREADY_EXISTS);
        }
        List<Permission> permissions=permissionService.getPermissionsUsingIds(permissionIds);
        Role createdRole=roleService.createRole(roleName,permissions);
        if(createdRole==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,UNEXPECTED_ERROR);
        }
        response.put(DATA,createdRole);
        return responseBuilder.buildSuccessResponse(response);
    }

    @DELETE
    public Response deleteRole(@QueryParam("roleName")String roleName){
        response=responseBuilder.createResponse();
        if( roleName==null || roleName.isEmpty() ){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ROLE_NAME_REQUIRED);
        }
        Role existingRole=roleService.findByKey(roleName);
        if(existingRole==null){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ROLE_NOT_EXISTS);
        }
        Boolean isRoleDeleted=roleService.deleteRole(existingRole);
        if(Boolean.FALSE.equals(isRoleDeleted)){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,ROLE_CANNOT_BE_DELETED);
        }
        return responseBuilder.buildSuccessResponse(response);
    }

    @Path("{roleId}/permissions")
    @GET
    public Response getPermissionsOfRole(@PathParam("roleId") UUID roleId){
        response=responseBuilder.createResponse();
        if(!isValidRoleId(roleId)){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,VALID_ROLE_ID_REQUIRED);
        }
        response.put(DATA,roleService.findById(roleId).getPermissions()) ;
        return responseBuilder.buildSuccessResponse(response);
    }

    @Path("{roleId}/permissions")
    @POST
    public Response addPermissionsToRole(@PathParam("roleId")UUID roleId, List<Entity> permissionsId){
        response=responseBuilder.createResponse();
        if(permissionsId.isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,ONE_PERMISSION_REQUIRED);
        }
        if(!isValidRoleId(roleId)){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,VALID_ROLE_ID_REQUIRED);
        }
        Role role=roleService.findById(roleId);
        if(role==null){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,ROLE_NOT_EXISTS);
        }
        List<Permission> permissionsToBeAdded=permissionService.getPermissionsUsingIds(permissionsId);
        if(permissionsToBeAdded.isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,NO_PERMISSIONS_WERE_FOUND);
        }
        List<Permission> permissionAddedResponse=roleService.assignPermissions(role,permissionsToBeAdded);
        if(permissionAddedResponse.isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,UNEXPECTED_ERROR);
        }
        response.put(DATA,permissionAddedResponse) ;
        return Response.status(Response.Status.ACCEPTED).entity(response).build();
    }


    @Path("{roleId}/permissions")
    @DELETE
    public Response deletePermissionsOfRole(@PathParam("roleId")UUID roleId, List<Entity> permissionsId){
        response=responseBuilder.createResponse();
        if(permissionsId.isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,ONE_PERMISSION_REQUIRED);
        }
        if(!isValidRoleId(roleId)){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,VALID_ROLE_ID_REQUIRED);
        }
        Role role=roleService.findById(roleId);
        if(role==null){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,ROLE_NOT_EXISTS);
        }
        List<Permission> permissions=permissionService.getPermissionsUsingIds(permissionsId);
        if(permissions.isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,NO_PERMISSIONS_WERE_FOUND);
        }
        List<Permission> permissionRemoveResponse=roleService.removePermissions(role,permissions);
        if(permissionRemoveResponse.isEmpty()){
            return responseBuilder.buildErrorResponse(Response.Status.BAD_GATEWAY,UNEXPECTED_ERROR);
        }
        response.put(DATA,permissionRemoveResponse);
        return responseBuilder.buildSuccessResponse(response);
    }



    public boolean isValidRoleId(UUID roleId){
        if(roleId==null && roleId.equals(" ")){
            return false;
        }
        return true;
    }






}
