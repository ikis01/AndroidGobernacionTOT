package com.tsg.kot.data.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenCustom {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("expiration")
    @Expose
    private String expiration;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public TokenCustom(String token,String expiration){
        this.token = token;
        this.expiration= expiration;

    }
    public TokenCustom(){

    }


    @NonNull
    @Override
    public String toString() {
        return "TokenCustom { token=" +this.token + " expiration = "+this.expiration+"}"
        ;
    }
}
