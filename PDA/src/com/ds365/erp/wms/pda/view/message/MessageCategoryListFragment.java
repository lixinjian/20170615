package com.ds365.erp.wms.pda.view.message;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.message.model.MessageCategoryModel;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.service.enums.EnumService;
import com.ds365.erp.wms.pda.view.common.fragment.BaseFragment;
import com.ds365.erp.wms.pda.view.message.activity.MessageListActivity;
import com.ds365.erp.wms.pda.view.message.adapter.MessageCategoryAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MessageCategoryListFragment extends BaseFragment {
	private Context context;
	private PullToRefreshListView listView;
	private EnumService enumService= new EnumService();
	private List<MessageCategoryModel<Integer>> messageCategoryList = new ArrayList<MessageCategoryModel<Integer>>();
	private MessageCategoryAdapter adapter;
	
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	@Override
	public void onStart() {
		super.onStart();
		resetBadgeCount();
	}
	
	@Override
	protected void initFragmentRequestNet() {

	}

	@Override
	protected View initFragmentView(LayoutInflater inflater) {
		view=inflater.inflate(R.layout.message_category_list_fragment, null);
		context = getActivity();
		
		listView = (PullToRefreshListView) findViewById(R.id.messageCategoryListFragment_listview);
        
		adapter = new MessageCategoryAdapter(context, messageCategoryList);
		listView.setAdapter(adapter);
		
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				getData();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				IntentUtils.startActivityForSeria(context,MessageListActivity.class, SER_KEY,messageCategoryList.get(position-1), null);
			}
		});
		getData();
		
		return view;
		
	}

	@Override
	protected void initFragmentChildView(View view) {

	}

	@Override
	protected void initFragmentData(Bundle savedInstanceState) {

	}

	//重新设置未读消息数
	public void resetBadgeCount(){
		for(int i=0;i<messageCategoryList.size();i++){
			messageCategoryList.get(i).setNotReadCount(AppGlobal.dbManager.getNotReadCountForType(messageCategoryList.get(i).getId()));
		}
		adapter.notifyDataSetChanged();
	}
	
	private void getData(){
		messageCategoryList.clear();
		adapter.notifyDataSetChanged();
		enumService.searchMessageCategoryForPdaEnum(new AbstractServiceCallBack<List<MessageCategoryModel<Integer>>>(context) {

			@Override
			public void doSuccess(List<MessageCategoryModel<Integer>> data) {
				if (null != data && data.size() > 0) {
					for(int i=0;i<data.size();i++){
						data.get(i).setNotReadCount(AppGlobal.dbManager.getNotReadCountForType(data.get(i).getId()));
					}
					messageCategoryList.addAll(data);
					adapter.notifyDataSetChanged();
				}
				listView.onRefreshComplete();
			}

			@Override
			public void doFaile(String str) {
				super.doFaile(str);
				listView.onRefreshComplete();
			}
		});
	}
	
}