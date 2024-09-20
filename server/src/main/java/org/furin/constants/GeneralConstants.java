package org.furin.constants;

import java.util.List;
import java.util.Set;

public class GeneralConstants {
    public static final String ADMIN_ASSIGNED_TO_ORGANIZATION="Assigned Admin to Organization Successfully";
    public static final String STATUS="status";

    public static final String DATA="data";
    public static final String ERROR_DETAILS="errorDetails";

    public static final String ROLE_ID_REQUIRED="roleId is required";

    public static final String UNEXPECTED_ERROR="Unexpected Error Happened!";

    public static final String SUPER_ADMIN_LOGIN_PATH="/api/admin/auth/signin";

    public static final String SUPER_ADMIN_SIGN_UP_PATH="/api/admin/auth/signup";
    public static final String USER_LOGIN_PATH="/api/users/login";
    public static final String USER_REGISTER_PATH="/api/users/signup";

    public static final String SUPER_ADMIN_EXISTENCE="/api/admin/auth/SuperAdmin/existence";


    public static final Set<String> allowdedPaths=Set.of(SUPER_ADMIN_LOGIN_PATH,SUPER_ADMIN_SIGN_UP_PATH,USER_LOGIN_PATH,SUPER_ADMIN_EXISTENCE,USER_REGISTER_PATH);

    public static final String AUTHORIZATION="Authorization";
    public static final String AUTHORIZATION_HEADER_REQUIRED="Authorization header must be provided";

    public static final String CONTACT_ADMIN_MSG="Please! Contact Admin";

    public static final String SESSION_EXPIRED="Session Expired!";

    public static final String ACCESS_TOKEN="access_token";
   public static final String SUPER_ADMIN="SuperAdmin";
}
