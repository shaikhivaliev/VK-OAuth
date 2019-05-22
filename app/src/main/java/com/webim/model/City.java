package com.webim.testshaikhivaliev.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable {

    @SerializedName("title")
    private String mCityName;

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }
}
