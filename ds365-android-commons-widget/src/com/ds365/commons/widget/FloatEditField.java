package com.ds365.commons.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

public class FloatEditField extends LinearLayout {

	private EditText timeText;
	private int textColor;
	private float textSize;
	
	public FloatEditField(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DateTextAttrs);//TypedArray是一个数组容器 
		textSize = a.getDimension(R.styleable.DateTextAttrs_textSize,0);
		textColor = a.getColor(R.styleable.DateTextAttrs_textColor,getResources().getColor(R.color.black));//同上,这里的属性是:名字_属性名 
		a.recycle();//我的理解是：返回以前取回的属性，供以后使用。以前取回的可能就是textSize和textColor初始化的那段 
		initViews(context);
	}
	
	private void initViews(Context context){
		timeText = new EditText(context);
		// 第一个参数为宽的设置，第二个参数为高的设置。
		timeText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		// 设置字体大小
		if(textSize!=0)
		timeText.setTextSize(textSize);
		// 设置输入类型
		timeText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
		// 设置字体颜色
		timeText.setTextColor(textColor);
		timeText.setBackgroundResource(R.drawable.dateedit_background_shape);
		timeText.setPadding(3, 0, 0, 0);//left, top, right, bottom
		addView(timeText);
	}
	
	public void setValue(float number){
		timeText.setText(String.valueOf(number));
	}
	
	public float getValue(){
		return Float.valueOf(timeText.getText().toString());
	}
}
