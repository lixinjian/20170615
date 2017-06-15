package com.ds365.commons.message;

import com.ds365.commons.json.JsonParser;
import com.ds365.commons.message.model.MessageModel;

/** * @author yxz  
 * @date 创建时间：2016-9-30 下午6:30:58 
 * @version 1.0  
 */
public interface MessageReceivedListener<M extends MessageModel>/**<MessageCategoryType, MessageFunctionType>>*/ {
	void onMessageReceived();
	public JsonParser<M> getParser(); 
}
