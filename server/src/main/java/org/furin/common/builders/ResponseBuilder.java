package org.furin.common.builders;

import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import static org.furin.constants.FormConstants.ERROR;
import static org.furin.constants.FormConstants.SUCCESS;

public class ResponseBuilder {


    public static ResponseBuilder getInstance(){
        return new ResponseBuilder();
    }
    public Response buildErrorResponse(Response.Status status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, message);
        response.put(SUCCESS, false);
        return Response.status(status).entity(response).build();
    }
    public HashMap<String,Object> createResponse(){
        return new HashMap<>();
    }

    public Response buildSuccessResponse(Map<String, Object> response) {
        response.put(SUCCESS, true);
        return Response.status(Response.Status.ACCEPTED).entity(response).build();
    }

    public Response buildSuccessResponseWithToken(Map<String,Object> response,String token){
        int maxAgeInSeconds = 60*60*1000; // 1 hour expiration time
        String cookie = String.format("access_token=%s; HttpOnly; Secure; SameSite=Strict; Path=/; Max-Age=%d",
                token, maxAgeInSeconds);
        response.put(SUCCESS,true);
        return Response.status(Response.Status.ACCEPTED).header("Set-Cookie",cookie)
                .entity(response).build();
    }

}
