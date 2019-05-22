package com.webim.testshaikhivaliev.net;

import com.webim.testshaikhivaliev.model.FriendResponse;
import com.webim.testshaikhivaliev.model.UserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkApi {

    @GET("/method/users.get")
    Single<UserResponse> getUserInformation();

    @GET("/method/friends.get")
    Single<FriendResponse> getFriendList(
            @Query("order") String order,
            @Query("count") String count,
            @Query("fields") String fields
    );

}
