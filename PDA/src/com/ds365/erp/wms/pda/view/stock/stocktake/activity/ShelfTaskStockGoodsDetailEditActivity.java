package com.ds365.erp.wms.pda.view.stock.stocktake.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
/**
 * 货位商品盘点编辑
 * @author LiXinjian
 * @date 2016年6月17日.
 *
 */
public class ShelfTaskStockGoodsDetailEditActivity extends BasePdaListActivity{

	
	
	@Override
	protected void initActivityView() {
		
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.shelfTakeStockGoodsDetailEdit_headerview, R.string.shelf_take_stock);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.taskstock_shelf_take_stock_goods_detail_edit;
	}

	@Override
	protected void setListener() {
		
	}

}
