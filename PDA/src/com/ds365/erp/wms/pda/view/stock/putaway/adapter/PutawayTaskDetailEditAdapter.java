package com.ds365.erp.wms.pda.view.stock.putaway.adapter;

import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class PutawayTaskDetailEditAdapter extends CommonAdapter<PutawayTaskDetailModel> {
	
	private ShelfService shelfService = new ShelfService();
	public PutawayTaskDetailEditAdapter(Context context, List<PutawayTaskDetailModel> list) {
		super(context, list);
	}

	/**
	 * 删除选中的item
	 * @param delete
	 */
	public void deleteItem(int delete){
		list.remove(delete);
		notifyDataSetChanged();
	}
	
	@Override
	public int getContentViewId() {
		return R.layout.stock_putaway_task_record_adapter;
	}

	@Override
	public CommonAdapter<PutawayTaskDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.shelfCodeValue = (EditText) contentView.findViewById(R.id.putawaytaskRecordAdapter_shelfCode_value);
		holder.storeNameValue = (TextView) contentView.findViewById(R.id.putawaytaskRecordAdapter_storeName_value);
		holder.unitQtyValue = (IntEditField) contentView.findViewById(R.id.putawaytaskRecordAdapter_unitQty_editValue);
		holder.minUnitQtyValue = (IntEditField) contentView.findViewById(R.id.putawaytaskRecordAdapter_minUnitQty_editValue);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PutawayTaskDetailModel>.HolderView contentView, final PutawayTaskDetailModel putawayTaskDetail) {
		final ViewHold holder = (ViewHold) contentView;
		holder.unitQtyValue.setValue(putawayTaskDetail.getUnitQty());
		holder.minUnitQtyValue.setValue(putawayTaskDetail.getMinUnitQty());
		if(putawayTaskDetail.getShelf() != null){
			holder.shelfCodeValue.setText(putawayTaskDetail.getShelf().getCode());
		}else{
			holder.shelfCodeValue.setText("");
		}
		if(putawayTaskDetail.getStore() != null){
			holder.storeNameValue.setText(putawayTaskDetail.getStore().getName());
		}else{
			holder.storeNameValue.setText("");
		}
		holder.minUnitQtyValue.setMaxValueAndTextChangeListener(putawayTaskDetail.getSpecQty(),"散数不能大于包装数量！",null);

		holder.shelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					searchShelf(v,putawayTaskDetail,holder);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchShelf(v,putawayTaskDetail,holder);
                    return true;
                }
				return false;
			}
		});
		
		TextWatcher textWatcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				if (!"".equals(s.toString())) {
					int minUnitQty = holder.minUnitQtyValue.getValue();
					int unitQty = holder.unitQtyValue.getValue();
					int qty = QtyMoneyUtils.getQty(unitQty, minUnitQty, putawayTaskDetail.getSpecQty());
					putawayTaskDetail.setQty(qty);
					putawayTaskDetail.setUnitQty(unitQty);
					putawayTaskDetail.setMinUnitQty(minUnitQty);
				}
			}
		};
		
		holder.unitQtyValue.getEditText().addTextChangedListener(textWatcher);
		holder.minUnitQtyValue.getEditText().addTextChangedListener(textWatcher);
	}
	
	private void searchShelf(TextView v,final PutawayTaskDetailModel putawayTaskDetail,final ViewHold holder){
		
		String shelfCode = v.getText().toString().trim();

		SkuShelfBatchStockQueryParamsModel skuShelfBatchStockQueryParams = new SkuShelfBatchStockQueryParamsModel();
		skuShelfBatchStockQueryParams.setSkuId(putawayTaskDetail.getSku().getId());
		skuShelfBatchStockQueryParams.setExcludeSysBatchNo(putawayTaskDetail.getSysBatchNo());
		skuShelfBatchStockQueryParams.setShelfCode(shelfCode);
		
		shelfService.getShelfByCodeAndVal(skuShelfBatchStockQueryParams, new AbstractServiceCallBack<ShelfModel>(context) {

			@Override
			public void doSuccess(ShelfModel shelf) {
				if (shelf !=null) {
					StoreModel store=shelf.getStore();
					putawayTaskDetail.setShelf(shelf);
					putawayTaskDetail.setStore(store);
					putawayTaskDetail.setShelfCode(shelf.getCode());
					holder.storeNameValue.setText(store.getName());
					holder.shelfCodeValue.setText(shelf.getCode());
					holder.unitQtyValue.requestFocus();
				}else{
					T.showLong(context, "找不到对应的货位！");
					holder.shelfCodeValue.setText("");
					holder.shelfCodeValue.requestFocus();
				}
			}
			@Override
			public void doFaile(String str) {
				T.showLong(context, str);
			}
		});
	}
	
	class ViewHold extends HolderView{	
		EditText shelfCodeValue;
		TextView storeNameValue;
		IntEditField unitQtyValue;
		IntEditField minUnitQtyValue;
	}
}
