package fuyunwang.com.myapplication.view.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import fuyunwang.com.myapplication.R;
import fuyunwang.com.myapplication.adapter.MyAdapter;
import fuyunwang.com.myapplication.bean.Weather;
import fuyunwang.com.myapplication.bean.WeatherForecast;
import fuyunwang.com.myapplication.bean.WeatherInfo;
import fuyunwang.com.myapplication.presenter.WeatherPresenter;
import fuyunwang.com.myapplication.presenter.impl.WeatherPresenterImpl;
import fuyunwang.com.myapplication.view.WeatherView;

/**
 * 本类作为View的实现类，注意在MVP中借口必须只关注于自己所做的事
 */
public class WeatherActivity extends AppCompatActivity implements WeatherView, View.OnClickListener {

    private WeatherPresenter weatherPresenter;
    //声明UI组件
    private AutoCompleteTextView atv_city_name;
    private Button btn_submit;
    private ProgressBar progressBar;
    private ListView listView;

    /**
     * 得到结果之后
     */
    private MyAdapter listItemAdapter;
    private String[] values = new String[9];//临时存储listView中要设置的天气信息

    private UIHandler uiHandler=new UIHandler(this);    //创建Handler用于在Presenter中更新界面UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //初始化WeatherPresenter控制器
        weatherPresenter = new WeatherPresenterImpl(uiHandler, this);
    }

    private void initView() {
        atv_city_name = (AutoCompleteTextView) findViewById(R.id.atv_city_name);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        listView = (ListView) findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //初始化为不可见
        progressBar.setVisibility(View.INVISIBLE);



        //设置按钮监听
        btn_submit.setOnClickListener(this);
        //设置自动填充文本框内容
        String[] citys = getResources().getStringArray(R.array.city_names);
        ArrayAdapter cityNamesAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, citys);
        atv_city_name.setAdapter(cityNamesAdapter);

        //初始化listView
        listItemAdapter = new MyAdapter(values, this);
        listView.setAdapter(listItemAdapter);
    }

    /**
     * 正在查询
     */
    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        btn_submit.setEnabled(false);
    }

    /**
     * 隐藏进度条
     */
    @Override
    public void dismissLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        btn_submit.setEnabled(true);
    }

    /**
     * 查询结果失败
     */
    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(),"天气数据获取失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeatherInfo(Weather weather) {
        WeatherInfo weatherInfo = weather.getInfo();
        List<WeatherForecast> forecasts = weatherInfo.getForecast();
        WeatherForecast forecast = forecasts.get(1);
        //{"地点", "发布日期", "预测日期", "当前温度", "最高温度", "最低温度", "风力", "风向", "大气状况"}
        values[0] = weatherInfo.getCityname();
        values[1] = weatherInfo.getPublishTime();
        values[2] = weatherInfo.getCurrentTemp();
        values[3] = forecast.getDate();
        values[4] = forecast.getHightemp();
        values[5] = forecast.getLowtemp();
        values[6] = forecast.getFengli();
        values[7] = forecast.getFengxiang();
        values[8] = forecast.getType();

        //通知更新
        listItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (!TextUtils.isEmpty(atv_city_name.getText())) {
            String cityName = atv_city_name.getText().toString();
            showLoading();
            weatherPresenter.getWeather(cityName);
        } else {
            Toast.makeText(this, "城市名称不能为空", Toast.LENGTH_SHORT).show();
        }
    }


    private static class UIHandler extends Handler{
        private WeakReference<WeatherActivity> reference=null;
        public UIHandler(WeatherActivity weatherActivity) {
            reference = new WeakReference<WeatherActivity>(weatherActivity);

        }

        @Override
        public void handleMessage(Message msg) {

            int what=msg.what;
            WeatherActivity weatherActivity=reference.get();
            weatherActivity.dismissLoading();
            Weather weather;
            switch (what){
                //0x123:代表数据更新成功，进行刷新数据操作
                case 0x123:
                    weather = (Weather) msg.obj;
                    weatherActivity.setWeatherInfo(weather);
                    break;
                //0x123：数据更新失败，提升用户
                case 0x000:
                    weatherActivity.showError();
                    break;
            }
            super.handleMessage(msg);
        }
    }


}
