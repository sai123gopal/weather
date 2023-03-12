package com.saigopal.weather;

import com.saigopal.weather.models.CurrentResponse;
import com.saigopal.weather.models.HourlyForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather")
    Call<CurrentResponse> getCurrentWeather(@Query("lat") long lat, @Query("lon") long lon, @Query("appid") String appId, @Query("units") String units);

    @GET("forecast")
    Call<HourlyForecastResponse> getDataByHours(@Query("lat") long lat, @Query("lon") long lon, @Query("appid") String appId, @Query("units") String units,@Query("cnt")int count);

    @GET("forecast")
    Call<HourlyForecastResponse> getDataByDays(@Query("lat") long lat, @Query("lon") long lon, @Query("appid") String appId, @Query("units") String units);

}
