package fuyunwang.com.myapplication.model;

import fuyunwang.com.myapplication.presenter.OnWeatherListener;

/**
 * Created by 冬雪心境 on 2016/11/27.
 */
public interface WeatherModel {
    void loadWeather(String cityName,OnWeatherListener listener);
}
