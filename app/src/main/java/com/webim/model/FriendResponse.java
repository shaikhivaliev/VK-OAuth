package com.webim.testshaikhivaliev.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FriendResponse implements Serializable {

    @SerializedName("response")
    private ResponseBean response;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {

        @SerializedName("items")
        private List<User> mUsers;

        public List<User> getUsers() {
            return mUsers;
        }

        public void setUsers(List<User> users) {
            mUsers = users;
        }

    }

}
