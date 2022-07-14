package com.n26.service;

import com.n26.service.uritemplate.UriTemplate;
import io.restassured.response.Response;

public class StoreService extends CommonService {
    private static StoreService instance;

    public static StoreService getInstance() {
        if (instance == null) {
            instance = new StoreService();
        }
        return instance;
    }

    public Response getRequest(UriTemplate uri) {return super.getRequest(uri.getUri());}
    public Response getRequest(UriTemplate uri, int id) {
        return super.getRequest(uri.getUri(id));
    }
    public Response postRequest(UriTemplate uri, Object body) {
        return super.postRequest(uri.getUri(), body);
    }
    public void deleteRequest(UriTemplate uri, int orderId) {
        super.deleteRequest(uri.getUri(orderId));
    }
}