package com.example.weatherapi.models;
import com.google.gson.annotations.SerializedName;

public class WeatherModel {
    @SerializedName("icon")
    private String icon;

    @SerializedName("cod")
    private Integer cod;
}

//import com.google.gson.annotations.SerializedName;
//
//public class WeatherModel {
//    @SerializedName("icon")
//    private String icon;
//
//    @SerializedName("cod")
//    private Integer code;
//
//    @SerializedName("temp")
//    private  Double temp;
//
//    @SerializedName("name")
//    private  String name;
//
//    @SerializedName("temp_max")
//    private Double tempMax;
//
//
//    @SerializedName("temp_min")
//    private Double tempMin;
//
//    @SerializedName("main")
//    private Main main;
//
//    @SerializedName("sys")
//    private  Sys sys;
//
//    @SerializedName("clouds")
//    private Clouds clouds;
//
//    @SerializedName("dt")
//    private long dt;
//
//    @SerializedName("timezone")
//    private long timezone;
//
//    @SerializedName("humidity")
//    private int humidity;
//
//    @SerializedName("pressure")
//    private int pressure;
//
//    @SerializedName("speed")
//    private double speed;
//
//    @SerializedName("wind_kph")
//    private double wind_kph;
//
//    @SerializedName("sunrise")
//    private long sunrise;
//
//    @SerializedName("sunset")
//    private long sunset;
//
//    @SerializedName("all")
//    private int all;
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public Double getTemp() {
//        return temp;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Double getTempMax() {
//        return tempMax;
//    }
//
//    public Double getTempMin() {
//        return tempMin;
//    }
//
//    public Main getMain() {
//        return main;
//    }
//
//    public Sys getSys() {
//        return sys;
//    }
//
//    public Clouds getClouds() {
//        return clouds;
//    }
//
//    public long getDt() {
//        return dt;
//    }
//
//    public long getTimezone() {
//        return timezone;
//    }
//
//    public int getHumidity() {
//        return humidity;
//    }
//
//    public int getPressure() {
//        return pressure;
//    }
//
//    public double getSpeed() {
//        return speed;
//    }
//
//    public double getWind_kph() {
//        return wind_kph;
//    }
//
//    public long getSunrise() {
//        return sunrise;
//    }
//
//    public long getSunset() {
//        return sunset;
//    }
//
//    public int getAll() {
//        return all;
//    }
//}
