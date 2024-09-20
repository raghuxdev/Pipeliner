package org.furin.utility;

import jakarta.ws.rs.core.HttpHeaders;
import org.eclipse.microprofile.jwt.JsonWebToken;

import static org.furin.constants.GeneralConstants.ACCESS_TOKEN;

public class TokenUtility {
    public static String getTokenFromHeader(HttpHeaders headers){
        String accessToken=null;
        if(headers.getCookies().containsKey(ACCESS_TOKEN)){
            accessToken=headers.getCookies().get(ACCESS_TOKEN).getValue();
        }
        return accessToken;
    }
}
