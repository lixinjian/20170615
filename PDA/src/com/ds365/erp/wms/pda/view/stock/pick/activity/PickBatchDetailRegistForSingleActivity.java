package com.ds365.erp.wms.pda.view.stock.pick.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.ScreenUtils;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailMapModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.service.pick.PickService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBatchDetailForSingleAdapter;
import com.ds365.swipelistview.SwipeMenu;
import com.ds365.swipelistview.SwipeMenuCreator;
import com.ds365.swipelistview.SwipeMenuItem;
import com.ds365.swipelistview.SwipeMenuListView;
import com.ds365.swipelistview.SwipeMenuListView.OnMenuItemClickListener;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PickBatchDetailRegistForSingleActivity extends BasePdaListActivity implements OnClickListener{
	private TextView submitButton,unitNameValue, expectUnitQtyValue, expectMinUnitQtyValue, expectQtyValue;
	private TextView skuNameValue, storeNameValue,qtyValue, shelfCodeValue, sysBatchNoValue,specValue;
	private TextView skuCodeValue;
	private IntEditField unitQtyValue,minUnitQtyValue; 
	private ImageView addButton;
	
	private PickBatchDetailForSingleAdapter adapter;
	private SwipeMenuListView listView;
	private PickBatchDetailModel pickBatchDetailModel;
	private List<PickBatchDetailModel> details = new ArrayList<PickBatchDetailModel>();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	
	private PickService pickService = new PickService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_batch_detail_regist_for_single;
	}
	
	@Override
	protected void initActivityView() {
		
		pickBatchDetailModel = (PickBatchDetailModel) getIntent().getSerializableExtra(PickBillRegistForSingleActivity.SER_KEY);
		
		listView = (SwipeMenuListView) findViewById(R.id.pickBatchDetailRegistForSingle_details);
		//设置listView不可上拉和下拉
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		addButton = (ImageView) findViewById(R.id.pickBatchDetailRegistForSingle_add_button);
		skuCodeValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_skuCode_value);
		shelfCodeValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_shelfCode_value);
		sysBatchNoValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_sysBatchNo_value);
		skuNameValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_skuName_value);
		storeNameValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_storeName_value);
		unitQtyValue = (IntEditField) findViewById(R.id.pickBatchDetailRegistForSingle_unitQty_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.pickBatchDetailRegistForSingle_minUnitQty_value);
		qtyValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_qty_value);
		unitNameValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_unitName_value);
		expectUnitQtyValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_expectUnitQty_value);
		expectMinUnitQtyValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_expectMinUnitQty_value);
		expectQtyValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_expectQty_value);
		submitButton = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_submit_button);
		specValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingle_spec_value);
		skuCodeValue.setText(pickBatchDetailModel.getSku().getCode());
		shelfCodeValue.setText(pickBatchDetailModel.getShelf().getCode());
		sysBatchNoValue.setText(pickBatchDetailModel.getSysBatchNo());
		skuNameValue.setText(pickBatchDetailModel.getSku().getGoods().getName());
		storeNameValue.setText(pickBatchDetailModel.getStore().getName());
		unitNameValue.setText(pickBatchDetailModel.getUnitName());
		expectUnitQtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectUnitQty()));
		expectMinUnitQtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectMinUnitQty()));
		expectQtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectQty()));
		unitQtyValue.setValue(pickBatchDetailModel.getExpectUnitQty());
		minUnitQtyValue.setValue(pickBatchDetailModel.getExpectMinUnitQty());
		qtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectQty()));
		specValue.setText(pickBatchDetailModel.getSpec());
		pickBatchDetailModel.setPickQty(pickBatchDetailModel.getExpectQty());
		pickBatchDetailModel.setExpectPickQty(pickBatchDetailModel.getExpectQty());
		adapter = new PickBatchDetailForSingleAdapter(context, details);
		listView.setAdapter(adapter);
		minUnitQtyValue.setMaxValueAndTextChangeListener(pickBatchDetailModel.getSpecQty(),"散数不能大于包装数量！",null);
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
	protected void initNavigation() {
		initHeadView(R.id.pickBatchDetailRegistForSingle_headerView, R.string.goods_batch_detail);
	}

	@Override
	protected void setListener() {
		submitButton.setOnClickListener(this);
		addButton.setOnClickListener(this);

		TextWatcher textWatcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
				
					Integer pickUnitQty = calPickUntiQty(editable);
					Integer pickMinUnitQty = calPickMinUintQty(editable);
					Integer pickQty = calPickQty(pickUnitQty, pickMinUnitQty);
					
					pickBatchDetailModel.setPickUnitQty(pickUnitQty);
					pickBatchDetailModel.setPickMinUnitQty(pickMinUnitQty);
					pickBatchDetailModel.setPickQty(pickQty);
					if(pickQty.intValue()>pickBatchDetailModel.getExpectQty().intValue()){
						T.showShort(context, "实捡总数量大于应捡总数量,请重新输入");
					}
					qtyValue.setText(String.valueOf(pickQty));
					
			}
		};
		
		unitQtyValue.getEditText().addTextChangedListener(textWatcher);
		minUnitQtyValue.getEditText().addTextChangedListener(textWatcher);
		
		listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                adapter.deleteItem(position);
                adapter.notifyDataSetChanged();
				return false;
			}
		});
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pickBatchDetailRegistForSingle_submit_button:
			verificationPickQty();
			break;
		case R.id.pickBatchDetailRegistForSingle_add_button:
			
			Integer expectPickQty = verificationExpectPickQty();
			
			pickBatchDetailModel.setExpectPickQty(expectPickQty);
			int qty = Integer.parseInt(qtyValue.getText().toString().trim());
			if(qty>=pickBatchDetailModel.getExpectQty().intValue()){
				T.showShort(context, "剩余应捡数量为0，不可添加！");
				return;
			}else{
				IntentUtils.startActivityBySerialForResult(PickBatchDetailRegistForSingleActivity.this
						, PickBatchDetailRegistForSingleEditActivity.class, SER_KEY
						, pickBatchDetailModel, PdaConstants.PDA_REQUEST_CODE);
			}
			break;
		}
	}

	private Integer calPickQty(Integer pickUnitQty, Integer pickMinUnitQty) {
		Integer specQty = pickBatchDetailModel.getSpecQty();
		Integer pickQty = QtyMoneyUtils.getQty(pickUnitQty, pickMinUnitQty, specQty);
		return pickQty;
	}

	private Integer calPickMinUintQty(Editable editable) {
		int pickMinUnitQty=0;
		if(!"".equals(editable.toString())){
			String minUintQtyStr=String.valueOf(minUnitQtyValue.getValue());
			if(StringUtils.isBlank(minUintQtyStr))
				pickMinUnitQty=StringUtils.isEmptyInt(minUintQtyStr);
			pickMinUnitQty = Integer.parseInt(minUintQtyStr);
			return pickMinUnitQty;
		}else{
			T.showShort(context, "请输入数量");
			return pickMinUnitQty;
		}
	}

	private Integer calPickUntiQty(Editable editable) {
		int pickUnitQty=0;
		if(!"".equals(editable.toString())){
			String unitQtyStr=String.valueOf(unitQtyValue.getValue());
			
			if(StringUtils.isBlank(unitQtyStr))
				pickUnitQty=StringUtils.isEmptyInt(unitQtyStr);
			pickUnitQty = Integer.parseInt(unitQtyStr);
			return pickUnitQty;
		}else{
			T.showShort(context, "请输入数量");
			return pickUnitQty;
		}
	}

	/**
	 * 提交处理
	 * @param params
	 */
	private void submitList() {
		
		PickBatchDetailMapModel createParams = setupPickBatchDetailMapModel();
		
		if(createParams.getBatchDetails().size()==0){
			T.showShort(context, "提交的检查记录数不能为空");
			return;
		}

		pickService.pickBillRegistByDetail(createParams, new AbstractServiceCallBack<String>(context) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				finish();
			}
			
			@Override
			public void doFaile(String str) {
				T.showShort(context, str);
				//如果提交失败,把details的第一条数据移除,等下次提交前再添加,否则,本条数据将会被添加到listView当中,造成数据重复
				details.remove(0);
			}
		});
	}
	
	private PickBatchDetailMapModel setupPickBatchDetailMapModel() {
		
		PickBatchDetailMapModel pickBatchDetailMapModel = new PickBatchDetailMapModel();
		
		pickBatchDetailMapModel.setBatchDetailId(pickBatchDetailModel.getId());
		
		pickBatchDetailMapModel.setBatchDetails(details);
		
		return pickBatchDetailMapModel;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		
		if(intent != null){
			if (resultCode == PickBatchDetailRegistForSingleEditActivity.RESULT_CODE) {
				PickBatchDetailModel pickBatchDetailModel = (PickBatchDetailModel) intent.getSerializableExtra(
						PickBatchDetailRegistForSingleEditActivity.SER_KEY);
				
//				在此添加一条数据,并更新adapter
				details.add(pickBatchDetailModel);
				adapter.notifyDataSetChanged();
			}
		}
		
	}
	
//	验证应捡数量
	private Integer verificationExpectPickQty(){
		Integer expectPickQty = 0;
		Integer expectQty = pickBatchDetailModel.getExpectQty() - Integer.valueOf(qtyValue.getText().toString());
		for(PickBatchDetailModel detailModel :details){
			expectPickQty += detailModel.getPickQty();
		}
		return expectQty - expectPickQty;
	}
	
	//验证实拣总数量不能大于应捡总数量
	private void verificationPickQty(){
		
		Integer expectQty = pickBatchDetailModel.getExpectQty();
		Integer pickQty = 0;
		
		Integer pickUnitQty = 0;
		Integer pickMinUnitQty = 0;
		
		for(PickBatchDetailModel detailModel :details){
			pickUnitQty += detailModel.getPickUnitQty();
			pickMinUnitQty += detailModel.getPickMinUnitQty();
		}
		
		pickQty = calPickQty(pickUnitQty, pickMinUnitQty);
		
		if (pickBatchDetailModel.getPickQty() + pickQty > expectQty) {
			T.showShort(context, "实捡总数量大于应捡总数量,请重新输入");
			return;
		}
		
		//验证通过后开始提交操作
		DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
				, getString(R.string.dialog_submit_pickbill), true, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
//						 pickBatchDetailModel为默认值,
//						 如果不需要拣货到多个货位时:直接将本条数据提交
//						 如果需要拣货到多个货位时:将其它货位数据添加到details中,再将pickBatchDetailModel添加到details中的第一条
//						,若提交失败则将此条数据移除,否则此条数据将会添加到listView当中;
						pickBatchDetailModel.setPickUnitQty(unitQtyValue.getValue());
						pickBatchDetailModel.setPickMinUnitQty(minUnitQtyValue.getValue());
						pickBatchDetailModel.setPickQty(Integer.valueOf(qtyValue.getText().toString().trim()));
						details.add(0,pickBatchDetailModel);
						submitList();
					}
				});
	}
}
