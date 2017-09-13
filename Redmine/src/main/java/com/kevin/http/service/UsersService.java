package com.kevin.http.service;

import io.reactivex.Observable;
import okhttp3.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * <p>
 * - Created by: maitao.
 * <br>
 * -       Date: 17-9-13.
 */

public interface UsersService {

    @GET("users.json")
    Observable<Response> all(@Header("Authorization") String auth);

    @GET("users/{id}.json")
    Observable<Response> detail(@Path("id") String id);

    @GET("users/current.json")
    Observable<Response> current();

    //删除用户
    @DELETE("users/{id}.json")
    Observable<Response> delete(@Path("id") String id);

    // 更新用户
    @PUT("users/{id}.json")
    Observable<Response> update(@Path("id") String id);
}
