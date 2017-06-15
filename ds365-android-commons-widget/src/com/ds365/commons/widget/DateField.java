package com.ds365.commons.widget;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds365.commons.utils.DateFormatUtils;

public class DateField extends LinearLayout {

	private TextView timeText;
	private String dateFormatForyyyyMMdd="yyyy-MM-dd";
	private String dateFormatForyyyyMMddHHmmss="yyyy-MM-dd HH:mm:ss";
	private String defaultDateFormat=dateFormatForyyyyMMddHHmmss;
	private Date dateTime;
	private int textColor;
	private float textSize;
	
	public DateField(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DateTextAttrs);//TypedArray是一个数组容器 
		int dataStyle = a.getInt(R.styleable.DateTextAttrs_dateformat, 0);
		textSize = a.getDimension(R.styleable.DateTextAttrs_textSize,0);//防止在XML文件里没有定义，就加上了默认值30
		textColor = a.getColor(R.styleable.DateTextAttrs_textColor,getResources().getColor(R.color.black));//同上,这里的属性是:名字_属性名 
		if(dataStyle==0){
			defaultDateFormat=dateFormatForyyyyMMddHHmmss;
		}else{
			defaultDateFormat=dateFormatForyyyyMMdd;
		}
		a.recycle();//我的理解是：返回以前取回的属性，供以后使用。以前取回的可能就是textSize和textColor初始化的那段 
		initViews(context);
	}
	
	private void initViews(Context context){
		timeText = new TextView(context);
		// 第一个参数为宽的设置，第二个参数为高的设置。
		timeText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		// 设置字体大小
		if(textSize!=0)
		timeText.setTextSize(textSize);
		// 设置字体颜色
		timeText.setTextColor(textColor);
		timeText.setPadding(1, 0, 0, 0);//left, top, right, bottom
		setTextCurrentTime();
		addView(timeText);
	}
	
	public void setValue(Date data){
		dateTime = data;
		timeText.setText(dateToString(data));
	}
	
//	public void setValueForyyyyMMdd(Date data){
//		defaultDateFormat=dateFormatForyyyyMMdd;
//		dateTime = data;
//		timeText.setText(dateToString(data));
//	}
//	
//	public void setValue(Date data,String format){
//		defaultDateFormat=format;
//		dateTime = data;
//		timeText.setText(dateToString(data));
//	}
	
	public void setTextCurrentTime(){
		String time = DateFormatUtils.getCurrentTime(defaultDateFormat);
		timeText.setText(time);
		dateTime=stringToDate(time);
	}

	public Date getValue(){
		return dateTime;
	}

	private String dateToString(Date data) {
		if(data==null)
			return "";
        return new SimpleDateFormat(defaultDateFormat).format(data);
    }

	private Date stringToDate(String time){
		DateFormat sdf = new SimpleDateFormat(defaultDateFormat);
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			return null;
		}
	}
}
