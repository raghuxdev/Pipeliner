package org.furin.resources.mfa;

import io.smallrye.jwt.auth.principal.JWTParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.furin.common.builders.ResponseBuilder;
import org.furin.services.jwt.JWTService;
import org.furin.services.user.UserService;

import java.util.HashMap;

import static org.furin.constants.UserConstants.MFA_VERIFICATION_FAILED;
import static org.furin.constants.UserConstants.MFA_VERIFICATION_SUCCESS;

import static org.furin.constants.FormConstants.MSG;

public class MFA {

    ResponseBuilder responseBuilder=ResponseBuilder.getInstance();

    HashMap<String,Object> response=null;

    @Inject
    JWTParser parser;

    @Inject
    JWTService jwtService;

    @Inject
    UserService userService;

    @GET
    @Path("/magiclink/verify")
    public Response magicLinkEmailVerification(@QueryParam("token")String token){
        if(token.isEmpty()){
            responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,MFA_VERIFICATION_FAILED);
        }
        JsonWebToken jwt=jwtService.parseAndVerifyToken(token);
        if(jwt==null){
            responseBuilder.buildErrorResponse(Response.Status.EXPECTATION_FAILED,MFA_VERIFICATION_FAILED);
        }
        response=responseBuilder.createResponse();
        response.put(MSG,MFA_VERIFICATION_SUCCESS);
        return responseBuilder.buildSuccessResponseWithToken(response,token);
    }
}
