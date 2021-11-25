package com.sogefi.position.api;


import com.sogefi.position.models.Categories;
import com.sogefi.position.models.Nominatim;
import com.sogefi.position.models.ResponseApi;
import com.sogefi.position.models.UserModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("search")
    Call<List<Nominatim>> nominatim(@Query("q") String q, @Query("format") String format, @Query("addressdetails") int addressdetails, @Query("countrycodes") String countrycodes);

    @GET("reverse")
    Call<Nominatim> nominatimCoord(@Query("lat") String lat, @Query("lon") String lon, @Query("addressdetails") int addressdetails, @Query("format") String format);


    @GET("search")
    Single<Response<List<Nominatim>>> nominatimRx(@Query("q") String q, @Query("format") String format, @Query("addressdetails") int addressdetails, @Query("countrycodes") String countrycodes);

    @POST("api/auth/login")
    Call<UserModel> login(@Header("X-Authorization") String apiKey, @Body UserModel userModel);

    @POST("api/auth/logout")
    Call<ResponseApi> logout(@Header("X-Authorization") String apiKey,@Header("Authorization") String token);


    @GET("api/user/me")
    Call<UserModel> getUser(@Header("X-Authorization") String apiKey, @Header("Authorization") String token);

    @GET("api/categories")
    Call<Categories> getCategories(@Header("X-Authorization") String apiKey);

}
