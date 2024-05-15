package com.example.weatherapi.models;

import com.google.gson.annotations.SerializedName;

public class Dt {
    @SerializedName("dt")
    private long dt;

    public long getDt() {
        return dt;
    }
}
