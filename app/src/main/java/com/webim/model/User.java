package com.webim.testshaikhivaliev.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {


    @SerializedName("first_name")
    private String mName;

    @SerializedName("last_name")
    private String mSurname;

    @SerializedName("city")
    private City mCity;

    @SerializedName("photo_50")
    private String mPhoto;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public City getCity() {
        return mCity;
    }

    public void setCity(City city) {
        mCity = city;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    public String getFullName() {
        StringBuilder builder = new StringBuilder();
        builder.append(mName + "\n");
        builder.append(mSurname);
        return builder.toString();
    }
}
