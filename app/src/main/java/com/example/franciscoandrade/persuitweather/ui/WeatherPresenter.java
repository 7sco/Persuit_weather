package com.example.franciscoandrade.persuitweather.ui;

import android.support.annotation.NonNull;

import com.example.franciscoandrade.persuitweather.api.ClientService;
import com.example.franciscoandrade.persuitweather.api.WeatherApi;
import com.example.franciscoandrade.persuitweather.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter implements WeatherContract.Presenter {

    private static final String TAG= WeatherPresenter.class.getSimpleName();
    private WeatherContract.View viewImpl;
    private ClientService clientService;

    public WeatherPresenter(@NonNull WeatherContract.View viewImpl,
                          @NonNull ClientService clientService) {
        this.viewImpl = viewImpl;
        this.clientService = clientService;
    }

    @Override
    public void getWeatherList() {
        WeatherApi matchApi= clientService.getWeatherApi();
        Call<WeatherResponse> weatherCall= matchApi.getWeather(11220);
        weatherCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                viewImpl.setRecyclerView(response.body().getResponse().get(0).getPeriods());
            }
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                viewImpl.showMessage();
            }
        });
    }
}
