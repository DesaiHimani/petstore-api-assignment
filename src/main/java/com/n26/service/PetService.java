package com.n26.service;

import com.n26.service.uritemplate.UriTemplate;
import io.restassured.response.Response;

public class PetService extends CommonService {
    private static PetService instance;

    public static PetService getInstance() {
        if (instance == null) {
            instance = new PetService();
        }
        return instance;
    }

    public Response getRequest(UriTemplate uri, int id) {
        return super.getRequest(uri.getUri(id));
    }

    public Response getRequest(UriTemplate uri, String status) {
        return super.getRequest(uri.getUri(status,"status"));
    }

    public Response postRequest(UriTemplate uri, Object body) {
        return super.postRequest(uri.getUri(), body);
    }

    public Response putRequest(UriTemplate uri, Object body) {
        return super.putRequest(uri.getUri(), body);
    }
    public void deleteRequest(UriTemplate uri, int id) {
        super.deleteRequest(uri.getUri(id));
    }
}