package com.ds365.commons.message;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.drafts.Draft_17;

import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.message.WebSocketConnection.WebSocketConnectHearbeatMessageListener;
import com.ds365.commons.message.WebSocketConnection.WebSocketConnectListener;
import com.ds365.commons.utils.L;

public class WebSocketService {

	private  WebSocketConnection connection;
	//长链接发生报错重连时间
	private int reConnectTime = 1000*10;
	//长链接发生报错重连次数
	private int reConnectCount;
	private Object connectMonitor= new Object();//连接socket，的监视器

	public void setWebSocketConnectHearbeatMessageListener(
			WebSocketConnectHearbeatMessageListener webSocketConnectHearbeatMessageListener) {
		if(connection!=null){
			connection.setWebSocketConnectHearbeatMessageListener(webSocketConnectHearbeatMessageListener);
		}
	}

	public WebSocketService startWebSocket(){
		reConnectCount = AppConstants.WEBSOCKET_RECONNECT_COUNT;
		synchronized (connectMonitor) {
			try{
				connection=connect();
				while (reConnectCount>=0) {
					try {
						connectMonitor.wait(reConnectTime);
					} catch (InterruptedException e) {
						L.e("websocket",e.toString());
					}
					if(connection!=null&&connection.isOpen()){
						L.i("链接成功");
						return this;
					}else{
						closeConnection();
						connection=connect();
					}
					L.i("websocket","第"+reConnectCount+"次重连");
					reConnectCount--;
				}
			}catch(Exception e){
				L.e("websocket",e.toString());
			}
		}
		return null;
	}
	
	public boolean isOpen(){
		if(connection!=null){
			return connection.isOpen();
		}else{
			return false;
		}
	}
	
	public void send(String sendMessage){
		if(connection!=null){
			try {
				connection.send(sendMessage);
			} catch (Exception e) {
				L.e("无法成功发送消息！"+e.toString());
				return;
			}
		}
	}

	public void setHeartbeatFlag(boolean heartbeatFlag){
		if(connection!=null){
			connection.setHeartbeatFlag(heartbeatFlag);
		}
	}

	public boolean isHeartbeatFlag(){
		if(connection!=null){
			return connection.isHeartbeatFlag();
		}else{
			return false;
		}
	}

	private WebSocketConnection connect(){
		WebSocketConnection webSocket=null;
		L.i(AppGlobal.appServiceUrl);
		try {
			webSocket = new WebSocketConnection(new URI(AppGlobal.appServiceUrl),new Draft_17());
			webSocket.setConnectListener(new WebSocketConnectListener() {

				@Override
				public void onError() {
					synchronized (connectMonitor) {
						connectMonitor.notify();
					}
				}

				@Override
				public void onOpen() {
					synchronized (connectMonitor) {
						connectMonitor.notify();
					}
				}
			});
			webSocket.connect();
		} catch (URISyntaxException e) {
			closeConnection();
			L.e("URL不合法，无法转化为URI");
		}catch(Exception e){
			closeConnection();
			L.e("连接失败："+e.getMessage());
		}
		return webSocket;
	}

	public  void closeConnection(){
		if(connection!=null){
			try{
				connection.close();
			}catch(Exception e){

			}finally{
				connection=null;
			}
		}
	}

}
