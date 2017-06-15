package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateEditField;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class PurchaseEnterDetailEditAdapter2 extends CommonAdapter<PurchaseEnterDetailModel>{

	public PurchaseEnterDetailEditAdapter2(Context context, List<PurchaseEnterDetailModel> list) {
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
	public void setItemView(int position, CommonAdapter<PurchaseEnterDetailModel>.HolderView contentView, final PurchaseEnterDetailModel purchaseEnterDetail) {
		final ViewHold holder = (ViewHold) contentView;
		holder.unitQtyValue.setValue(purchaseEnterDetail.getUnitQty());
		holder.minUnitQtyValue.setValue(purchaseEnterDetail.getMinUnitQty());
		holder.produceDate.setValue(purchaseEnterDetail.getProduceDate());
		holder.produceBatchNo.setText(purchaseEnterDetail.getProduceBatchNo());
		
		TextWatcher textWatcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				int minUnitQty = holder.minUnitQtyValue.getValue();
				int unitQty = holder.unitQtyValue.getValue(); 
				int actualQty = QtyMoneyUtils.getQty(unitQty, minUnitQty, purchaseEnterDetail.getSpecQty());
				purchaseEnterDetail.setQty(actualQty);
				purchaseEnterDetail.setUnitQty(unitQty);
				purchaseEnterDetail.setMinUnitQty(minUnitQty);
				//将生产批次设置到model
				purchaseEnterDetail.setProduceBatchNo(holder.produceBatchNo.getText().toString().trim());
				//将生产日期设置到model
				purchaseEnterDetail.setProduceDate(holder.produceDate.getValue());
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
