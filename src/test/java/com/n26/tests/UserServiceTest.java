package com.n26.tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.n26.entities.User;
import com.n26.log.Log;
import com.n26.steps.UserServiceSteps;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    Faker fake = new Faker();
    User user = createUser();
    User user2 = createUser();

    List<User> usersList = new ArrayList<>();

    @BeforeTest
    public void createBody(){

    }

    @Test(priority = 2)
    public void loginTest() {
        Log.info("User login test starts...");
        Response responseLogin = UserServiceSteps.login(user.getUsername(), user.getPassword());
        Assert.assertEquals(responseLogin.getStatusCode(), 200,
                "Status code is not as expected");
        Log.info(user + " logged in successfully!");
    }

    @Test(priority = 1)
    public void createUsersTest() {
        Log.info("User creation test starts...");
        Response responseCreatedUser = UserServiceSteps.createUser(user);
        Assert.assertEquals(responseCreatedUser.getStatusCode(), 200,
                "Status code is not as expected\"");
        Log.info(user + " created successfully!");
    }


    @Test(priority = 3)
    public void getUserByUsernameTest() {
        Log.info("Get user test starts...");
        Response responseCreatedUser = UserServiceSteps.createUser(user);
        User createdUser = UserServiceSteps.getUserByUsername(user.getUsername()).as(User.class);
        Assert.assertEquals(createdUser.getId(), user.getId(),
                "User ID is incorrect");
        Assert.assertEquals(createdUser.getUsername(), user.getUsername(),
                "Username is incorrect");
        Log.info(user + " retrieved successfully!");
    }



    @Test(priority = 4)
    public void logoutTest() {
        Log.info("User Logout test starts...");
        Response responseLogin = UserServiceSteps.logout();
        Assert.assertEquals(responseLogin.getStatusCode(), 200,
                "Status code is not as expected");
        Log.info(user + " logged out successfully!");
    }

    @Test(priority = 5)
    public void deleteUserTest() {
        Log.info("Delete user test starts...");
        Response responseCreatedUser = UserServiceSteps.createUser(user);
        UserServiceSteps.deleteUserByUsername(user.getUsername());
        Response responseDeletedUser = UserServiceSteps.getUserByUsername(user.getUsername());
        Assert.assertEquals(responseDeletedUser.getStatusCode(), 404,
                "User is not deleted");
        Log.info(user + " deleted successfully!");
    }

    @Test(priority = 6)
    public void createUserWithList() throws IOException {

        final List<User> userList = new ArrayList<>(2);
        userList.add(user);
        userList.add(user2);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, userList);
        final byte[] data = out.toByteArray();
        Response responseCreatedUserWithList = UserServiceSteps.createUserWithList(new String(data));
        Assert.assertEquals(responseCreatedUserWithList.getStatusCode(), 200,
                "Status code is not as expected\"");
        User createdUser = UserServiceSteps.getUserByUsername(user2.getUsername()).as(User.class);
        Assert.assertEquals(createdUser.getId(), user2.getId(),
                "User ID is incorrect");
        Log.info(" Users with List created successfully!");

    }


    private User createUser() {
        return new User()
                .setId(fake.number().randomDigitNotZero())
                .setUsername(fake.name().username())
                .setFirstName(fake.name().firstName())
                .setLastName(fake.name().lastName())
                .setEmail("johndoe@test.com")
                .setPassword("abc1@XYZ")
                .setPhone(String.valueOf(fake.phoneNumber()))
                .setUserStatus(1);
    }


}
