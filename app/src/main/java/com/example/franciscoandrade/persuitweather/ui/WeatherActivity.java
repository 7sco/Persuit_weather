package com.example.franciscoandrade.persuitweather.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.franciscoandrade.persuitweather.R;
import com.example.franciscoandrade.persuitweather.api.ClientService;
import com.example.franciscoandrade.persuitweather.model.Periods;
import com.example.franciscoandrade.persuitweather.recyclerview.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<Periods> savedPeriods;
    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        ClientService clientService = new ClientService(getString(R.string.WW_Domain));
        WeatherContract.Presenter presenter = new WeatherPresenter(this, clientService);
        adapter = new WeatherAdapter(true, this.getPackageName());
        if(savedInstanceState!=null){
            savedPeriods=(ArrayList<Periods>) savedInstanceState.getSerializable(getResources().getString(R.string.rotation_text));
            adapter.addWeather(savedPeriods);
            recyclerView.setAdapter(adapter);
        }
        else {
            presenter.getWeatherList();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(getResources().getString(R.string.rotation_text),  savedPeriods);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setRecyclerView(List<Periods> weatherList) {
        adapter.addWeather(weatherList);
        savedPeriods= new ArrayList<>();
        savedPeriods.addAll(weatherList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMessage() {
        String message=this.getString(R.string.error_no_internet);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.fahrenheit)adapter.reloadData(true);
        else adapter.reloadData(false);
        return super.onOptionsItemSelected(item);
    }
}
