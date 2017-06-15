package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonSelectAdapter;
import com.ds365.commons.utils.ListViewHeight;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderDetailModel;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SupplierOrderDetailGroupAdapter extends CommonSelectAdapter<SupplierOrderDetailModel> {
	
	public SupplierOrderDetailGroupAdapter(Context context, List<SupplierOrderDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_supplier_order_detail_group_adapter;
	}

	@Override
	public HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		
		ViewHold holder = new ViewHold(); 
		holder.skuCode = (TextView) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_skuCode_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_unitName_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_skuName_value);
		holder.makeSure = (TextView) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_makesure);
		holder.makeSureImg = (ImageView) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_makesureImg);
		holder.expectUnitQty =(TextView) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_expectUnitQty_value);
		holder.expectMinUnitQty=(TextView) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_expectMinUnitQty_value);
		holder.specQty  =(TextView) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_specQty_value);
		holder.innerListView = (ListView) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_innerListView);
		holder.parent = (LinearLayout) contentView.findViewById(R.id.supplierOrderDetailGroupAdapter_parent);
		return holder;
	}
	
	@Override
	public void setItemView(final int position, HolderView contentView, final SupplierOrderDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		holder.skuCode.setText(StringUtils.isEmptyString(arg2.getSku().getCode()));
		holder.unitName.setText(arg2.getUnitName());
		holder.skuName.setText(arg2.getSku().getName());
		holder.expectUnitQty.setText(String.valueOf(arg2.getUnitQty()));
		holder.expectMinUnitQty.setText(String.valueOf(arg2.getMinUnitQty()));
		holder.specQty.setText(String.valueOf(arg2.getSpecQty()));
		
		PurchaseEnterDetailForGroupAdapter innerAdapter = new PurchaseEnterDetailForGroupAdapter(context, arg2.getPurchaseEnterDetails());
		holder.innerListView.setAdapter(innerAdapter);
		innerAdapter.notifyDataSetChanged();
		ListViewHeight.setListViewHeight(holder.innerListView, false);
		
		if (arg2.isCheckFlag()) {
			holder.makeSure.setText("已检查");
			holder.makeSureImg.setVisibility(View.VISIBLE);
		}else{
			holder.makeSure.setText("未检查");
			holder.makeSureImg.setVisibility(View.GONE);
		}
		if(selectPositon == position){
			holder.parent.setBackgroundColor(context.getResources().getColor(R.color.dropdownlist_bg));
		}else{
			holder.parent.setBackgroundColor(context.getResources().getColor(R.color.transparent));
		}
		holder.makeSure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!arg2.isCheckFlag()) {
					StringBuilder message=new StringBuilder();
					List<PurchaseEnterDetailModel> list = arg2.getPurchaseEnterDetails();
					for (int i = 0; i < list.size(); i++) {
						
						if (list.get(i).getProduceDate() == null) {
							message.append("生产日期为空,请录入\r\n");
						} 
						if (list.get(i).getProduceBatchNo() == null) {
							message.append("生产批次为空,请录入\r\n");
						}
						if (list.get(i).getUnitQty() == null && list.get(i).getMinUnitQty() == null) {
							message.append("件数和散数不能同时为空,请录入");
						}
						if(message.length()!=0){
							T.showShort(context, message.toString());
							return;
						}
					}
					
					holder.makeSure.setText("已检查");
					holder.makeSureImg.setVisibility(View.VISIBLE);
					arg2.setCheckFlag(true);
				} else if (arg2.isCheckFlag()) {
					holder.makeSure.setText("未检查");
					holder.makeSureImg.setVisibility(View.GONE);
					arg2.setCheckFlag(false);
				}
			}
		});
	}
	
	 class ViewHold extends HolderView{
		TextView skuCode;		//SKU码
		TextView expectUnitQty;		//应收件数
		TextView expectMinUnitQty; 	//应收散数
		TextView unitName;		//包装
		TextView skuName;		//SkuName
		TextView makeSure;		//
		ImageView makeSureImg;
		TextView specQty;	
		ListView innerListView;
		LinearLayout parent;
	}
}