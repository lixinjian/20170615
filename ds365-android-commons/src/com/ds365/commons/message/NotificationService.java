package com.ds365.commons.message;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.message.WebSocketConnection.WebSocketConnectHearbeatMessageListener;
import com.ds365.commons.utils.L;

public class NotificationService extends Service {
	//心跳时间
	private int heartbeatTime = 1000*60*10;
	//心跳重连次数
	private Integer heartbeatCount=3;
	//服务开启后开启心跳时间
	private int connectAfterTime = 1000*31;
	//心跳重发时间
	private int heartbeatConnectTime = 1000*5;
	//心跳的定时器
	private Timer heartbeatTimer = new Timer();
	//心跳监听
	private Object hearbeatMonitor = new Object();
	private WebSocketService webSocketService;

	@Override 
	public void onCreate() {  
		super.onCreate();  
		webSocketService = new WebSocketService();
	}

	@Override
	public int onStartCommand(Intent intent, int flags,int startId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				webSocketService = webSocketService.startWebSocket();
			}
		}).start();
		
		heartbeatTimer.schedule(new TimerTask() {  

			@Override  
			public void run() {
				if(AppGlobal.checkUserLoginListener!=null){
					AppGlobal.checkUserLoginListener.checkUserLogin(new CheckUserLoginResultListener(){

						@Override
						public void onCheckOk() {
							if(webSocketService==null||!webSocketService.isOpen()){
								L.i("心跳重连");
								if(webSocketService!=null){
									webSocketService.closeConnection();
								}
								webSocketService = new WebSocketService();
								webSocketService = webSocketService.startWebSocket();
							}
							if(webSocketService!=null){
								webSocketService.setWebSocketConnectHearbeatMessageListener(new WebSocketConnectHearbeatMessageListener() {
									@Override
									public void onReceiveHearbeatMessage() {
										synchronized (hearbeatMonitor) {
											hearbeatMonitor.notify();
										}
									}
								});
								for(int i=0;i<heartbeatCount;i++){
									L.i("向服务器发送心跳请求第"+i+"次");
									synchronized (hearbeatMonitor) {
										try {
											webSocketService.setHeartbeatFlag(false);
											webSocketService.send(AppConstants.HEARTBEAT_MESSAGE);
											hearbeatMonitor.wait(heartbeatConnectTime);
										} catch (InterruptedException e) {
											L.e("websocket",e.toString());
										}
										if(webSocketService.isHeartbeatFlag()){
											L.i("心跳响应成功");
											break;
										}
									}
								}
								if(!webSocketService.isHeartbeatFlag()){
									webSocketService.closeConnection();
								}
							}
						}

						@Override
						public void onCheckFail() {
							try{
								heartbeatTimer.cancel();
							}finally{
								if(webSocketService!=null){
									webSocketService.closeConnection();;
								}
							}
						}
					});
				}else{
					throw new RuntimeException("请在心跳前添加用户检查登录！");
				}
			}  
		},connectAfterTime,heartbeatTime);//服务启动后5s开始心跳请求

		return START_STICKY;
	}

	@Override 
	public void onDestroy() { 
		try{
			heartbeatTimer.cancel();
		}finally{
			if(webSocketService!=null){
				webSocketService.closeConnection();;
			}
		}
		super.onDestroy();
	}  

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
