package com.yaratech.yaratube.data.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Producer implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("producer_slug")
    @Expose
    private String producerSlug;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Parcelable.Creator<Producer> CREATOR = new Creator<Producer>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Producer createFromParcel(Parcel in) {
            return new Producer(in);
        }

        public Producer[] newArray(int size) {
            return (new Producer[size]);
        }

    };

    protected Producer(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.producerSlug = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((Object) in.readValue((Object.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Producer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducerSlug() {
        return producerSlug;
    }

    public void setProducerSlug(String producerSlug) {
        this.producerSlug = producerSlug;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(email);
        dest.writeValue(name);
        dest.writeValue(producerSlug);
        dest.writeValue(avatar);
        dest.writeValue(description);
    }

    public int describeContents() {
        return 0;
    }

}

