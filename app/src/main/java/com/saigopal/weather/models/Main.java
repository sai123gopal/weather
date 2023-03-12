package com.saigopal.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    double temp;
    @SerializedName("feels_like")
    @Expose
    double feelsLike;
    @SerializedName("temp_min")
    @Expose
    double tempMin;
    @SerializedName("temp_max")
    @Expose
    double tempMax;
    @SerializedName("pressure")
    @Expose
    double pressure;
    @SerializedName("humidity")
    @Expose
    double humidity;
    @SerializedName("sea_level")
    @Expose
    double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    double grnLevel;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getGrnLevel() {
        return grnLevel;
    }

    public void setGrnLevel(double grnLevel) {
        this.grnLevel = grnLevel;
    }
}