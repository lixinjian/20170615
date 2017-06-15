package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.CommonSelectAdapter;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterDetailModel;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SaleReturnEnterDetailAdapter extends CommonSelectAdapter<SaleReturnEnterDetailModel> {
	
	public SaleReturnEnterDetailAdapter(Context context, List<SaleReturnEnterDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_enter_detail_adapter;
	}

	@Override
	public CommonAdapter<SaleReturnEnterDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_skuCode_value);
//		holder.qty = (TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_qty_value);
//		holder.receivedQty = (TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_receivedQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_unitName_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_skuName_value);
		holder.makeSure = (TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_makesure);
		holder.makeSureImg = (ImageView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_makesureImg);
		holder.signUnitQty =(TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_signUnitQty_value);
		holder.signMinUnitQty=(TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_signMinUnitQty_value);
		holder.unitQty =(TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_unitQty_value);
		holder.minUnitQty=(TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_minUnitQty_value);
		holder.specQty  =(TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_specQty_value);
		
		holder.storeName=(TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_storeName_value);
		holder.shelfCode=(TextView) contentView.findViewById(R.id.saleReturnEnterDetail_adapter_shelfCode_value);
		holder.parent = (LinearLayout) contentView.findViewById(R.id.saleReturnEnterDetail_parent);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<SaleReturnEnterDetailModel>.HolderView contentView, SaleReturnEnterDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final SaleReturnEnterDetailModel  detailModel=list.get(position);
		holder.skuCode.setText(detailModel.getSku().getCode());
//		holder.qty.setText(String.valueOf(list.get(position).getUnitQty()));
//		holder.receivedQty.setText(String.valueOf(list.get(position).getMinUnitQty()));
		holder.unitName.setText(detailModel.getUnitName());
		holder.specQty.setText(String.valueOf(detailModel.getSpecQty()));
		holder.skuName.setText(detailModel.getSkuName());
		holder.unitQty.setText(String.valueOf(detailModel.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(detailModel.getMinUnitQty()));
		holder.signUnitQty.setText(String.valueOf(detailModel.getSignUnitQty()));
		holder.signMinUnitQty.setText(String.valueOf(detailModel.getSignMinUnitQty()));
		if(detailModel.getStore()!=null)
			holder.storeName.setText(detailModel.getStore().getName());
		if(detailModel.getShelf()!=null)
			holder.shelfCode.setText(detailModel.getShelf().getCode());
		if (detailModel.isCheckFlag()) {
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
				if (!detailModel.isCheckFlag()) {
					
					StringBuilder message=new StringBuilder();
					if(detailModel.getStore() == null){
						message.append("库区为空,请录入\r\n");
					}
					if (detailModel.getShelf() == null) {
						message.append("货位为空,请录入\r\n");
					}
					if (detailModel.getProduceDate() == null) {
						message.append("生产日期为空,请录入\r\n");
					} 
					if (detailModel.getProduceBatchNo() == null) {
						message.append("生产批次为空,请录入\r\n");
					}
					if (detailModel.getSysBatchNo() == null) {
						message.append("系统批次为空,请录入\r\n");
					}
					if (detailModel.getUnitQty() == null && detailModel.getMinUnitQty() == null) {
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
		TextView skuCode;	//sku码
//		TextView qty;		//应收总数量
//		TextView receivedQty;//实收总数量
		TextView specQty;	//包装
		TextView skuName;	//sku名称
		TextView unitName;		//包装
		TextView makeSure;	
		ImageView makeSureImg;
		TextView signUnitQty;		//应退件数
		TextView signMinUnitQty; 	//应退散数
		TextView unitQty;		//实退件数
		TextView minUnitQty; 	//实退散数
		TextView storeName;
		TextView shelfCode;
		LinearLayout parent;
		
	}
}