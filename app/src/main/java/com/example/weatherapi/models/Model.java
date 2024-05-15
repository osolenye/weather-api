package com.example.weatherapi.models;

import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("main")
    Main model;

    @SerializedName("wind")
    Wind wind_model;

    @SerializedName("sys")
    Sys sys_model;

    @SerializedName("clouds")
    Clouds clouds_model;
    @SerializedName("timezone")
    long timezone;

    public long getTime_zone() {
        return timezone;
    }

    public Main getModel() {
        return model;
    }

    public Wind getWind_model() {
        return wind_model;
    }

    public Sys getSys_model() {
        return sys_model;
    }

    public Clouds getClouds_model() {
        return clouds_model;
    }

}
//
//import com.google.gson.annotations.SerializedName;
//
//public class Model {
//    @SerializedName("main")
//    Main main_model;
//
//    @SerializedName("wind")
//    Wind wind_model;
//
//    @SerializedName("sys")
//    Sys sys_model;
//
//    @SerializedName("clouds")
//    Clouds clouds_model;
//
//    @SerializedName("dt")
//    Dt dt_model;
//
//    public long getTime_zone() {
//        return time_zone;
//    }
//
//    @SerializedName("timezone")
//    long time_zone;
//
//    public Main getMain_model() {
//        return main_model;
//    }
//
//    public Wind getWind_model() {
//        return wind_model;
//    }
//
//    public Sys getSys_model() {
//        return sys_model;
//    }
//
//    public Clouds getClouds_model() {
//        return clouds_model;
//    }
//
//    public Dt getDt_model() {
//        return dt_model;
//    }
//}
