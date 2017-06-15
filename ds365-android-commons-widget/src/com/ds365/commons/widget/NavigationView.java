package com.ds365.commons.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds365.commons.utils.ActivityUtils;

/**
 * 头部导航栏View
 */
public class NavigationView extends LinearLayout implements OnClickListener {

	private ImageView back;
	private TextView title,right;
	private Activity context; 
	private HeadMenuClickListener rightListener,leftListener;
	
	public NavigationView(Context context) {
		super(context);
		initViews(context);
	}

	public void setRightListener(HeadMenuClickListener rightListener) {
		this.rightListener = rightListener;
	}
	
	public void setLeftListener(HeadMenuClickListener leftListener) {
		this.leftListener = leftListener;
	}
	
	public NavigationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}

	public void initViews(Context context) {
		this.context = (Activity) context;
		View container = View.inflate(context, R.layout.common_head, null);
		back = (ImageView) container.findViewById(R.id.common_head_left);
		title = (TextView) container.findViewById(R.id.common_head_title);
		right = (TextView) container.findViewById(R.id.common_head_right);
		back.setOnClickListener(this);
		right.setOnClickListener(this);
		addView(container);
	}
	/***设置标题头*/
    public void setTitle(String string){
    	if (title != null) {
    		title.setText(string);
    	}
    }
	
	public TextView getTitleView(){
		if(title != null){
			return title;
		}
		return null;
	}
	
    public void setRightText(String rightText){
    	setRightText(rightText,true);
    }
    
    public void setRightText(String rightText,boolean clickable){
    	right.setText(rightText);
    	right.setVisibility(View.VISIBLE);
    	if(!clickable){
    	  	right.setClickable(false);
    	  	right.setTextColor(getResources().getColor(R.color.gray_line));
    	}
    }
	
	public void setLeft(int id,boolean isShow){
		if (back != null) {
			back.setImageResource(id);
			back.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
		}
	}

	public void setAlphaBackground(int alphaValue){
		Drawable d = getBackground();
		if(d != null)
		d.setAlpha(alphaValue);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.common_head_left){
			if(leftListener!=null){
				leftListener.onRightClick();
			}
			ActivityUtils.remove(context);
			context.finish();
		}else if(v.getId()==R.id.common_head_right){
			if (rightListener != null) {
				rightListener.onRightClick();
			}
		}
	}
	
	public interface HeadMenuClickListener {
		void onRightClick();
	}
}
