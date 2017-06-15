package com.ds365.commons.message.database;

/**
 * @author 杨晓振
 *         on 2016/12/21.
 *         消息操作类
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ds365.commons.message.model.MessageDatebaseModel;
import com.ds365.commons.utils.L;

public class DBManager {
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	private String tableName;

	public DBManager(Context context,String tableName) {
		helper = new DatabaseHelper(context,tableName);
		db = helper.getWritableDatabase();
		this.tableName = tableName;
	}
	//添加信息
	public int insertMessage(MessageDatebaseModel model) {
		db.beginTransaction();  //开始事务
		try {
			db.execSQL("INSERT INTO "+tableName+" VALUES(null,?,?,?,?,?,?,?,0,?,?)", 
					new Object[]{model.getId(),model.getMessageCategory(),
					model.getMessageType(),model.getTitle(),model.getSendTime(),model.getContent(),
					model.getMessageFunctionType(),model.getExpiryTime(),model.getParamsMap()});
			db.setTransactionSuccessful();  //设置事务成功完成
		} finally {
			db.endTransaction();    //结束事务
		}
		//返回最新数据的_id
		return getMessageForType().get(0).get_id();
	}
	
	//删除一条记录
	public void deleteMessage(int _id){
		db.beginTransaction();  //开始事务
		try {
			db.execSQL("DELETE FROM "+tableName+" WHERE _id = "+_id);
			db.setTransactionSuccessful();  //设置事务成功完成
		} finally {
			db.endTransaction();    //结束事务
		}
	}
	//删除所有记录
	public void deleteAllMessage(){
		db.beginTransaction();  //开始事务
		try {
			db.execSQL("DELETE FROM "+tableName);
			db.setTransactionSuccessful();  //设置事务成功完成
		} finally {
			db.endTransaction();    //结束事务
		}
	}
	//根据消息类型删除记录
	public void deleteMessageByMessageCategory(int messageCategory){
		db.beginTransaction();  //开始事务
		try {
			db.execSQL("DELETE FROM "+tableName+" WHERE messageCategory = "+messageCategory);
			db.setTransactionSuccessful();  //设置事务成功完成
		} finally {
			db.endTransaction();    //结束事务
		}
	}
	//根据消息类型和未读标志删除记录
	public void deleteMessageByMessageCategoryAndReadFlag(int messageCategory,boolean isRead){
		db.beginTransaction();  //开始事务
		try {
			int readFlag = 0;
			if(isRead){
				readFlag = 1;
			}
			db.execSQL("DELETE FROM "+tableName+" WHERE messageCategory = "+messageCategory+" AND readFlag = "+readFlag);
			db.setTransactionSuccessful();  //设置事务成功完成
		} finally {
			db.endTransaction();    //结束事务
		}
	}
	//修改信息已读状态
	public void updateMessageReadFlag(int _id){
		db.beginTransaction();  //开始事务
		try {
			db.execSQL("update "+tableName+" set readFlag = 1 where _id = "+_id);
			db.setTransactionSuccessful();  //设置事务成功完成
		} finally {
			db.endTransaction();    //结束事务
		}
	}
	
	//根据类型获取未读消息数量
	public int getNotReadCountForType(int messageCategory){
		List<MessageDatebaseModel> messages = getAllMessage(messageCategory,0,0);
		int count = 0;
		for(int i=0;i<messages.size();i++){
			if(messages.get(i).getReadFlag()==0){
				count++;
			}
		}
		return count;
	}
	//获取所有未读数量
	public int getAllNotReadCount(){
		List<MessageDatebaseModel> messages = getAllMessage(-1,0,0);
		int count = 0;
		for(int i=0;i<messages.size();i++){
			if(messages.get(i).getReadFlag()==0){
				count++;
			}
		}
		return count;
	}
	//根据消息类型分页查询已读和未读消息
	public List<MessageDatebaseModel> getMessagePageForType(int messageCategory,boolean isRead,int start,int limit){
		List<MessageDatebaseModel> allMessage = getAllMessage(messageCategory,start,limit);
		List<MessageDatebaseModel> message = new ArrayList<MessageDatebaseModel>();
		int readFlag = 0;
		if(isRead){
			readFlag = 1;
		}
		for(int i=0;i<allMessage.size();i++){
			if(allMessage.get(i).getReadFlag()==readFlag){
				message.add(allMessage.get(i));
			}
		}
		return message;
	}

	//根据消息类型分页查询消息
	public List<MessageDatebaseModel> getMessagePageForType(int messageCategory,int start,int limit){
		return getAllMessage(messageCategory,start,limit);
	}
	
	//根据消息类型获取所有消息
	private List<MessageDatebaseModel> getMessageForType(){
		return getAllMessage(-1,0,0);
	}
	//获取所有消息
	private List<MessageDatebaseModel> getAllMessage(int messageCategory,int start,int limit){
		ArrayList<MessageDatebaseModel> messages = new ArrayList<MessageDatebaseModel>();
		Cursor c;
		
		if(messageCategory==-1){
			c = db.rawQuery("SELECT * FROM "+tableName+" ORDER BY _id DESC", null);
		}else{
			if(limit == 0){
				c = db.rawQuery("SELECT * FROM "+tableName+" WHERE messageCategory = "+messageCategory+" ORDER BY _id DESC", null);
			}else{
				c = db.rawQuery("SELECT * FROM "+tableName+" WHERE messageCategory = "+messageCategory+" ORDER BY _id DESC LIMIT "+limit+" OFFSET "+start, null);
			}
		}
		
		try {
			while (c.moveToNext()) {
				MessageDatebaseModel message = new MessageDatebaseModel();
				message.set_id(c.getInt(c.getColumnIndex("_id")));
				message.setId(c.getInt(c.getColumnIndex("messageid")));
				message.setMessageCategory(c.getInt(c.getColumnIndex("messageCategory")));
				message.setMessageType(c.getInt(c.getColumnIndex("messageType")));
				message.setTitle(c.getString(c.getColumnIndex("title")));
				message.setSendTime(c.getString(c.getColumnIndex("sendTime")));
				message.setContent(c.getString(c.getColumnIndex("content")));
				message.setMessageFunctionType(c.getInt(c.getColumnIndex("messageFunctionType")));
				message.setReadFlag(c.getInt(c.getColumnIndex("readFlag")));
				message.setExpiryTime(c.getString(c.getColumnIndex("expiryTime")));
				message.setParamsMap(c.getString(c.getColumnIndex("paramsMap")));
				messages.add(message);
			}
		}catch(Exception e){
			L.e(e.toString());
		}finally{
			c.close();
		}
		return messages;
	}
	/**
	 * close database
	 */
	public void closeDB() {
		db.close();
	}
}
