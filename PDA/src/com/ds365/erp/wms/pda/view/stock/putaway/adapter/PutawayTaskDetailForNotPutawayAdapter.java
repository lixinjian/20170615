package com.ds365.erp.wms.pda.view.stock.putaway.adapter;

import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailModel;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskBillService;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class PutawayTaskDetailForNotPutawayAdapter extends CommonAdapter<PutawayTaskDetailModel> {

	private PutawayTaskBillService putawayTaskBillService = new PutawayTaskBillService();
	public static final String SER_KEY = PdaConstants.nextSerKey();
	private EditCallback editCallback;

	public interface EditCallback {
		public void click(View v);
	}
	
	public PutawayTaskDetailForNotPutawayAdapter(Context context, List<PutawayTaskDetailModel> list, EditCallback editCallback) {
		super(context, list);
		this.editCallback = editCallback;
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_putaway_task_detail_for_not_putaway_adapter;
	}

	@Override
	public CommonAdapter<PutawayTaskDetailModel>.HolderView getHoldView(int position, View contentView,
			ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.putawayButton = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_putaway_button);
		holder.spec = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_spec_value);
		holder.qty = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_qty_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_skuName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_shelfCode_value);
		holder.skuCode = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_skuCode_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_storeName_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_unitName_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_minUnitQty_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_specQty_value);
		holder.editButton = (TextView) contentView.findViewById(R.id.putawayTaskDetailForNotPutawayAdapter_edit_button);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PutawayTaskDetailModel>.HolderView contentView,
			PutawayTaskDetailModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		final PutawayTaskDetailModel putawayTaskDetailModel = list.get(position);
		holder.qty.setText(String.valueOf(putawayTaskDetailModel.getQty()));
		holder.skuName.setText(putawayTaskDetailModel.getSku().getGoods().getName());
		holder.shelfCode.setText(putawayTaskDetailModel.getShelf().getCode());
		holder.skuCode.setText(putawayTaskDetailModel.getSku().getCode());
		holder.storeName.setText(putawayTaskDetailModel.getStore().getName());
		holder.unitName.setText(putawayTaskDetailModel.getUnitName());
		holder.spec.setText(putawayTaskDetailModel.getSku().getSpec());
		holder.specQty.setText(String.valueOf(putawayTaskDetailModel.getSpecQty()));
		holder.unitQty.setText(String.valueOf(putawayTaskDetailModel.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(putawayTaskDetailModel.getMinUnitQty()));
		holder.putawayButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogUtils.createConfirmDialog(context, context.getString(R.string.dialog_submit_title)
						, context.getString(R.string.dialog_putaway_task), true, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//点击上架按钮进行上架
								putawayTaskBillService.putaway(putawayTaskDetailModel.getId(), new AbstractServiceCallBack<String>(context) {

									@Override
									public void doSuccess(String data) {
										T.showShort(context, "上架成功");
										remove(position);
//										//上架成功刷新adapter
										notifyDataSetChanged();
									}
								});
							}
						});
			}
		});
		holder.editButton.setTag(position);
		holder.editButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editCallback.click(v);
				
			}
		});
		
	}

	class ViewHold extends HolderView{
		TextView skuName;		//
		TextView skuCode;		//编号
		TextView spec;		//规格
		TextView unitName;
		TextView storeName;
		TextView shelfCode;		//货位号
		TextView unitQty;	//上架件数
		TextView minUnitQty;//上架散数
		TextView qty;		//上架总数量
		TextView specQty;
		TextView putawayButton;//上架按钮
		TextView editButton;//修改按钮
	}
}
