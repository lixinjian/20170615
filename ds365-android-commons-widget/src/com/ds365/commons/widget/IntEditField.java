package com.ds365.commons.widget;

import com.ds365.commons.utils.T;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

public class IntEditField extends LinearLayout {

	private EditText timeText;
	private int textColor;
	private float textSize;
	private Context context;
	
	public IntEditField(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
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
		timeText.setInputType(InputType.TYPE_CLASS_NUMBER);
		// 设置字体颜色
		timeText.setTextColor(textColor);
		timeText.setBackgroundResource(R.drawable.dateedit_background_shape);
		timeText.setPadding(3, 0, 0, 0);//left, top, right, bottom
		setValue(0);
		addView(timeText);
	}
	
	public void setMaxValueAndTextChangeListener(final int maxValue,final String onOverstepMaxMessage,final TextChangeListener textChangeListener){
		timeText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				int tempMinUnitQty;
				if(s.length()>0&&!s.equals("")){
					tempMinUnitQty = Integer.valueOf(s.toString());
				}else{
					tempMinUnitQty =0;
				}
				//当maxValue为0时则不判断最大数量
				if(maxValue!=0&&tempMinUnitQty>=maxValue){
					T.showShort(context,onOverstepMaxMessage);
					setValue(0);
				}else{
					if(textChangeListener!=null){
						textChangeListener.onEditTextChange(tempMinUnitQty);
					}
				}
			}
		});
	}
	
	public EditText getEditText(){
		return timeText;
	}
	
	public interface TextChangeListener {
		void onEditTextChange(int value);
	}
	
	public void setValue(int number){
		timeText.setText(String.valueOf(number));
	}
	
	public int getValue(){
		String str = timeText.getText().toString();
		if("".equals(str)){
			return 0;
		}
		return Integer.valueOf(str);
	}
}
