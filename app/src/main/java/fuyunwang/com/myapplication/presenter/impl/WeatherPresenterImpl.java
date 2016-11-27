package fuyunwang.com.myapplication.presenter.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import fuyunwang.com.myapplication.bean.Weather;
import fuyunwang.com.myapplication.model.WeatherModel;
import fuyunwang.com.myapplication.model.impl.WeatherModelImpl;
import fuyunwang.com.myapplication.presenter.OnWeatherListener;
import fuyunwang.com.myapplication.presenter.WeatherPresenter;

/**
 * Created by 冬雪心境 on 2016/11/27.
 */
public class WeatherPresenterImpl implements WeatherPresenter,OnWeatherListener {

    private Handler handler;
    private WeatherModel model;

    public WeatherPresenterImpl(Handler handler, Context context){
        this.handler=handler;
        model=new WeatherModelImpl(context);
    }


    @Override
    public void getWeather(String cityName) {
        model.loadWeather(cityName,this);
    }


    @Override
    public void onSuccess(Weather weather) {
        Message msg = handler.obtainMessage();
        msg.obj = weather;
        msg.what = 0x123;
        handler.sendMessage(msg);
    }

    @Override
    public void onFailed() {
        handler.sendEmptyMessage(0x000);
    }
}
