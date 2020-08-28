package com.tsg.tot.data.remote;

public class ApiUtils {

    private ApiUtils() {
    }

    static final String BASE_URL = "http://192.168.0.27:8000/";

    public static ApiService getAPIService() {
        return Client.getClient().create(ApiService.class);
    }

}
