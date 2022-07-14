package com.n26.service.uritemplate;

public class PetServiceUri {
    public static final UriTemplate PET = new UriTemplate("pet");
    public static final UriTemplate PET_BY_ID = new UriTemplate("pet/%s");
    public static final UriTemplate PET_BY_STATUS = new UriTemplate("pet/findByStatus?status=%s");
    public static final UriTemplate PET_BY_TAGS = new UriTemplate("pet/findByTags?tags=%s");
    public static final UriTemplate PET_ID_IMAGE = new UriTemplate("pet/petID?tags=%s");

}
