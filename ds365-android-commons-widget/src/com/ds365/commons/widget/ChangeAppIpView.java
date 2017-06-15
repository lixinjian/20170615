package com.ds365.commons.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.utils.ChangeIPSharedPreference;

public class ChangeAppIpView extends LinearLayout {

	private Context context;
	private LayoutInflater inflater;
	private AlertDialog dialog;
	private NewIpSaveListener saveListener;
	private String defaultHost,defaultUpdateHost,defaultMessageHost;

	public void setIpSaveListener(NewIpSaveListener listener){
		this.saveListener = listener;
	}

	public ChangeAppIpView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initViews();
	}

	private void initViews(){
		View view = new View(context);
		// 第一个参数为宽的设置，第二个参数为高的设置。
		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		view.setPadding(30, 20,30,20);//left, top, right, bottom
		addView(view);
		setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(AppGlobal.activeFlag){
					AlertDialog.Builder builder = new AlertDialog.Builder(context);  
					inflater = LayoutInflater.from(context);  
					View layout = inflater.inflate(R.layout.changeip_dialog, null);//获取自定义布局  
					builder.setView(layout);  
					builder.setTitle(R.string.changeip);//设置标题内容
					final EditText host = (EditText) layout.findViewById(R.id.change_host);
					final EditText updateHost = (EditText) layout.findViewById(R.id.change_updatehost);
					final EditText messageHost = (EditText) layout.findViewById(R.id.change_messagehost);
					host.setText(defaultHost);
					updateHost.setText(defaultUpdateHost);
					messageHost.setText(defaultMessageHost);
					//确认按钮  
					builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {  

						@Override  
						public void onClick(DialogInterface arg0, int arg1) {
							defaultHost =  host.getEditableText().toString();
							defaultUpdateHost =  updateHost.getEditableText().toString();
							defaultMessageHost = messageHost.getEditableText().toString();
							ChangeIPSharedPreference.saveIP(context,defaultHost,defaultUpdateHost,defaultMessageHost);
							if(saveListener!=null){
								saveListener.onIpSave(defaultHost,defaultUpdateHost,defaultMessageHost);
							}
						}  
					});  
					//取消  
					builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  

						@Override  
						public void onClick(DialogInterface arg0, int arg1) {  
							dialog.dismiss(); 
						}  
					});   
					dialog = builder.create(); 
					dialog.show();  
				}
			}
		});
	}

	public void setAppDefaultIp(String host,String updateHost,String messageHost){
		this.defaultHost = host;
		this.defaultUpdateHost = updateHost;
		this.defaultMessageHost = messageHost;
	}

	public interface NewIpSaveListener {
		void onIpSave(String newHost,String newUpdateHost,String newMessageHost);
	}
}
