package com.ds365.erp.wms.pda.view.stock.pick.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.service.pick.PickService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBatchDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 拣货下架 - 商品批次明细 	查看界面
 * @author lxj
 *
 */
public class PickBatchDetailActivity extends BasePdaPageActivity {
	private List<PickBatchDetailModel> details = new ArrayList<PickBatchDetailModel>();
	private PullToRefreshListView listView;
	private PickBatchDetailAdapter adapter;
	private PickService pickService = new PickService();
	private Long billId;

	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_batch_detail;
	}
	
	@Override
	protected void initActivityView() {
		Intent intent = getIntent();
		billId = intent.getExtras().getLong("billId");
		Long skuId = intent.getExtras().getLong("skuId");
		
		listView = (PullToRefreshListView) findViewById(R.id.pickBatchDetail_details);
		ListViewUtil.setOnPullDownListView(listView);
		adapter = new PickBatchDetailAdapter(context, details );
		
		/*params.getParams().put("billId", String.valueOf(billId));
		params.getParams().put("skuId", String.valueOf(skuId));*/
//		params.setUrl(ConstantUrl.PICK_BATCH_DETAIL);//**********
		getData();
		
	}
	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBatchDetail_headerView, R.string.title_pick_bill_detail_view);
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context, PickBillDetailViewActivity.class);
				
				intent.putExtra("shelfCode", details.get(position-1).getShelf().getCode());
				intent.putExtra("skuBatch", details.get(position-1).getSysBatchNo());
				intent.putExtra("actualPickUnitQty", details.get(position-1).getPickMinUnitQty());
				intent.putExtra("actualPickMinUnitQty", details.get(position-1).getPickUnitQty());
				intent.putExtra("shouldPickMinUnitQty", details.get(position-1).getPickMinUnitQty());
				intent.putExtra("shouldPickUnitQty", details.get(position-1).getPickUnitQty());
				
				startActivity(intent);
			}
		});
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				getData();
			}

		}));
	}
	
	private void getData() {
		details.clear();
		
		pickService.searchPickBatchDetailByBillId(billId, new AbstractServiceCallBack<List<PickBatchDetailModel>>(context) {
			
			@Override
			public void doSuccess(List<PickBatchDetailModel> result) {
				if (result != null && result.size() > 0) {
					details.addAll(result);
					listView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
			}
		});
	}
}
