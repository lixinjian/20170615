package com.ds365.commons.widget;

import java.util.List;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class CommonTitleTab extends LinearLayout {

	private int textColor,textSelectColor,textBackground,textSelectBackground;
	private float textSize;
	private Context context;
	private List<String> list;
	private SelectChangeListener selectChangeListener;
	private RadioGroup radioGroup;
	private WindowManager windowManager;
	/**
	 * 设置监听一定要在设置数据之前，不然会漏掉默认选择的监听，第一项为0
	 */
	public void setSelectChangeListener(SelectChangeListener selectChangeListener){
		this.selectChangeListener = selectChangeListener;
	}

	public CommonTitleTab(Context context, AttributeSet attrs) {
		super(context, attrs);
		windowManager = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DateTextAttrs);//TypedArray是一个数组容器 
		textSize = a.getDimension(R.styleable.DateTextAttrs_textSize,0);
		textColor = a.getColor(R.styleable.DateTextAttrs_textColor,getResources().getColor(R.color.black));//同上,这里的属性是:名字_属性名 
		textSelectColor = a.getColor(R.styleable.DateTextAttrs_selectTextColor,getResources().getColor(R.color.white));
		textBackground = a.getColor(R.styleable.DateTextAttrs_textBackground,getResources().getColor(R.color.white));
		textSelectBackground = a.getColor(R.styleable.DateTextAttrs_selectTextBackground,getResources().getColor(R.color.red));
		a.recycle();//我的理解是：返回以前取回的属性，供以后使用。以前取回的可能就是textSize和textColor初始化的那段 
		this.context = context;
	}

	private void initViews(){
		setOrientation(HORIZONTAL);
		radioGroup = new RadioGroup(context);
		radioGroup.setOrientation(HORIZONTAL);
		radioGroup.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for(int i = 0 ;i<group.getChildCount();i++){
					RadioButton  radioBtn = (RadioButton) radioGroup.getChildAt(i);
					if(checkedId == radioBtn.getId()){
						radioBtn.setBackgroundColor(textSelectBackground);
						if(selectChangeListener!=null){
							selectChangeListener.onItemSelect(i,radioBtn);
						}
					}else{
						radioBtn.setBackgroundColor(textBackground);
					}
				}

			}
		});
		for(int i=0;i<list.size();i++){
			RadioButton  radioBtn = new RadioButton(context);
			// 第一个参数为宽的设置，第二个参数为高的设置。
			radioBtn.setLayoutParams(new RadioGroup.LayoutParams(radioGroup.getWidth()/list.size(),LayoutParams.WRAP_CONTENT,1));
//			radioBtn.setLayoutParams(new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1));
			// 设置字体大小
			if(textSize!=0)
				radioBtn.setTextSize(textSize);
			
//			changeViewSize(radioGroup, windowManager.getDefaultDisplay().getWidth(), windowManager.getDefaultDisplay().getHeight());
			// 设置字体颜色
			radioBtn.setTextColor(createColorStateList(textSelectColor,textColor));
			radioBtn.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
			radioBtn.setText(list.get(i));
			//设置背景颜色
			radioBtn.setBackgroundColor(textBackground);
			radioBtn.setGravity(Gravity.CENTER);
			radioBtn.setPadding(0, 20, 0, 20);//left, top, right, bottom
			radioGroup.addView(radioBtn);
			if(i==0){
				radioBtn.setChecked(true);
			}
		}
		addView(radioGroup);
	}


	
	
	
	
	
	
	
	/*//遍历设置字体  
	public static void changeViewSize(ViewGroup viewGroup,int screenWidth,int screenHeight) {//传入Activity顶层Layout,屏幕宽,屏幕高  
	        int adjustFontSize = adjustFontSize(screenWidth,screenHeight); 
	        for(int i = 0; i<viewGroup.getChildCount(); i++ ){ 
	            View v = viewGroup.getChildAt(i); 
	            if(v instanceof ViewGroup){ 
	                changeViewSize((ViewGroup)v,screenWidth,screenHeight); 
	            }else if(v instanceof Button){//按钮加大这个一定要放在TextView上面，因为Button也继承了TextView  
	                ( (Button)v ).setTextSize(adjustFontSize+2); 
	            }else if(v instanceof TextView){ 
	                if(v.getId()== R.id.title_msg){//顶部标题  
	                    ( (TextView)v ).setTextSize(adjustFontSize+4); 
	                }else{ 
	                    ( (TextView)v ).setTextSize(adjustFontSize); 
	                } 
	            } 
	        } 
	    } 

	 
	//获取字体大小  
	public static int adjustFontSize(int screenWidth, int screenHeight) { 
	        screenWidth=screenWidth>screenHeight?screenWidth:screenHeight; 
	        *//**
	         * 1. 在视图的 onsizechanged里获取视图宽度，一般情况下默认宽度是320，所以计算一个缩放比率
	            rate = (float) w/320   w是实际宽度
	           2.然后在设置字体尺寸时 paint.setTextSize((int)(8*rate));   8是在分辨率宽为320 下需要设置的字体大小
	            实际字体大小 = 默认字体大小 x  rate
	         *//* 
	        int rate = (int)(5*(float) screenWidth/320); //我自己测试这个倍数比较适合，当然你可以测试后再修改  
	        return rate<15?15:rate; //字体太小也不好看的  
	}*/ 
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 对TextView设置不同状态时其文字颜色。 */  
	private ColorStateList createColorStateList(int checked, int unchecked) {  
		int[] colors = new int[] { checked, unchecked};  
		int[][] states = new int[2][];  
		states[0] = new int[] {android.R.attr.state_checked};  
		states[1] = new int[] {};
		ColorStateList colorList = new ColorStateList(states, colors);  
		return colorList;  
	}

	/**
	 * 设置数据
	 * @param list
	 */
	public void setData(List<String> list){
		this.list = list;
		initViews();
	}

	public interface SelectChangeListener {
		void onItemSelect(int selectValue,RadioButton selectRadioButton);
	}
}
