package com.saigopal.weather.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HourlyForecastResponse {

    @SerializedName("cod")
    private String cod;

    @SerializedName("message")
    private double message;

    @SerializedName("cnt")
    private int cnt;

    @SerializedName("list")
    private List<ThreeHourForecastWeather> list;

    public String getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<ThreeHourForecastWeather> getList() {
        return list;
    }


    public class ThreeHourForecastWeather {

        @SerializedName("dt")
        private Long dt;

        @SerializedName("main")
        private Main main;

        @SerializedName("weather")
        private List<WeatherItem> weather;

        @SerializedName("clouds")
        private Clouds clouds;

        @SerializedName("wind")
        private Wind wind;

        @SerializedName("visibility")
        private Long visibility;

        @SerializedName("pop")
        private Double pop;

        @SerializedName("rain")
        private Rain rain;

        @SerializedName("dt_txt")
        private String dtTxt;

        public Long getDt() {
            return dt;
        }

        public void setDt(Long dt) {
            this.dt = dt;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public List<WeatherItem> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherItem> weather) {
            this.weather = weather;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }

        public Wind getWind() {
            return wind;
        }

        public Long getVisibility() {
            return visibility;
        }

        public Double getPop() {
            return pop;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Rain getRain() {
            return rain;
        }

        public void setRain(Rain rain) {
            this.rain = rain;
        }

        public String getDtTxt() {
            return dtTxt;
        }

        public void setDtTxt(String dtTxt) {
            this.dtTxt = dtTxt;
        }
    }

}
