package com.n26.steps;

import com.n26.entities.User;
import com.n26.service.UserService;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.n26.service.uritemplate.UserServiceUri.*;

public class UserServiceSteps {
    private static final UserService USER_SERVICE = UserService.getInstance();

    public static Response getUserByUsername(String username) {
        return USER_SERVICE.getRequest(USER_BY_USERNAME, username);
    }

    public static Response login(String username, String password) {
        return USER_SERVICE.getRequest(USER_LOGIN, username, password);
    }

    public static Response logout() {
        return USER_SERVICE.getRequest(USER_LOGOUT);
    }

    public static Response createUser(User user) {
        return USER_SERVICE.postRequest(USER, user);
    }

    public static Response createUserWithList(String userPayLoad) {
        return USER_SERVICE.postRequest(USER_WITH_LIST, userPayLoad);
    }

    public static void deleteUserByUsername(String username) {
        USER_SERVICE.deleteRequest(USER_BY_USERNAME, username);
    }
}