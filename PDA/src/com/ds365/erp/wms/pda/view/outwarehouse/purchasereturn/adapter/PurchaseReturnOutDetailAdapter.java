package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutDetailModel;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PurchaseReturnOutDetailAdapter extends CommonAdapter<PurchaseReturnOutDetailModel> {
	
	public PurchaseReturnOutDetailAdapter(Context context, List<PurchaseReturnOutDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_out_detail_adapter;
	}

	@Override
	public CommonAdapter<PurchaseReturnOutDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();

		holder.skuCode = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_skuCode_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_skuName_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_specQty_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_minUnitQty_value);
		holder.expectUnitQty = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_expectUnitQty_value);
		holder.expectMinUnitQty = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_expectMinUnitQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_unitName_value);
		holder.makeSure = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_makesure);
		holder.makeSureImg = (ImageView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_makesureImg);
		holder.storeName = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_storeName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailAdapter_shelfCode_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PurchaseReturnOutDetailModel>.HolderView contentView, PurchaseReturnOutDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		
		final PurchaseReturnOutDetailModel detailModel = list.get(position);
		
		holder.skuCode.setText(detailModel.getSku().getCode());
		holder.skuName.setText(detailModel.getSku().getName());
		holder.specQty.setText(String.valueOf(detailModel.getSpecQty())); 
		holder.unitQty.setText(String.valueOf(detailModel.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(detailModel.getMinUnitQty()));
		holder.expectUnitQty.setText(String.valueOf(detailModel.getExpectUnitQty()));
		holder.expectMinUnitQty.setText(String.valueOf(detailModel.getExpectMinUnitQty()));
		holder.unitName.setText(detailModel.getPack().getUnitName());
		holder.storeName.setText(detailModel.getStore().getName());			
		holder.shelfCode.setText(detailModel.getShelf().getCode());
		
		if (detailModel.isCheckFlag()) {
			holder.makeSure.setText("已检查");
			holder.makeSureImg.setVisibility(View.VISIBLE);
		}else{
			holder.makeSure.setText("未检查");
			holder.makeSureImg.setVisibility(View.GONE);
		}
		holder.makeSure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!detailModel.isCheckFlag()) {
					
					StringBuilder message=new StringBuilder();
					if(detailModel.getStore() == null && detailModel.getShelf() == null){
						message.append("件数和散数不能同时为空,请录入");
					}
					if(message.length()!=0){
						T.showShort(context, message.toString());
						return;
					}
					
					holder.makeSure.setText("已检查");
					holder.makeSureImg.setVisibility(View.VISIBLE);
					detailModel.setCheckFlag(true);
        			
				} else if (detailModel.isCheckFlag()) {
					holder.makeSure.setText("未检查");
					holder.makeSureImg.setVisibility(View.GONE);
					detailModel.setCheckFlag(false);
				}
			}
		});
	}
	class ViewHold extends HolderView{
		TextView skuCode;			//sku码
		TextView skuName;			//sku名称
		TextView specQty;	
		TextView unitQty;	//退货件数
		TextView minUnitQty;	//退货散数
		TextView expectUnitQty;	//应退件数
		TextView expectMinUnitQty;	//应退散数
		TextView unitName;			//包装
		TextView makeSure;		//
		ImageView makeSureImg;
		TextView storeName;			//包装
		TextView shelfCode;		//
	}
}
