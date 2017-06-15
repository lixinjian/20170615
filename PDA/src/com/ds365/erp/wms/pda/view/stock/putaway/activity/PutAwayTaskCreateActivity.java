package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.commons.utils.T;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 
 * @author LiXinjian
 * @date 2016年6月13日.
 *	上架业务  - 创建上架任务单
 */
public class PutAwayTaskCreateActivity extends BasePdaListActivity implements OnClickListener{
	private TextView save;
	private Intent intent;
	private EditText qtyTV;
	
	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_create;
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawayTaskCreate_headerview, R.string.title_putaway);
	}

	@Override
	protected void setListener() {
		save.setOnClickListener(this);
	}
	
	@Override
	protected void initActivityView() {
		intent = getIntent();
		qtyTV = (EditText) findViewById(R.id.putawaytaskcreate_qty);
		save = (TextView) findViewById(R.id.putawayTaskCreate_saveButton);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.putawayTaskCreate_saveButton:
			
			String shangjiaqty = qtyTV.getText().toString().trim();
			intent.putExtra("shangjiaqty", shangjiaqty);
			PutAwayTaskCreateActivity.this.setResult(RESULT_OK, intent);
			
			T.showShort(context, R.string.save_success);
			PutAwayTaskCreateActivity.this.finish();
			break;
		}
	}

}
