package com.ds365.commons.message;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.json.JacksonUtils;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.message.model.MessageDatebaseModel;
import com.ds365.commons.message.model.MessageModel;
import com.ds365.commons.message.model.MessageTypeEnum;
import com.ds365.commons.utils.ApplicationContextUtils;
import com.ds365.commons.utils.DateFormatUtils;
import com.ds365.commons.utils.L;
import com.ds365.commons.utils.SharedPreferencesUtils;

public class WebSocketConnection extends WebSocketClient {  
	//用来标记是否收到服务端的心跳返回
	private boolean heartbeatFlag = true;
	private WebSocketConnectListener connectListener;
	private WebSocketConnectHearbeatMessageListener webSocketConnectHearbeatMessageListener;
	private NoticeHandler noticeHandler;

	public void setConnectListener(WebSocketConnectListener connectListener) {
		this.connectListener = connectListener;
	}

	public boolean isHeartbeatFlag() {
		return heartbeatFlag;
	}

	public void setWebSocketConnectHearbeatMessageListener(
			WebSocketConnectHearbeatMessageListener webSocketConnectHearbeatMessageListener) {
		this.webSocketConnectHearbeatMessageListener = webSocketConnectHearbeatMessageListener;
	}

	public void setHeartbeatFlag(boolean heartbeatFlag) {
		this.heartbeatFlag = heartbeatFlag;
	}

	public WebSocketConnection(URI serverUri,Draft draft) {
		super(serverUri,draft);
		this.noticeHandler=new NoticeHandler(); 
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		L.i("websocket","开流--opened connection" );
		if(connectListener!=null){
			connectListener.onOpen();
		}
	}

	@Override
	public void onMessage(String result) {
		L.i("websocket","接收--received: " + result);
		if (result != null && !result.equals("")) {
			if(AppConstants.HEARTBEAT_MESSAGE.equals(result)){
				heartbeatFlag = true;
				if(webSocketConnectHearbeatMessageListener !=null)
				webSocketConnectHearbeatMessageListener.onReceiveHearbeatMessage();
				return;
			}
			if(AppGlobal.dbManager!=null){

				if(AppGlobal.messageReceivedListener!=null){
					JsonParser  jsonParser=AppGlobal.messageReceivedListener.getParser();
					//JsonParser<MessageModel> jsonParser = new JsonParser<MessageModel>(){};
					MessageModel message = (MessageModel)jsonParser.parseJSON(result);
					MessageDatebaseModel databaseMessage = messageChange(message);

					databaseMessage.set_id(AppGlobal.dbManager.insertMessage(databaseMessage));
					
					saveNotificationId();
					//收到消息给服务器的返回
					Map<String,Object> receiveMap = new HashMap<String, Object>();
					receiveMap.put("messageId",String.valueOf(databaseMessage.getId()));
					receiveMap.put("flag","1");
					String receiveJson = JacksonUtils.toJson(receiveMap);
					send(receiveJson);
					
					noticeHandler.notice(databaseMessage);
					AppGlobal.messageReceivedListener.onMessageReceived();
				}else{
					L.i("服务监听未初始化！");
				}
			}else{
				L.i("本地数据库未初始化！");
			}
		}else{
			L.i("服务器发送信息为空");
		}
	}  

	private void saveNotificationId() {
		// TODO Auto-generated method stub
		AppGlobal.notificationId=AppGlobal.notificationId+1;
		SharedPreferencesUtils.saveData(ApplicationContextUtils.getContext(),AppConstants.NOTIFICATIONID_KEY,AppGlobal.notificationId);
	}

	private MessageDatebaseModel messageChange(MessageModel message){
		MessageDatebaseModel messageDatebase = new MessageDatebaseModel();
		messageDatebase.setId(new Long(message.getId()).intValue());
		messageDatebase.setSendTime(DateFormatUtils.dateToStringForyyyyMMddHHmmss(message.getSendTime()));
		messageDatebase.setMessageType(message.getMessageType().getId());
		messageDatebase.setMessageCategory(message.getMessageCategory().getId());
		messageDatebase.setTitle(message.getTitle());
		messageDatebase.setContent(message.getContent());
		messageDatebase.setExpiryTime(DateFormatUtils.dateToStringForyyyyMMddHHmmss(message.getExpiryTime()));
		if(message.getMessageType().getId()==MessageTypeEnum.LINK.getId()){
			messageDatebase.setMessageFunctionType(message.getMessageFunctionType().getId());
			messageDatebase.setParamsMap(JacksonUtils.toJson(message.getParamsMap()));
		}
		return messageDatebase;
	}

	@Override
	public void onFragment(Framedata fragment) {
		L.i("websocket","片段--received fragment: " + new String( fragment.getPayloadData().array()));
	}  

	@Override
	public void onClose(int code, String reason, boolean remote) {
		L.i("websocket","关流--Connection closed by " + ( remote ? "remote peer" : "us"));
	}  

	@Override
	public void onError(Exception ex) {
		L.i("websocket","出错--Error Exception"+ex.toString());
		if(connectListener!=null){
			connectListener.onError();
		}
	}
	
	public interface WebSocketConnectListener {
		void onError();
		void onOpen();
	}

	public interface WebSocketConnectHearbeatMessageListener {
		void onReceiveHearbeatMessage();
	}
}  