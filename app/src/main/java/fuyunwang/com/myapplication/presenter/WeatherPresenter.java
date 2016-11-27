package fuyunwang.com.myapplication.presenter;

/**
 * Created by 冬雪心境 on 2016/11/27.
 */
public interface WeatherPresenter {
    //前台调用该方法间接的调用Model中的方法获取数据
    void getWeather(String cityName);
}
