package fuyunwang.com.myapplication.bean;

/**
 * Created by 冬雪心境 on 2016/11/27.
 */
public class Weather {

    public String resultCode;
    public String resultDesc;
    public WeatherInfo info;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public WeatherInfo getInfo() {
        return info;
    }

    public void setInfo(WeatherInfo info) {
        this.info = info;
    }
}
