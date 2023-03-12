package com.saigopal.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.saigopal.weather.databinding.ActivityModeDaysBinding;
import com.saigopal.weather.models.HourlyForecastResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModeDaysActivity extends AppCompatActivity {

    ActivityModeDaysBinding binding;

    ApiInterface apiInterface;

    double lat = 0.0;
    double lon = 0.0;
    String appId = "Place your API Key here";
    List<HourlyForecastResponse.ThreeHourForecastWeather> list = new ArrayList<>();

    MoreDaysListAdapter moreDaysListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mode_days);
        binding.setLifecycleOwner(this);

        binding.idRecycler.setLayoutManager(new LinearLayoutManager(this));

        lat = Double.parseDouble(PreferenceManager.getDefaultSharedPreferences(this).getString("lat", "0"));
        lon = Double.parseDouble(PreferenceManager.getDefaultSharedPreferences(this).getString("lon", "0"));

        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        moreDaysListAdapter = new MoreDaysListAdapter(list,this);

        binding.idRecycler.setAdapter(moreDaysListAdapter);

        getDataByDates();
    }

    public void getDataByDates() {
        apiInterface.getDataByDays((long) lat, (long) lon, appId, "metric")
                .enqueue(new Callback<HourlyForecastResponse>() {
                    @Override
                    public void onResponse(Call<HourlyForecastResponse> call, Response<HourlyForecastResponse> response) {

                        if (response.isSuccessful()) {
                            binding.setModel(response.body());
                            list.addAll(response.body().getList());
                            moreDaysListAdapter.notifyDataSetChanged();
                        } else {
                            try {
                                Log.d("tag", "onResponse: " + response.errorBody().string());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HourlyForecastResponse> call, Throwable t) {

                    }
                });

    }

}