package com.ds365.erp.wms.pda.view.stock.pick.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailRegisterParamsModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBillModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBillRegisterParamModel;
import com.ds365.erp.wms.pda.service.pick.PickService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.EmployeeSelectorActivity;
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBatchDetailAdapter;
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBillListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 拣货下架 - 拣货单明细   	页面可编辑
 * 
 * @author lxj
 *
 */
public class PickBillRegistActivity extends BasePdaListActivity implements OnClickListener{
	private PullToRefreshListView listView;
	private PickBatchDetailAdapter adapter;
	private TextView submitButton;
	private TextView pickerNameValue, billCodeValue;
	private DateField makeTimeValue;
	private ImageView searchPickerNameButton;
	
	private Long billId;
	
	private PickBillModel bill = new PickBillModel();
	private List<PickBatchDetailModel> details = new ArrayList<PickBatchDetailModel>();
	
	private int selectPosition = 0;
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private PickService pickService = new PickService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_bill_regist;
	}
	
	@Override
	protected void initActivityView() {
		billCodeValue = (TextView) findViewById(R.id.pickBillRegist_billCode_value);
		makeTimeValue = (DateField) findViewById(R.id.pickBillRegist_makeTime_value);
		submitButton = (TextView) findViewById(R.id.pickBillRegist_submitButton);
		listView = (PullToRefreshListView) findViewById(R.id.pickBillRegist_details);
		pickerNameValue = (TextView) findViewById(R.id.pickBillRegist_pickerName_Value);
		searchPickerNameButton = (ImageView) findViewById(R.id.pickBillRegist_searchPickerName_button);
		PickBillModel pickBillModel = (PickBillModel) getIntent().getSerializableExtra(PickBillListAdapter.SER_KEY);
		billId = pickBillModel.getId();
		pickerNameValue.setText(pickBillModel.getPicker().getName());
		makeTimeValue.setValue(pickBillModel.getMakeTime());
		billCodeValue.setText(pickBillModel.getBillCode());
		adapter=new PickBatchDetailAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
        searchPickBatchDetail();
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBillRegist_headerview, R.string.title_pick_bill_manager);
	}

	@Override
	protected void setListener() {
		submitButton.setOnClickListener(this);
		searchPickerNameButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IntentUtils.startActivityBySerialForResult(PickBillRegistActivity.this
						, PickBatchDetailEditViewActivity.class, SER_KEY
						, details.get(selectPosition), PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchPickBatchDetail();
			}
		}));
	}
	
	private void searchPickBatchDetail() {
		details.clear();
		
		pickService.searchPickBatchDetailByBillId(billId, new AbstractServiceCallBack<List<PickBatchDetailModel>>(context) {
			
			@Override
			public void doSuccess(List<PickBatchDetailModel> result) {
				if (result != null && result.size() > 0) {
					details.addAll(result);
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
				adapter.notifyDataSetChanged();
			}
		});
	}

	/**
	 * 提交处理
	 * @param params
	 */
	private void submitList() {
		
		PickBillRegisterParamModel  createParams=setupCreateRegisterBillModel(bill,details);
		
		if(createParams.getBatchDetails().size()==0){
			T.showShort(context, "提交的检查记录数不能为空");
			return;
		}

		pickService.pickBillRegisterByBill(createParams, new AbstractServiceCallBack<String>(context) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				finish();
			}
		});
	
	}
	
	private PickBillRegisterParamModel setupCreateRegisterBillModel(PickBillModel bill, List<PickBatchDetailModel> details) {
		
		PickBillRegisterParamModel pickBillRegisterParamModel = new PickBillRegisterParamModel();
		
		pickBillRegisterParamModel.setId(billId);
		pickBillRegisterParamModel.setPickerName(bill.getPicker().getName());
		pickBillRegisterParamModel.setPickerId(bill.getPicker().getId());
		pickBillRegisterParamModel.setPickDate(bill.getPickDate());
		List<PickBatchDetailRegisterParamsModel> createDetails=new ArrayList<PickBatchDetailRegisterParamsModel>();
		
		for(PickBatchDetailModel  detail: details){
			if(detail.isCheckFlag()){
				
				PickBatchDetailRegisterParamsModel createDetail = new PickBatchDetailRegisterParamsModel();
				createDetail.setDiffQty(detail.getDiffQty());
				createDetail.setPickMinUnitQty(detail.getPickMinUnitQty());
				createDetail.setPickQty(detail.getPickQty());
				createDetail.setPickUnitQty(detail.getPickUnitQty());
				createDetail.setId(detail.getId());
				createDetail.setShelfId(detail.getShelf().getId());
				createDetail.setStoreId(detail.getStore().getId());
				createDetails.add(createDetail);
			}
		}
		
		pickBillRegisterParamModel.setBatchDetails(createDetails);
		
		return pickBillRegisterParamModel;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pickBillRegist_submitButton:
			DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
					, getString(R.string.dialog_submit_pickbill), true, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							submitList();
						}
					});
			break;
		case R.id.pickBillRegist_searchPickerName_button:
			Intent intent = new Intent(context,EmployeeSelectorActivity.class);
			intent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchPagePickerByParams);
			startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (null != data) {
			if (resultCode == EmployeeSelectorActivity.RESULT_CODE) {
				EmployeeModel employeeModel = (EmployeeModel) data.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
				bill.setPicker(employeeModel);
				pickerNameValue.setText(employeeModel.getName());
			}else if(resultCode == PickBatchDetailEditViewActivity.RESULT_CODE) {
				PickBatchDetailModel pickBatchDetailModel = (PickBatchDetailModel) data.getSerializableExtra(PickBatchDetailEditViewActivity.SER_KEY);
				
				details.set(selectPosition, pickBatchDetailModel);
				details.get(selectPosition).setCheckFlag(true);
				adapter.notifyDataSetChanged();
			}
		}
	}
}