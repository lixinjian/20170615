package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateEditField;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class PurchaseEnterDetailEditAdapter extends CommonAdapter<PurchaseEnterDetailModel>{

	public PurchaseEnterDetailEditAdapter(Context context, List<PurchaseEnterDetailModel> list) {
		super(context, list);
	}

	public void deleteItem(int delete){
		list.remove(delete);
		notifyDataSetChanged();
	}
	
	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_purchase_enter_detail_edit_adapter;
	}

	@Override
	public CommonAdapter<PurchaseEnterDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.produceDate = (DateEditField) contentView.findViewById(R.id.purchaseEnterDetailEditAdapter_produceDate_value);
		holder.produceBatchNo = (EditText) contentView.findViewById(R.id.purchaseEnterDetailEditAdapter_produceBatchNo_value);
		holder.unitQtyValue = (IntEditField) contentView.findViewById(R.id.purchaseEnterDetailEditAdapter_unitQty_value);
		holder.minUnitQtyValue = (IntEditField) contentView.findViewById(R.id.purchaseEnterDetailEditAdapter_minUnitQty_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<PurchaseEnterDetailModel>.HolderView contentView, PurchaseEnterDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PurchaseEnterDetailModel detailModel = list.get(position);
		
		TextWatcher textWatcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				if(!"".equals(s.toString())){
					Integer minUnitQty = Integer.parseInt(holder.minUnitQtyValue.getEditText().getText().toString().trim());
					Integer unitQty = Integer.parseInt(holder.unitQtyValue.getEditText().getText().toString().trim());
					Integer qty = QtyMoneyUtils.getQty(unitQty, minUnitQty, detailModel.getSpecQty());
					
					if(qty.intValue() > detailModel.getRemainQty().intValue()){
						T.showShort(context, "输入的数量超过了剩余的总数量，请重新输入");
					}else{
						detailModel.setQty(qty);
						detailModel.setUnitQty(unitQty);
						detailModel.setMinUnitQty(minUnitQty);
					}
				}else{
					T.showShort(context, "请输入数量！");
				}
				
				//将生产批次设置到model
				detailModel.setProduceBatchNo(holder.produceBatchNo.getText().toString().trim());
				//将生产日期设置到model
				detailModel.setProduceDate(holder.produceDate.getValue());
				
			}
		};
		
		holder.unitQtyValue.getEditText().addTextChangedListener(textWatcher);
		holder.minUnitQtyValue.getEditText().addTextChangedListener(textWatcher);
		holder.produceDate.getEditText().addTextChangedListener(textWatcher);
		holder.produceBatchNo.addTextChangedListener(textWatcher);
	}
	
	class ViewHold extends HolderView{	
		DateEditField produceDate;
		EditText produceBatchNo;
		IntEditField unitQtyValue;
		IntEditField minUnitQtyValue;
	}
}
