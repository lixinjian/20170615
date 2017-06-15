package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PurchaseEnterDetailAdapter extends CommonAdapter<PurchaseEnterDetailModel> {
	
	public PurchaseEnterDetailAdapter(Context context, List<PurchaseEnterDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_purchase_enter_detail_adapter;
	}

	@Override
	public HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_skuCode_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_minUnitQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_unitName_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_skuName_value);
		holder.makeSure = (TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_makesure);
		holder.makeSureImg = (ImageView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_makesureImg);
		holder.expectUnitQty =(TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_expectUnitQty_value);
		holder.expectMinUnitQty=(TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_expectMinUnitQty_value);
		holder.specQty  =(TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_specQty_value);
		
		holder.storeName=(TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_storeName_value);
		holder.shelfCode=(TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_shelfCode_value);
		holder.produceDate =(DateField) contentView.findViewById(R.id.purchaseEnterDetailAdapter_produceDate_value);
		holder.produceBatchNo =(TextView) contentView.findViewById(R.id.purchaseEnterDetailAdapter_produceBatchNo_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, HolderView contentView, PurchaseEnterDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PurchaseEnterDetailModel enterDetail  =list.get(position);
		
		holder.skuCode.setText(StringUtils.isEmptyString(enterDetail.getSku().getCode()));
		holder.unitName.setText(enterDetail.getUnitName());
		holder.unitQty.setText(String.valueOf(enterDetail.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(enterDetail.getMinUnitQty()));
		holder.skuName.setText(enterDetail.getSku().getName());
		holder.expectUnitQty.setText(String.valueOf(enterDetail.getExpectUnitQty()));
		holder.expectMinUnitQty.setText(String.valueOf(enterDetail.getExpectMinUnitQty()));
		holder.specQty.setText(String.valueOf(enterDetail.getSpecQty()));
		holder.produceDate.setValue(enterDetail.getProduceDate());
		holder.produceBatchNo.setText(enterDetail.getProduceBatchNo());
		if(enterDetail.getStore()!=null)	
			holder.storeName.setText(enterDetail.getStore().getName());
		if(enterDetail.getShelf()!=null)
			holder.shelfCode.setText(enterDetail.getShelf().getCode());
		if (enterDetail.isCheckFlag()) {
			holder.makeSure.setText("已检查");
			holder.makeSureImg.setVisibility(View.VISIBLE);
		}else{
			holder.makeSure.setText("未检查");
			holder.makeSureImg.setVisibility(View.GONE);
		}
		
		holder.makeSure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!enterDetail.isCheckFlag()) {
					StringBuilder message=new StringBuilder();
					if(enterDetail.getStore() == null){
						message.append("库区为空,请录入\r\n");
					}
					if (enterDetail.getShelf() == null) {
						message.append("货位为空,请录入\r\n");
					}
					if (enterDetail.getProduceDate() == null) {
						message.append("生产日期为空,请录入\r\n");
					} 
					if (enterDetail.getProduceBatchNo() == null) {
						message.append("生产批次为空,请录入\r\n");
					}
					if (enterDetail.getUnitQty() == null && enterDetail.getMinUnitQty() == null) {
						message.append("件数和散数不能同时为空,请录入");
					}
					if(message.length()!=0){
						T.showShort(context, message.toString());
						return;
					}
					
					holder.makeSure.setText("已检查");
					holder.makeSureImg.setVisibility(View.VISIBLE);
        			enterDetail.setCheckFlag(true);
        			
				} else if (enterDetail.isCheckFlag()) {
					holder.makeSure.setText("未检查");
					holder.makeSureImg.setVisibility(View.GONE);
        			enterDetail.setCheckFlag(false);
				}
			}
		});
	}
	
	 class ViewHold extends HolderView{
		TextView skuCode;		//SKU码
		TextView expectUnitQty;		//应收件数
		TextView expectMinUnitQty; 	//应收散数
		TextView unitQty;		//实收件数
		TextView minUnitQty; 	//实收散数
		TextView unitName;		//包装
		TextView skuName;		//SkuName
		TextView makeSure;		//
		ImageView makeSureImg;
		TextView specQty;	
		TextView storeName;
		TextView shelfCode;
		DateField produceDate;
		TextView produceBatchNo;
	}
}


