package com.sogefi.position.api;


import com.sogefi.position.models.Batiments;
import com.sogefi.position.models.BatimentsModel;
import com.sogefi.position.models.Categories;
import com.sogefi.position.models.Etablissements;
import com.sogefi.position.models.Horaires;
import com.sogefi.position.models.Images;
import com.sogefi.position.models.Nominatim;
import com.sogefi.position.models.ResponseApi;
import com.sogefi.position.models.SearchEtablissement;
import com.sogefi.position.models.SearchSousCategories;
import com.sogefi.position.models.Telephones;
import com.sogefi.position.models.Tracking;
import com.sogefi.position.models.UserModel;
import com.sogefi.position.models.Zones;
import com.sogefi.position.models.data.DataBatiments;
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.models.data.DataTracking;

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

    @GET("api/batiments")
    Call<BatimentsModel> getbatiments(@Header("X-Authorization") String apiKey);

    @POST("api/etablissements")
    Call<Etablissements> addetablissements(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body);


    @POST("api/etablissements/{id}")
    Call<Etablissements> updateetablissements(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body,@Path("id") int idEtablissement);

   /* @Headers({"Content-Type: application/json"})
    @PUT("api/etablissements/{id}")
    Call<Etablissements> updateetablissements(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body DataEtablissements body, @Path("id") int idEtablissement);*/

    @POST("api/images")
    Call<Images> addimage(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body);

    @POST("api/images/{id}")
    Call<Images> updateimage(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body,@Path("id") int idImage);

    @POST("api/telephones")
    Call<Telephones> addtelephone(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body);

    @POST("api/telephones/{id}")
    Call<Telephones> updatetelephone(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body,@Path("id") int idPhone);

    @POST("api/horaires")
    Call<Horaires> addhoraire(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body);

    @POST("api/horaires/{id}")
    Call<Horaires> updatehoraire(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body RequestBody body,@Path("id") int idHoraire);

    @POST("api/tracking")
    Call<Tracking> addtracking(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Body DataTracking body);

    @GET("api/search/etablissements")
    Call<SearchEtablissement> searchetablissement(@Header("X-Authorization") String apiKey, @Query("q") String q);


    @GET("api/search/souscategories")
    Call<SearchSousCategories> searchsouscategories(@Header("X-Authorization") String apiKey, @Query("q") String q);

    @GET("api/zones/{id}")
    Call<Zones> getzone(@Header("X-Authorization") String apiKey, @Header("Authorization") String token, @Path("id") int idZone);
}

