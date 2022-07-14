package com.n26.tests;

import com.github.javafaker.Faker;
import com.n26.entities.Order;
import com.n26.entities.OrderStatus;
import com.n26.log.Log;
import com.n26.steps.StoreServiceSteps;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreServiceTest {

    Faker fake = new Faker();
    Order   order = createOrder();


    @Test(priority=1)
    public void createOrderTest() {
        Log.info("Order creation test starts...");
        Response responseCreatedOrder = StoreServiceSteps.createOrder(order);
        Assert.assertEquals(responseCreatedOrder.getStatusCode(), 200
                , "Incorrect order status code");
        Assert.assertEquals(responseCreatedOrder.as(Order.class).getId(), order.getId()
                , "Order ID is incorrect");
        Log.info(order + " created successfully!");
    }

    @Test(priority=2)
    public void getOrderByIdTest() {
        Log.info("Getting order by pet ID test starts...");
        Response responseGetOrderById = StoreServiceSteps.getOrderById(order.getId());
        Assert.assertEquals(responseGetOrderById.getStatusCode(), 200
                , "Status code is not as expected");
        Assert.assertEquals(responseGetOrderById.as(Order.class).getId(), order.getId(),
                "Order ID is incorrect");
        Log.info(order + " retrieved!");
    }

    @Test(priority = 4)
    public void deleteOrderTest() {
        Log.info("Delete order by ID test starts...");
        StoreServiceSteps.deleteOrderById(order.getId());
        Response responseDeletedOrder = StoreServiceSteps.getOrderById(order.getId());
        Assert.assertEquals(responseDeletedOrder.getStatusCode(), 404
                , "Order was not deleted");
        Log.info(order + " deleted successfully");
    }

    @Test(priority = 3)
    public void inventoryTest() {
        Log.info("Inventory test starts...");
        Response responseGetInventory = StoreServiceSteps.getInventory();
        Assert.assertEquals(responseGetInventory.getStatusCode(), 200
                , "Status code is not as expected");
        Log.info(" Inventory check successfully");
    }

    private Order createOrder() {
        return new Order()
                .setId(fake.number().randomDigitNotZero())
                .setPetId(fake.number().randomDigitNotZero())
                .setQuantity(1)
                .setShipmentDate("2022-07-10")
                .setStatus(OrderStatus.placed.toString())
                .setComplete(true);
    }


}