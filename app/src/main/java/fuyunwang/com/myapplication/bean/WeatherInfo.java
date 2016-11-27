package fuyunwang.com.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by 冬雪心境 on 2016/11/27.
 */
public class WeatherInfo {
    public String citycode;
    public String publishTime;
    public String currentTemp;
    public String cityname;
    public ArrayList<WeatherForecast> forecast;

    public ArrayList<WeatherForecast> getForecast() {
        return forecast;
    }

    public void setForecast(ArrayList<WeatherForecast> forecast) {
        this.forecast = forecast;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
}
