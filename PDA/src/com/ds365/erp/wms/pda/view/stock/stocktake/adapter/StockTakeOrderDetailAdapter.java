package com.ds365.erp.wms.pda.view.stock.stocktake.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderDetailModel;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class StockTakeOrderDetailAdapter extends CommonAdapter<StockTakeOrderDetailModel> {

	public StockTakeOrderDetailAdapter(Context context, List<StockTakeOrderDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stocktake_order_detail_adapter;
	}

	@Override
	public CommonAdapter<StockTakeOrderDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuName = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_skuName_value);
		holder.skuCode = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_skuCode_value);
		holder.spec = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_spec_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_specQty_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_shelfCode_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_sysBatchNo_value);
		holder.expectUnitQty = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_unitQty_value);
		holder.expectMinUnitQty = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_minUnitQty_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_realUnitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_realMinUnitQty_value);
		holder.lossUnitQty = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_lossUnitQty_value);
		holder.lossMinUnitQty = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_lossMinUnitQty_value);
		holder.makeSure = (TextView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_makesure);
		holder.makeSureImg = (ImageView) contentView.findViewById(R.id.stockTakeOrderDetailAdapter_makesureImg);
		
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<StockTakeOrderDetailModel>.HolderView contentView, final StockTakeOrderDetailModel detailModel) {
		final ViewHold holder = (ViewHold) contentView;
		holder.skuName.setText(detailModel.getSku().getName());
		holder.skuCode.setText(detailModel.getSku().getCode());
		holder.spec.setText(detailModel.getSku().getSpec());
		holder.specQty.setText(String.valueOf(detailModel.getSku().getGoodsPack().getSpecQty()));
		holder.shelfCode.setText(detailModel.getShelf().getCode());
		holder.sysBatchNo.setText(detailModel.getSysBatchNo());
		holder.expectUnitQty.setText(String.valueOf(detailModel.getExpectUnitQty()));
		holder.expectMinUnitQty.setText(String.valueOf(detailModel.getExpectMinUnitQty()));
		
		Integer unitQtyValue = detailModel.getUnitQty();
		Integer minUnitQtyValue = detailModel.getMinUnitQty();
		if(unitQtyValue == null){
			holder.unitQty.setText("");
			unitQtyValue = 0;
		}else{
			holder.unitQty.setText(String.valueOf(unitQtyValue));
		}
		if(minUnitQtyValue == null){
			holder.minUnitQty.setText("");
			minUnitQtyValue = 0;
		}else{
			holder.minUnitQty.setText(String.valueOf(minUnitQtyValue));
		}
		
		Integer specQty = detailModel.getSku().getGoodsPack().getSpecQty();
		Integer expectQty = QtyMoneyUtils.getQty(detailModel.getExpectUnitQty(), detailModel.getExpectMinUnitQty(), specQty);
		
		Integer qty = QtyMoneyUtils.getQty(unitQtyValue, minUnitQtyValue, specQty);
		Integer lossQty = expectQty - qty; 
		QtyModel qtyModel = QtyMoneyUtils.getQtyEntity(lossQty, specQty);
		holder.lossUnitQty.setText(String.valueOf(qtyModel.getUnitQty()));
		holder.lossMinUnitQty.setText(String.valueOf(qtyModel.getMinUnitQty()));
		
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
					if(detailModel.getUnitQty() == null && detailModel.getMinUnitQty() == null){
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
		TextView skuName;		//sku名称
		TextView skuCode;		//sku码
		TextView spec;//规格
		TextView specQty;//包装数量
		TextView shelfCode;
		TextView sysBatchNo;
		TextView expectUnitQty;
		TextView expectMinUnitQty;
		TextView unitQty;//实盘件数
		TextView minUnitQty;//实盘散数
		TextView lossUnitQty;//盘损件数
		TextView lossMinUnitQty;//盘损散数
		TextView makeSure;		
		ImageView makeSureImg;
	}
}