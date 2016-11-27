package fuyunwang.com.myapplication.model.impl;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fuyunwang.com.myapplication.bean.Weather;
import fuyunwang.com.myapplication.model.WeatherModel;
import fuyunwang.com.myapplication.presenter.OnWeatherListener;

/**
 * Created by 冬雪心境 on 2016/11/27.
 */
public class WeatherModelImpl implements WeatherModel {

    private Context context;
    //根据城市名称查询的API地址
    private static final String URL = "http://service.envicloud.cn:8082/api/getWeatherForecast";
    //环境云分配的请求ID
    private static final String ACCESSID = "ZNV5DW53YW5NMTQ4MDI0NTY3NDG0NQ==";
    private RequestQueue requestQueue;
    private Gson gson = new Gson();

    public WeatherModelImpl(Context context){
        requestQueue= Volley.newRequestQueue(context);
    }

    @Override
    public void loadWeather(String cityName, OnWeatherListener listener) {
        doGet(cityName, listener);
    }

    private void doGet(String cityName,final OnWeatherListener listener) {
        String url=URL+"?cityname="+cityName+"&ak="+ACCESSID;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                System.out.println(s);
                //将json字符串转换为weather对象
                Weather weather = gson.fromJson(s, Weather.class);
                //如果weather的resultCode==0证明查询数据成功
                if ("0".equals(weather.getResultCode())) {
                    //调用listener的onSuccess（）方法，通知数据查询成功，更新数据。
                    listener.onSuccess(weather);
                } else {
                    //数据查询失败
                    listener.onFailed();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //数据查询失败
                System.out.println("ERROR" + volleyError.getMessage());
                listener.onFailed();
            }
        });

        //执行post请求
        requestQueue.add(stringRequest);
    }


}
