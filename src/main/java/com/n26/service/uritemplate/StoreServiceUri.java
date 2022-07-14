package com.n26.service.uritemplate;

public class StoreServiceUri {
    public static final UriTemplate STORE_ORDER = new UriTemplate("store/order");
    public static final UriTemplate STORE_ORDER_BY_ID = new UriTemplate("store/order/%s");
    public static final UriTemplate STORE_INVENTORY = new UriTemplate("store/inventory");
}