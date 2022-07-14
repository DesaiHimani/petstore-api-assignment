package com.n26.steps;

import com.n26.entities.Order;
import com.n26.service.StoreService;
import io.restassured.response.Response;

import static com.n26.service.uritemplate.StoreServiceUri.*;

public class StoreServiceSteps {
    private static final StoreService STORE_SERVICE = StoreService.getInstance();

    public static Response createOrder(Order order) {
        return STORE_SERVICE.postRequest(STORE_ORDER, order);
    }

    public static Response getOrderById(int id) {
        return STORE_SERVICE.getRequest(STORE_ORDER_BY_ID, id);
    }

    public static void deleteOrderById(int orderId) {
        STORE_SERVICE.deleteRequest(STORE_ORDER_BY_ID, orderId);
    }

    public static Response getInventory() {
        return STORE_SERVICE.getRequest(STORE_INVENTORY);
    }


}