package com.saigopal.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.saigopal.weather.databinding.ActivityLocationChoseBinding;
import com.saigopal.weather.models.Cites;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class LocationChoseActivity extends AppCompatActivity {

    ActivityLocationChoseBinding  binding;
    ArrayList<Cites> cityList = new ArrayList<>();
    CitesListAdapter citesListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_chose);
        binding.setLifecycleOwner(this);

        citesListAdapter = new CitesListAdapter(cityList,this);
        try {
            JSONArray jsonElements = new JSONArray(loadJSONFromAsset());

            for (int i = 0; i < jsonElements.length(); i++) {
                JSONObject jo_inside = jsonElements.getJSONObject(i);
                String cityName = jo_inside.getString("city");
                String lat = jo_inside.getString("lat");
                String lng = jo_inside.getString("lng");
                Cites city = new Cites();
                city.setCity(cityName);
                city.setLat(lat);
                city.setLng(lng);
                cityList.add(city);
            }
            citesListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(citesListAdapter);

        binding.idSearch.setQueryHint("Search by city name");

        binding.idSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return false;
            }
        });


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("in.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e("tag", "loadJSONFromAsset: ",ex );
            return null;
        }
        return json;
    }


    public void search (String s){

        ArrayList<Cites> ml = new ArrayList<>();

        for (Cites obj : cityList) {
            if (obj.getCity().toLowerCase().contains(s.toLowerCase())) {
                ml.add(obj);
            }
        }
        CitesListAdapter facultyAdapter = new CitesListAdapter(ml, this);
        binding.list.setAdapter(facultyAdapter);
    }



}