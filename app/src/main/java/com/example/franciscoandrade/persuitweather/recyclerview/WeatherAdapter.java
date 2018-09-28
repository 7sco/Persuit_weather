package com.example.franciscoandrade.persuitweather.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franciscoandrade.persuitweather.R;
import com.example.franciscoandrade.persuitweather.model.Periods;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private List<Periods> listWeather;
    private boolean temperature;
    private String packageName;

    public WeatherAdapter(boolean temperature, String packageName) {
        this.temperature = temperature;
        this.packageName = packageName;
        listWeather= new ArrayList<>();
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewHolder = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        return new WeatherViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) { holder.bind(listWeather.get(position)); }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    public void addWeather(List<Periods> weatherList) {
        this.listWeather.clear();
        this.listWeather.addAll(weatherList);
    }

    public void reloadData(boolean id) {
        if(this.temperature != id){
            this.temperature=id;
            notifyDataSetChanged();
        }
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon_temp)
        ImageView iconTemp;
        @BindView(R.id.high_temp_text)
        TextView highTempText;
        @BindView(R.id.low_temp_text)
        TextView lowTempText;
        @BindView(R.id.time_text)
        TextView timeText;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Periods periods) {
            String maxC = String.format(itemView.getResources().getString(R.string.celsius), String.valueOf(periods.getMaxtempc()));
            String minC = String.format(itemView.getResources().getString(R.string.celsius), String.valueOf(periods.getMintempc()));
            String maxF = String.format(itemView.getResources().getString(R.string.fahrenheit), String.valueOf(periods.getMaxtempf()));
            //String maxF = periods.getMaxtempf()+" "+itemView.getResources().getString(R.string.fahrenheit);
            String minF = String.format(itemView.getResources().getString(R.string.fahrenheit), String.valueOf(periods.getMintempf()));
            String date = formatDate(periods.getDatetimeiso());
            timeText.setText(date);
            if(!temperature){
                highTempText.setText(maxC);
                lowTempText.setText(minC);
            }else {
                highTempText.setText(maxF);
                lowTempText.setText(minF);
            }
            String name = periods.getIcon().substring(0, periods.getIcon().length()-4);
            int res = itemView.getResources().getIdentifier(name, itemView.getResources().getString(R.string.drawable), packageName);
            iconTemp.setImageResource(res);
        }

        private String formatDate(String date) {
            String currentDate="";
            SimpleDateFormat dateFormat = new SimpleDateFormat(itemView.getResources().getString(R.string.date_format), Locale.US);
            try {
                Date parsedDate = dateFormat.parse(date);
                String[] dateArr = parsedDate.toString().split(" ");
                currentDate=String.format(itemView.getResources().getString(R.string.date), dateArr[0], dateArr[1], dateArr[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return currentDate;
        }
    }
}
