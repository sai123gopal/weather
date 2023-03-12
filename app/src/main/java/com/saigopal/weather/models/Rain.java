package com.saigopal.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("1h")
    double oneH;

    public double getOneH() {
        return oneH;
    }

    public void setOneH(double oneH) {
        this.oneH = oneH;
    }

    @Override
    public String toString() {
        return "Rain{" +
                "oneH=" + oneH +
                '}';
    }
}
