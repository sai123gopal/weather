package com.saigopal.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.saigopal.weather.databinding.ActivityMainBinding;
import com.saigopal.weather.models.CurrentResponse;
import com.saigopal.weather.models.HourlyForecastResponse;

import java.io.Console;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    ApiInterface apiInterface;
    double lat = 0.0;
    double lon = 0.0;
    String appId = "Place your API Key here";

    String selectedLocation = "";

    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        lat = Double.parseDouble(PreferenceManager.getDefaultSharedPreferences(this).getString("lat", "0"));
        lon = Double.parseDouble(PreferenceManager.getDefaultSharedPreferences(this).getString("lon", "0"));

        selectedLocation = PreferenceManager.getDefaultSharedPreferences(this).getString("selectedLocationName","");

        binding.setLocation(selectedLocation);
        dialog =  showLoadingDialog(this);


        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        if(lat==0L){
            checkLocationPermission();
        }else {
            getCurrentData(false);
            getHourlyData();
        }

        if(isNetworkConnected(this)){
            dialog.show();
        }else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }



        binding.idLocationChange.setOnClickListener(view -> startActivity(new Intent(this,LocationChoseActivity.class)));

        binding.idMore.setOnClickListener(v -> startActivity(new Intent(this,ModeDaysActivity.class)));

    }

    private void checkLocationPermission() {
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION},
                        100);
                return ;
            }
        }

        getLocation();

    }


    public void getCurrentData(boolean isFirstTime) {
        apiInterface.getCurrentWeather((long) lat, (long) lon, appId, "metric")
                .enqueue(new Callback<CurrentResponse>() {
                    @Override
                    public void onResponse(Call<CurrentResponse> call, Response<CurrentResponse> response) {

                        dialog.dismiss();
                        if (response.isSuccessful()) {
                            binding.setModel(response.body());
                            Log.d("tag", "onResponse: "+response.body());
                            if (isFirstTime){
                                binding.setLocation(response.body().getName());
                            }
                        } else {
                            try {
                                Log.d("tag", "onResponse: " + response.errorBody().string());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentResponse> call, Throwable t) {
                        dialog.dismiss();
                    }
                });
    }

    public void getHourlyData() {
        apiInterface.getDataByHours((long) lat, (long) lon, appId, "metric", 4)
                .enqueue(new Callback<HourlyForecastResponse>() {
                    @Override
                    public void onResponse(Call<HourlyForecastResponse> call, Response<HourlyForecastResponse> response) {

                        if (response.isSuccessful()) {
                            binding.setHModel(response.body());
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Please provide location PERMISSION", Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (myLocation == null)
        {
            myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if(myLocation != null) {
                lat = myLocation.getLatitude();
                lon = myLocation.getLongitude();

                PreferenceManager.getDefaultSharedPreferences(this).edit().putString("lon", String.valueOf(lon)).apply();
                PreferenceManager.getDefaultSharedPreferences(this).edit().putString("lat", String.valueOf(lat)).apply();

                getCurrentData(true);
                getHourlyData();

            }else {
                Toast.makeText(this, "Please turn on your location and come back", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        finishAffinity();
    }

    public Dialog showLoadingDialog(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return dialog;
    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}