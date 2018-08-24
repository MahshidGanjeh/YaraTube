package com.yaratech.yaratube.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;


    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("files_added")
    @Expose
    /*private Object filesAdded;
    @SerializedName("nickname")
    @Expose*/
    private String nickname;
    @SerializedName("Fino_token")
    @Expose
    private String FinoToken;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /*public void setFilesAdded(Object filesAdded) {
        this.filesAdded = filesAdded;
    }*/

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFinoToken(String finoToken) {
        FinoToken = finoToken;
    }

    public Integer getError() {

        return error;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    /*public Object getFilesAdded() {
        return filesAdded;
    }*/

    public String getNickname() {
        return nickname;
    }

    public String getFinoToken() {
        return FinoToken;
    }
}
