package fuyunwang.com.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import fuyunwang.com.myapplication.R;

/**
 * Created by 冬雪心境 on 2016/11/27.
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private String[] values;
    private String[] titles;

    public MyAdapter(String[] values, Context context){
        this.context=context;
        this.values=values;
        titles = new String[]{"地点", "发布日期", "当前温度", "预测日期", "最高温度", "最低温度", "风力", "风向", "大气状况"};
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public String  getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
//            view=View.inflate(context, R.layout.item_layout,null);
            view= LayoutInflater.from(context).inflate(R.layout.item_layout,null);
            holder.tv_title= (TextView) view.findViewById(R.id.title);
            holder.tv_value= (TextView) view.findViewById(R.id.value);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_title.setText(titles[i]);
        holder.tv_value.setText(values[i]);
        return view;
    }

    static class ViewHolder{
        TextView tv_title;
        TextView tv_value;
    }
}
