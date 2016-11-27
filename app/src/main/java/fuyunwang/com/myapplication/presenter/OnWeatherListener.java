package fuyunwang.com.myapplication.presenter;

import fuyunwang.com.myapplication.bean.Weather;

/**
 * Created by 冬雪心境 on 2016/11/27.
 * 数据获取成功之后，回调这个借口中的方法将数据返回
 */
public interface OnWeatherListener {
    /**
     * 记录成功的回调
     */
    void onSuccess(Weather weather);

    /**
     * 记录失败的回调
     */
    void onFailed();
}
