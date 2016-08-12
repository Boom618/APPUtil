package com.example.administrator.view;

import android.content.Context;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class TimeView {

    /**
     * 显示 时间控件
     *
     * @param view    显示的控件
     * @param maxYear 显示的年数
     * @param type    控件的类型
     */
    public void showControlTime(Context context,final TextView view, boolean loop, int maxYear, final String timeType, TimePickerView.Type type) {
        TimePickerView pickerView = new TimePickerView(context, type);

        //控制时间范围
        final Calendar calendar = Calendar.getInstance();
        pickerView.setRange(calendar.get(Calendar.YEAR) - maxYear, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦

        pickerView.setTime(new Date());
        // 循环滚动
        pickerView.setCyclic(loop);
        pickerView.setCancelable(true);

        pickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
           @Override
           public void onTimeSelect(Date date) {
               Date newTime = calendar.getTime();
                                                   // 当前时间 与 选择的时间 对比
                                                   int i = newTime.compareTo(date);
                                                   if (i >= 0) {
                                                       if (timeType.equals("ALL")) {
                                                           view.setText(getAllTime(date));

                                                       } else if (timeType.equals("YMD")) {
                                                           view.setText(get3Time(date));

                                                       } else if (timeType.equals("YM")) {
                                                           view.setText(get2Time(date));
                                                       } else {
                                                           // 默认显示
                                                           view.setText(get3Time(date));
                                                       }
                                                   } else {
                                                       // 选择的是未来的时间 设定为当前时间
                                                       if (timeType.equals("ALL")) {
                                                           view.setText(getAllTime(newTime));

                                                       } else if (timeType.equals("YMD")) {
                                                           view.setText(get3Time(newTime));

                                                       } else if (timeType.equals("YM")) {
                                                           view.setText(get2Time(newTime));
                                                       } else {
                                                           view.setText(get3Time(newTime));
                                                       }
                                                   }
                                               }


                                           }
        );

        pickerView.show();


    }


    public static String getAllTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public static String get3Time(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String get2Time(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }
}
