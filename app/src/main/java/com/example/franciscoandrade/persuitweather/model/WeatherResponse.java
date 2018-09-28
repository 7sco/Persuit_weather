package com.example.franciscoandrade.persuitweather.model;


import java.io.Serializable;
import java.util.List;

public class WeatherResponse implements Serializable {

    private List<Response> response;

    public List<Response> getResponse() {
        return response;
    }

}
