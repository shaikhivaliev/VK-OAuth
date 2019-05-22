package com.webim.testshaikhivaliev.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserResponse implements Serializable {


    @SerializedName("response")
    private List <User> mUsers;

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }
}
