package com.example.franciscoandrade.persuitweather.api;

import com.example.franciscoandrade.persuitweather.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {

    @GET("{zip}?client_id=36xxpENZ4lGuRDU9YpdYm&client_secret=vlWRsdLQjYSvAtBTS43abxoWjx8LGG8r2iL9pmm1")
    Call<WeatherResponse> getWeather(@Path("zip") int zip);
}
