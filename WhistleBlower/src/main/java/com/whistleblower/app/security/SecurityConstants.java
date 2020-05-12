package com.whistleblower.app.security;

public class SecurityConstants {
    //NewIssueController
    public static final String NEW_ISSUE_URL_ROOT = "/issue";
    public static final String SEND_NEW_ISSUE_URL = "/send";
    public static final String GET_NEW_ISSUE_URL = "/get-all";
    public static final String MOVE_NEW_ISSUE_URL = "/move";


    //UserController
    public static final String USER_URL_ROOT = "/user";
    public static final String GET_ALL_LAWYERS = "/lawyers";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    //Roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_LAWYER = "LAWYER";
    public static final String ROLE_USER = "USER";


    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_KEY = "token";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
