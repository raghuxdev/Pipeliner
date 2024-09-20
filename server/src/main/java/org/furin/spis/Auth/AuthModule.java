package org.furin.spis.Auth;

public interface AuthModule {

    String getClientId();
    String getClientSecret();
    String getRedirectUri();
    String apiBaseUrl();

}
