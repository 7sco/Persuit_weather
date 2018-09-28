package com.example.franciscoandrade.persuitweather.ui;

import com.example.franciscoandrade.persuitweather.model.Periods;

import java.util.List;

public interface WeatherContract {

    interface View{

        void setRecyclerView(List<Periods> weatherList);
        void showMessage();

    }

    interface Presenter{

        void getWeatherList();

    }

}
