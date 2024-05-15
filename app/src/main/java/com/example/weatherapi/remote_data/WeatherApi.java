package com.example.weatherapi.remote_data;

import android.graphics.ColorSpace;

import com.example.weatherapi.models.Model;
import com.example.weatherapi.models.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("/data/2.5/weather")
    Call<Model> getCurrentWeather(
            @Query("q") String name,
            @Query("appid") String key);

    @GET("/data/2.5/weather?&appid=a0ef0a91ed320aa9ffd6c22f58e806d3")
    Call<WeatherModel> getWeather(
            @Query("q") String name);

   // String url = "api.openweathermap.org/data/2.5/weather?q=London&appid=openweathermap.org";
    String url = "a0ef0a91ed320aa9ffd6c22f58e806d3";

}
