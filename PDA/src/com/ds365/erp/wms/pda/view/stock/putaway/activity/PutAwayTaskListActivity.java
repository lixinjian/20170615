package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskQueryParamModel;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutAwayTaskListAdapter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
/**
 * 
 * @author LiXinjian
 * @date 2016年6月13日.
 *	上架业务  - 选择上架任务单
 */
public class PutAwayTaskListActivity extends BasePdaListActivity{
	private List<PutAwayTaskModel> arrayList = new ArrayList<PutAwayTaskModel>();
	private ListView listView;
	private PutAwayTaskListAdapter adapter;
	
	private PutawayTaskService putawayTaskService = new PutawayTaskService();
	private PutawayTaskQueryParamModel putawayTaskQueryParamModel = new PutawayTaskQueryParamModel();
	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_query;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		getData();
	}
	
	@Override
	protected void initActivityView() {
		listView = (ListView) findViewById(R.id.putawaytaskquery_lv);
		adapter = new PutAwayTaskListAdapter(context, arrayList);
	}
	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawaytaskquery_headerview, R.string.title_putaway_query);
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = getIntent();
				intent.putExtra("skuName", arrayList.get(position).getSku().getName());
				intent.putExtra("skuId", arrayList.get(position).getSku().getId());
	    		intent.putExtra("specName", arrayList.get(position).getSpec());
	    		intent.putExtra("qty", arrayList.get(position).getQty());
//	    		intent.putExtra("putawayTypeId", arrayList.get(position).getPutawaytype().getId());  // TODO: 后台为null
	    		intent.putExtra("storeId", arrayList.get(position).getStore().getId());
	    		intent.putExtra("shelfId", arrayList.get(position).getShelf().getId());
	    		intent.putExtra("relativeBillId", arrayList.get(position).getRelativeBillId());
	    		intent.putExtra("relativedDetailId", arrayList.get(position).getRelativeDetailId());
	    		intent.putExtra("sysBatchNo", arrayList.get(position).getSysBatchNo());//批次
	    		
	    		PutAwayTaskListActivity.this.setResult(RESULT_OK, intent);
	    		PutAwayTaskListActivity.this.finish();
			}
		});

//		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {
//				
//				SignOutDialogUtils.getDialog(PutAwayTaskListActivity.this, R.string.dialog_delete_title, R.string.dialog_delete_putawaytask, new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						SignOutDialogUtils.dismissDialog();
//						arrayList.remove(position);
//						adapter.notifyDataSetChanged();
//					}
//				}, new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						SignOutDialogUtils.dismissDialog();
//						
//					}
//				});
////				此处返回true,就不会触发onItemClick方法.否则,反之!
//				return true;
//			}
//		});
	}
	
	private void getData() {
		arrayList.clear();
		
		putawayTaskService.searchPagePutawayTaskByParamsForPut(putawayTaskQueryParamModel, new AbstractServiceCallBack<List<PutAwayTaskModel>>(context) {
			
			@Override
			public void doSuccess(List<PutAwayTaskModel> result) {
				List<PutAwayTaskModel> list = result;
				arrayList.addAll(list);
				listView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		});
	}
}
