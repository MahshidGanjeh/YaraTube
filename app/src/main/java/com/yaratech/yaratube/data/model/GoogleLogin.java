package com.yaratech.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoogleLogin {


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("is_registered")
    @Expose
    private Boolean isRegistered;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("mobile")
    @Expose
    private Object mobile;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("fino_token")
    @Expose
    private Object finoToken;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Object getFinoToken() {
        return finoToken;
    }

    public void setFinoToken(Object finoToken) {
        this.finoToken = finoToken;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}

