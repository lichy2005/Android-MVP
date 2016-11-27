package fuyunwang.com.myapplication.view;

import fuyunwang.com.myapplication.bean.Weather;

/**
 * Created by 冬雪心境 on 2016/11/27.
 */
public interface WeatherView {
    /**
     * 显示进度条
     */
    void showLoading();

    /**
     *隐藏进度条
     */
    void dismissLoading();

    /**
     * 显示更新失败
     */
    void showError();

    /**
     * 更新UI
     * @param weather
     */
    void setWeatherInfo(Weather weather);
}
