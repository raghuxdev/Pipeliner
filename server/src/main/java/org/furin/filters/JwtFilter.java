package org.furin.filters;

import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;
import io.smallrye.jwt.auth.principal.JWTParser;
import org.furin.annotations.RequiresPermission;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;


import static org.furin.constants.GeneralConstants.*;
import static org.furin.constants.PermissionConstants.*;


@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {




    @Inject
    JWTParser parser;

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (allowdedPaths.contains(requestContext.getUriInfo().getPath())) {
            return;
        }

        if(requestContext.getCookies().get(ACCESS_TOKEN)==null){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(AUTHORIZATION_HEADER_REQUIRED).build());
            return;
        }

        String token= requestContext.getCookies().get(ACCESS_TOKEN).getValue();

        System.out.println(requestContext.getUriInfo().getPath());


        if (token == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(AUTHORIZATION_HEADER_REQUIRED).build());
            return;
        }

        JsonWebToken jwt=null;
        try {
            // Ensure the token is valid
            jwt = parser.parse(token);

            // Get permissions from the JWT token
            List<Map<String, Object>> permissionMaps = jwt.getClaim(PERMISSIONS);
            Set<String> roles =jwt.getGroups();
            if(roles.contains(SUPER_ADMIN)){
                return;
            }

            // Get the method being called
            Method resourceMethod = resourceInfo.getResourceMethod();

            RequiresPermission permissionAnnotation = resourceMethod.getAnnotation(RequiresPermission.class);

            if (permissionAnnotation != null) {
                String requiredAction = permissionAnnotation.action();
                String requiredSubject = permissionAnnotation.subject();
               boolean hasPermission =  permissionMaps.stream().anyMatch(permission -> {
                    String actionValue = permission.get(ACTION).toString().replaceAll("\"", "");
                    String subjectValue = permission.get(SUBJECT).toString().replaceAll("\"", "");
                    return (actionValue.equals(requiredAction) && subjectValue.equals(requiredSubject));
                });
                if (!hasPermission) {
                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                            .entity(MISSING_REQUIRED_PERMISSIONS).build());
                }
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(SESSION_EXPIRED).build());
        }catch (Exception e){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(CONTACT_ADMIN_MSG).build());
        }
    }

}
