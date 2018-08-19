package com.yaratech.yaratube.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.ParcelConstructor;

@org.parceler.Parcel
public class CommentSummary {

    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("count")
    @Expose
    private Integer count;

    protected CommentSummary(Parcel in) {
        this.score = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    @ParcelConstructor
    public CommentSummary() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(score);
        dest.writeValue(count);
    }

    public int describeContents() {
        return 0;
    }

}
