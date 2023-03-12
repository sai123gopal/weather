package com.saigopal.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.saigopal.weather.databinding.ModeDaysWeatherLtemsBinding;
import com.saigopal.weather.models.HourlyForecastResponse;

import java.util.List;

public class MoreDaysListAdapter extends RecyclerView.Adapter<MoreDaysListAdapter.ViewHolder> {


    private List<HourlyForecastResponse.ThreeHourForecastWeather> list;
    private Context context;

    public MoreDaysListAdapter(List<HourlyForecastResponse.ThreeHourForecastWeather> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ModeDaysWeatherLtemsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.mode_days_weather_ltems,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setBinding(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ModeDaysWeatherLtemsBinding binding;
        public ViewHolder(@NonNull ModeDaysWeatherLtemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void setBinding(HourlyForecastResponse.ThreeHourForecastWeather model){
            binding.setModel(model);
        }


    }
}
