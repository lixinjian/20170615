package com.ds365.commons.widget;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ds365.commons.utils.DateFormatUtils;

public class DateEditField extends LinearLayout {

	private EditText timeText;
	private String defaultDateFormat="yyyy-MM-dd";
	private int textColor;
	private float textSize;
	private ImageView image;
	private Context context;
	private DateSelectListener dateSelectListener;
	
	public void setDateSelectListener(DateSelectListener dateSelectListener){
		this.dateSelectListener = dateSelectListener;
	}
	
	public DateEditField(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DateTextAttrs);//TypedArray是一个数组容器 
		textSize = a.getDimension(R.styleable.DateTextAttrs_textSize,0);//防止在XML文件里没有定义，就加上了默认值30
		textColor = a.getColor(R.styleable.DateTextAttrs_textColor,getResources().getColor(R.color.black));//同上,这里的属性是:名字_属性名 
		a.recycle();//我的理解是：返回以前取回的属性，供以后使用。以前取回的可能就是textSize和textColor初始化的那段 
		this.context = context;
		initViews();
	}
	
	private void initViews(){
		setOrientation(HORIZONTAL);
		setBackgroundResource(R.drawable.dateedit_background_shape);
		timeText = new EditText(context);
		// 第一个参数为宽的设置，第二个参数为高的设置。
		timeText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1));
		// 设置字体大小
		if(textSize!=0)
		timeText.setTextSize(textSize);
		// 设置字体颜色
		timeText.setInputType(InputType.TYPE_CLASS_DATETIME);
		timeText.setTextColor(textColor);
		timeText.setBackgroundColor(Color.TRANSPARENT);
		timeText.setPadding(5,5,5,5);//left, top, right, bottom
		setTextCurrentTime();
		addView(timeText);
		image = new ImageView(context);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER_VERTICAL;
		image.setLayoutParams(layoutParams);
		image.setPadding(15,15,15,15);//left, top, right, bottom
		image.setImageResource(R.drawable.icon_goright);
		addView(image);
		image.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker datePicker, int year,
							int month, int dayOfMonth) {
						// Calendar月份是从0开始,所以month要加1
						if(month<9){
							if(dayOfMonth<10){
								timeText.setText(year + "-0" +(month + 1) + "-0"+ dayOfMonth);
							}else{
								timeText.setText(year + "-0" +(month + 1) + "-"+ dayOfMonth);
							}
						}else{
							if(dayOfMonth<10){
								timeText.setText(year + "-" + (month + 1) + "-0"+ dayOfMonth);
							}else{
								timeText.setText(year + "-" + (month + 1) + "-"+ dayOfMonth);
							}
						}
						if(dateSelectListener!=null){
							dateSelectListener.onDateSelect(getValue());
						}
					}
				};
				Dialog timeDialog= new MyDatePickerDialog(context, dateListener,
						calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH));
				timeDialog.show();
			}
		});
	}
	
	public EditText getEditText(){
		return timeText;
	}
	
	public void setValue(Date data){
		timeText.setText(dateToString(data));
	}

	public void setText(String time){
		timeText.setText(time);
	}
	
	public void setTextCurrentTime(){
		timeText.setText(DateFormatUtils.getCurrentTime(defaultDateFormat));
	}

	public Date getValue(){
		return stringToDate(timeText.getText().toString());
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
	public interface DateSelectListener {
        void onDateSelect(Date dateValue);
    }
}
