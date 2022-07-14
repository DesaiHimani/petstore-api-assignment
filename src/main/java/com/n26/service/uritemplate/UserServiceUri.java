package com.n26.service.uritemplate;

public class UserServiceUri {
    public static final UriTemplate USER = new UriTemplate("user");
    public static final UriTemplate USER_BY_USERNAME = new UriTemplate("user/%s");
    public static final UriTemplate USER_LOGIN = new UriTemplate("user/login");
    public static final UriTemplate USER_LOGOUT = new UriTemplate("user/logout");
    public static final UriTemplate USER_WITH_LIST = new UriTemplate("user/createWithList");
}