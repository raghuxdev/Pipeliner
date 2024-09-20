package org.furin.resources.Permission;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.furin.entities.permission.Permission;
import org.furin.repositories.PermissionRepository;
import org.furin.services.permissions.PermissionService;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.furin.constants.FormConstants.SUCCESS;
import static org.furin.constants.GeneralConstants.*;
import static org.furin.constants.PermissionConstants.PERMISSION_ID_REQUIRED;

@Path("/api/permissions")
public class PermissionResource {

    @Inject
    PermissionService permissionService;

    HashMap<String,Object> response=new HashMap<>();

    @GET
    public List<Permission> getAllPermissions(){
        return permissionService.listAll();
    }

    @Path("{permissionId}/roles")
    @GET
    public Response getRolesWithPermission(@PathParam("permissionId") UUID permissionId){
        if(permissionId==null && permissionId.equals(" ")){
            response.put(SUCCESS,"false");
            response.put(ERROR_DETAILS,PERMISSION_ID_REQUIRED);
            return Response.status(Response.Status.BAD_GATEWAY).entity(response).build();
        }
        response.put(SUCCESS,"true");
        response.put(DATA,permissionService.getRolesWithPermission(permissionId)) ;
        return Response.status(Response.Status.ACCEPTED).entity(response).build();
    }


}


