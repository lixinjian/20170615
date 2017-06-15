package com.ds365.erp.wms.pda.view.message.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.message.model.MessageCategoryModel;
import com.ds365.commons.message.model.MessageDatebaseModel;
import com.ds365.commons.message.model.MessageTypeEnum;
import com.ds365.commons.utils.DateFormatUtils;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.ScreenUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.CommonTitleTab;
import com.ds365.commons.widget.CommonTitleTab.SelectChangeListener;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.message.MessageCategoryListFragment;
import com.ds365.erp.wms.pda.view.message.adapter.MessageAdapter;
import com.ds365.swipelistview.SwipeMenu;
import com.ds365.swipelistview.SwipeMenuCreator;
import com.ds365.swipelistview.SwipeMenuItem;
import com.ds365.swipelistview.SwipeMenuListView;
import com.ds365.swipelistview.SwipeMenuListView.OnMenuItemClickListener;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;

/**
 * 说明 ：消息列表 
 */
public class MessageListActivity extends BasePdaPageActivity {

	private MessageCategoryModel<Integer> messageCategory;
	private SwipeMenuListView listView;
	private CommonTitleTab titleTab;
	private List<String> titleTabList = new ArrayList<String>();
	private ImageView deleteButton;
	
	private List<MessageDatebaseModel> list;
	private List<MessageDatebaseModel> totalList = new ArrayList<MessageDatebaseModel>();
	private MessageAdapter adapter;
	
	private int select = 0;
	private static final String NOT_READ_TAB_CODE="notRead";
	private static final String READ_TAB_CODE="read";
	private  String[]  tabCodes={NOT_READ_TAB_CODE,READ_TAB_CODE};
	
	@Override
	protected void initNavigation() {
		messageCategory =  (MessageCategoryModel<Integer>) getIntent().getSerializableExtra(MessageCategoryListFragment.SER_KEY);
		initHeadView(R.id.messagelist_title, messageCategory.getName());
	}

	@Override
	protected int getContentViewId() {
		return R.layout.message_message_list;
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		setFirstPage();
		onTabChange();
	}

	@Override
	protected void initActivityView() {
		deleteButton = (ImageView) findViewById(R.id.messagelist_delete_button);
		listView = (SwipeMenuListView) findViewById(R.id.messagelist_listview);
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		
		titleTab = (CommonTitleTab) findViewById(R.id.messagelist_titleTab);
		titleTabList.add("未读");
		titleTabList.add("已读");
		
		adapter = new MessageAdapter(context, totalList);
		listView.setAdapter(adapter);
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				SwipeMenuItem openItem = new SwipeMenuItem(context);
				openItem.setBackground(new ColorDrawable(Color.rgb(221, 39, 39)));
				openItem.setWidth(ScreenUtils.dip2px(context, 90));
				openItem.setTitle(R.string.delete);
				openItem.setTitleSize(18);
				openItem.setTitleColor(Color.WHITE);
				menu.addMenuItem(openItem);
			}
		};
		listView.setMenuCreator(creator);

	}

	@Override
	protected void setListener() {

		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (tabCodes[select].equals(NOT_READ_TAB_CODE)) {
					DialogUtils.createConfirmDialog(context, getString(R.string.dialog_delete_title)
							, getString(R.string.dialog_delete_all_not_read), true, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									//清空未读消息
									AppGlobal.dbManager.deleteMessageByMessageCategoryAndReadFlag(messageCategory.getId(), false);
									getMessageData(AppConstants.CLEAR_LISTVIEW_YES,false);
								}
							});
				}else if (tabCodes[select].equals(READ_TAB_CODE)) {
					DialogUtils.createConfirmDialog(context, getString(R.string.dialog_delete_title)
							, getString(R.string.dialog_delete_all_read), true, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									//清空已读消息
									AppGlobal.dbManager.deleteMessageByMessageCategoryAndReadFlag(messageCategory.getId(),true);
									getMessageData(AppConstants.CLEAR_LISTVIEW_YES,true);
								}
							});
				}
			}
		});
		
		titleTab.setSelectChangeListener(new SelectChangeListener() {
			
			@Override
			public void onItemSelect(int selectValue, RadioButton selectRadioButton) {
				select = selectValue;
				onTabChange();
			}
		});
		titleTab.setData(titleTabList);
		
		listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu,int index) {
				AppGlobal.dbManager.deleteMessage(totalList.get(position).get_id());
				//查询未读消息
				if (tabCodes[select].equals(NOT_READ_TAB_CODE)) {
					getMessageData(PdaConstants.CLEAR_LISTVIEW_YES,false);
					//查询已读消息
				}else if (tabCodes[select].equals(READ_TAB_CODE)) {
					getMessageData(PdaConstants.CLEAR_LISTVIEW_YES,true);
				}
				return false;
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				if(DateFormatUtils.stringToDateForyyyyMMddHHmmss(list.get(position-1).getExpiryTime()).getTime()<System.currentTimeMillis()){
					T.showShort(context,"当前消息已过期！");
				}else{
					MessageDatebaseModel message = totalList.get(position-1);
					if(message.getMessageType()==MessageTypeEnum.LINK.getId()){
						IntentUtils.startActivityForSeria(context,AppGlobal.messageFunctionTypeIntentMap.get(message.getMessageFunctionType()),"message",message,null);
					}else{
						IntentUtils.startActivityForSeria(context,MessageDetailActivity.class,PdaConstants.MESSAGE_SER_KEY,message,null);
					}
				}
			}
		});
	}
	
	private void onTabChange(){
		//查询未读消息
		if (tabCodes[select].equals(NOT_READ_TAB_CODE)) {
			getMessageData(PdaConstants.CLEAR_LISTVIEW_YES,false);
		//查询已读消息
		}else if (tabCodes[select].equals(READ_TAB_CODE)) {
			getMessageData(PdaConstants.CLEAR_LISTVIEW_YES,true);
		}
	}
	
	/**
	 * 获取消息列表
	 */
	private void getMessageData(int isClearListView,boolean isRead) {
		if(isClearListView==AppConstants.CLEAR_LISTVIEW_YES){
			totalList.clear();
			adapter.notifyDataSetChanged();
		}
//		list=AppGlobal.dbManager.getMessagePageForType(messageCategory.getId(),isRead,start,PdaConstants.LIMIT);
		list=AppGlobal.dbManager.getMessagePageForType(messageCategory.getId(),isRead,start,0);
		if (list!=null&&list.size() > 0) {
			totalList.addAll(list);
			adapter.notifyDataSetChanged();
		} else {
			T.showShort(context,"没有更多数据了");
		}
	}
}
