package com.tsg.tot.data.remote;

public class ApiUtils {

    private ApiUtils() {
    }

    //static final String BASE_URL = "http://192.168.0.27:8000/";

    // servidor Linux "https://192.168.0.120:6104/";
    //  telmex  "https://192.168.1.134:6104/";
    //  izzi  "http://10.0.0.5:6104/"
    static final String BASE_URL = "http://192.168.1.134:6104/";
    public static ApiService getAPIService() {
        return Client.getClient().create(ApiService.class);
    }
}
