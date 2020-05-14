package com.whistleblower.app.security;

public class SecurityConstants {
    //IssueController
    public static final String ISSUE_URL_ROOT = "/issue";
    public static final String CREATE_NEW_ISSUE = "/create";
    public static final String GET_ALL_ISSUES_FOR_ADMIN = "/get-all";
    public static final String GET_ALL_ISSUES_FOR_LAWYER = "/get-all-lawyer";
    public static final String ASSIGN_ISSUE = "/assign";
    public static final String CHANGE_ISSUE_STATUS = "/change-status";

    //UserController
    public static final String USER_URL_ROOT = "/user";
    public static final String GET_ALL_LAWYERS = "/lawyers";

    //CategoryController
    public static final String CATEGORY_URL_ROOT = "/category";
    public static final String REMOVE_CATEGORY = "/remove";
    public static final String ADD_CATEGORY = "/add";
    public static final String GET_CATEGORIES = "/get-all";

    //PostBox
    public static final  String POSTBOX_URL_ROOT = "/post";
    public static final  String POSTBOX_SEND_BY_LAWYER = "/send";
    public static final  String POSTBOX_REPLY_USER = "/reply";
    public static final  String POSTBOX_GET_ALL_FOR_LAWYER = "/get-lawyer";
    public static final  String POSTBOX_GET_ALL_FOR_USER = "/get-user";


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
