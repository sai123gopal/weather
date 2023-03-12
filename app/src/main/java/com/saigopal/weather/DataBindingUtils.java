package com.saigopal.weather;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBindingUtils {

    @BindingAdapter({"setDate"})
    public static void setDate(TextView view,String time){
        if(time!= null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dayTimeFormat = new SimpleDateFormat("EEEE, dd, MMMM");
            Date date = new Date();
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            view.setText(dayTimeFormat.format(date));
        }
    }

    @BindingAdapter({"setTime"})
    public static void setTime(TextView view,String time){
        if(time!= null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dayTimeFormat = new SimpleDateFormat("EEEE \nHH:mm");
            Date date = new Date();
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            view.setText(dayTimeFormat.format(date));
        }
    }

    @BindingAdapter({"dayTimeText"})
    public static void dayTimeText(TextView textView,String time){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dayTimeFormat = new SimpleDateFormat("MMMM,dd EEEE HH,a");
        Date date = new Date();
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        textView.setText(dayTimeFormat.format(date));

    }

    @BindingAdapter({"tempText"})
    public static void tempText(TextView view,double temp){

        int tempInt = (int) temp;

        String tempString = String.valueOf(tempInt);
        
        String s = tempString + "\u00b0";

        view.setText(s);

    }
    @BindingAdapter({"setWeatherImage"})
    public static void setWeatherImage(ImageView view, String weather){


        int image = 0;

        if (weather != null) {
            switch (weather) {
                case "Wind":
                    image = R.drawable.windy;
                    break;
                case "Rain":
                    image = R.drawable.rain;
                    break;
                case "Clouds":
                    image = R.drawable.cloudy_day;
                    break;
                case "Thunders":
                    image = R.drawable.thundersrtom;
                    break;
                default:
                    image = R.drawable.clear;
                    break;
            }
        }

        view.setImageResource(image);

    }




}
