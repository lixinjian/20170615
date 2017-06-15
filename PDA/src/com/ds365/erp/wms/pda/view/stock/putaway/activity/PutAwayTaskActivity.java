package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.TabStateUtil;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.fragment.BaseFragment;
import com.ds365.erp.wms.pda.view.common.fragment.FragmentFactory;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

@SuppressLint("NewApi")
public class PutAwayTaskActivity extends BasePdaListActivity implements OnClickListener{
	
	private BaseFragment baseFragment;
	
	private TextView putAwayByChoiceButton;
	private TextView putAwayByBillCodeButton;
	
	@Override
	public void onClick(View v) {
		 switch(v.getId()){

         case R.id.putAwayTask_putAwayByChoiceButton:
        	 TabStateUtil.changeTwoButtonState(PutAwayTaskActivity.this, putAwayByChoiceButton, putAwayByBillCodeButton);
             crateFragment(4);
             break;
         case R.id.putAwayTask_putAwayByBillCodeButton:
        	 TabStateUtil.changeTwoButtonState(PutAwayTaskActivity.this, putAwayByBillCodeButton, putAwayByChoiceButton);
             crateFragment(5);
             break;
		 }
	}

	private void crateFragment(int checkedId){
        baseFragment=(BaseFragment) FragmentFactory.createMainActiviryFragment(checkedId);
        if(null!=baseFragment){
            getFragmentManager().beginTransaction().replace(R.id.putawayTask_framLayout,baseFragment).commitAllowingStateLoss();
        }
    }
    
	@Override
	protected void initActivityView() {
		putAwayByChoiceButton = (TextView) findViewById(R.id.putAwayTask_putAwayByChoiceButton);
		putAwayByBillCodeButton = (TextView) findViewById(R.id.putAwayTask_putAwayByBillCodeButton);
		
		putAwayByChoiceButton.setOnClickListener(this);
		putAwayByBillCodeButton.setOnClickListener(this);
		crateFragment(5);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawaytask_headerview, R.string.putaway_task_list);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task;
	}

	@Override
	protected void setListener() {
		
	}
}
