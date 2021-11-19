package com.sogefi.position.api;


import com.sogefi.position.models.Batiments;
import com.sogefi.position.models.Categories;
import com.sogefi.position.models.Etablissements;
import com.sogefi.position.models.Horaires;
import com.sogefi.position.models.Images;
import com.sogefi.position.models.Nominatim;
import com.sogefi.position.models.ResponseApi;
import com.sogefi.position.models.Telephones;
import com.sogefi.position.models.UserModel;
import com.sogefi.position.models.data.DataBatiments;
import com.sogefi.position.models.data.DataEtablissements;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
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


    @POST("api/batiments")
    Call<Batiments> addbatiments(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body);

    @POST("api/etablissements")
    Call<Etablissements> addetablissements(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body);

    @Headers({"Content-Type: application/json"})
    @PUT("api/etablissements/{id}")
    Call<Etablissements> updateetablissements(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body DataEtablissements body, @Path("id") int idEtablissement);

    @POST("api/images")
    Call<Images> addimage(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body);


    @POST("api/telephones")
    Call<Telephones> addtelephone(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body);


    @POST("api/horaires")
    Call<Horaires> addhoraire(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body);
}
