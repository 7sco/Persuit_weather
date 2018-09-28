package com.example.franciscoandrade.persuitweather.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Periods implements Serializable{

    private String icon;
    @SerializedName("minTempF")
    private int mintempf;
    @SerializedName("minTempC")
    private int mintempc;
    @SerializedName("maxTempF")
    private int maxtempf;
    @SerializedName("maxTempC")
    private int maxtempc;
    @SerializedName("dateTimeISO")
    private String datetimeiso;

    public String getIcon() {
        return icon;
    }

    public int getMintempf() {
        return mintempf;
    }

    public int getMintempc() {
        return mintempc;
    }

    public int getMaxtempf() {
        return maxtempf;
    }

    public int getMaxtempc() {
        return maxtempc;
    }

    public String getDatetimeiso() {
        return datetimeiso;
    }
}
