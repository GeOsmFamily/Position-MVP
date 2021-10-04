package com.sogefi.position.api;




import com.sogefi.position.models.Nominatim;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("search")
    Call<List<Nominatim>> nominatim(@Query("q") String q, @Query("format") String format, @Query("addressdetails") int addressdetails, @Query("countrycodes") String countrycodes);

    @GET("reverse")
    Call<Nominatim> nominatimCoord(@Query("lat") String lat, @Query("lon") String lon, @Query("addressdetails") int addressdetails, @Query("format") String format);


    @GET("search")
    Single<Response<List<Nominatim>>> nominatimRx(@Query("q") String q, @Query("format") String format, @Query("addressdetails") int addressdetails, @Query("countrycodes") String countrycodes);


}
