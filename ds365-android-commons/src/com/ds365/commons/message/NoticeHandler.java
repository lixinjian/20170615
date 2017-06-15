package com.ds365.commons.message;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ds365.commons.AppGlobal;
import com.ds365.commons.R;
import com.ds365.commons.message.model.MessageDatebaseModel;
import com.ds365.commons.message.model.MessageTypeEnum;
import com.ds365.commons.utils.ApplicationContextUtils;

public class NoticeHandler {

	private Context context;
	private NotificationManager mNotificationManager;
	private Notification.Builder mBuilder;

	public  NoticeHandler(){
		context=ApplicationContextUtils.getContext();
		mNotificationManager = (NotificationManager)context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		mBuilder = new Notification.Builder(context);
	}

	public void  notice(MessageDatebaseModel databaseMessage){
		Intent intent;
		if(databaseMessage.getMessageType()==MessageTypeEnum.LINK.getId()){
			intent = new Intent(context,AppGlobal.messageFunctionTypeIntentMap.get(databaseMessage.getMessageFunctionType()));
		}else{
			intent = new Intent(context,AppGlobal.messageDetailClass);
		}
		intent.putExtra("message",databaseMessage);
		PendingIntent pendingIntent = PendingIntent.getActivity(context,AppGlobal.notificationId, intent, 0);

		mBuilder.setContentTitle(databaseMessage.getTitle())//设置通知栏标题
		.setContentText(databaseMessage.getContent())
		.setContentIntent(pendingIntent)//设置通知栏点击意图
		.setTicker(databaseMessage.getTitle()) //通知首次出现在通知栏，带上升动画效果的
		.setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
		.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
		.setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON

		Notification notification= mBuilder.build();
		mNotificationManager.notify(AppGlobal.notificationId, notification);//id是应用中通知的唯一标识
	}

}
