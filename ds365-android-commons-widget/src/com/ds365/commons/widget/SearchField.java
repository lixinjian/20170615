package com.ds365.commons.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SearchField extends LinearLayout {

	private EditText editText;
	private int textColor;
	private float textSize;
	private ImageView clearImage;
	private EditTextClearListener editTextClearListener;

	public void setEditTextClearListener(EditTextClearListener editTextClearListener) {
		this.editTextClearListener = editTextClearListener;
	}

	public SearchField(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DateTextAttrs);//TypedArray是一个数组容器 
		textSize = a.getDimension(R.styleable.DateTextAttrs_textSize,0);
		textColor = a.getColor(R.styleable.DateTextAttrs_textColor,getResources().getColor(R.color.black));//同上,这里的属性是:名字_属性名 
		a.recycle();//我的理解是：返回以前取回的属性，供以后使用。以前取回的可能就是textSize和textColor初始化的那段 
		initViews(context);
	}
	
	private void initViews(Context context){
		setBackgroundResource(R.drawable.dateedit_background_shape);
		setOrientation(HORIZONTAL);
		editText = new EditText(context);
		// 设置字体大小
		if(textSize!=0)
			editText.setTextSize(textSize);
		// 设置字体颜色
		editText.setTextColor(textColor);
		editText.setSingleLine();
		editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
		editText.setBackgroundColor(getResources().getColor(R.color.transparent));

		LayoutParams editLayoutParams= new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		editLayoutParams.weight = 1;
		editLayoutParams.gravity = Gravity.CENTER_VERTICAL;
		addView(editText,editLayoutParams);
		
		clearImage = new ImageView(context);
		clearImage.setImageResource(R.drawable.icon_searchclear);
		clearImage.setPadding(5,0,5,0);
		clearImage.setVisibility(View.INVISIBLE);
		LayoutParams imageLayoutParams= new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		imageLayoutParams.gravity = Gravity.CENTER_VERTICAL;
		addView(clearImage,imageLayoutParams);
		editText.addTextChangedListener(new TextWatcher() {
			
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
				if(s.length()>0){
					clearImage.setVisibility(View.VISIBLE);
				}else{
					clearImage.setVisibility(View.INVISIBLE);
					if(editTextClearListener!=null){
						editTextClearListener.onEditTextClear();
					}
				}
			}
		});
		clearImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editText.setText("");
				if(editTextClearListener!=null){
					editTextClearListener.onEditTextClear();
				}
			}
		});
	}
	
	public String getValue(){
		return editText.getText().toString();
	}
	
	public void setValue(String value){
		editText.setText(value);
	}
	
	public EditText getEditView(){
		return editText;
	}
	
	public interface EditTextClearListener {
		void onEditTextClear();
	}
}
