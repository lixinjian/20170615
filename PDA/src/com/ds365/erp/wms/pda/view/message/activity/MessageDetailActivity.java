package com.ds365.erp.wms.pda.view.message.activity;

import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.message.model.MessageDatebaseModel;
import com.ds365.commons.utils.DateFormatUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.headtitlebar.SubHeadTitleBar.ButtonClickListener;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaMessageActivity;
import com.ds365.erp.wms.pda.view.login.activity.LoginActivity;
import com.ds365.erp.wms.pda.view.login.activity.MainActivity;

import android.view.KeyEvent;
import android.widget.TextView;

/**
 * 说明 ：消息详情 
 */
public class MessageDetailActivity extends BasePdaMessageActivity {

	private MessageDatebaseModel message;
	private DateField time;
	private TextView content;

	@Override
	public int getContentViewId() {
		return R.layout.message_message_detail;
	}
	
	@Override
	protected void initNavigation() {
		message = (MessageDatebaseModel) getIntent().getSerializableExtra(PdaConstants.MESSAGE_SER_KEY);
		initHeadView(R.id.messageDetail_title,message.getTitle());
	}

	@Override
	protected void initActivityView() {
		time = (DateField) findViewById(R.id.messageDetail_time);
		content = (TextView) findViewById(R.id.messageDetail_content);
		time.setValue(DateFormatUtils.stringToDateForyyyyMMddHHmmss(message.getSendTime()));
		content.setText(message.getContent());
	}

	@Override
	protected void setListener() {
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(AppGlobal.dbManager!=null&&message.getReadFlag()==0){
			AppGlobal.dbManager.updateMessageReadFlag(message.get_id());
		}
	}

	@Override
	protected void checkUserLogin() {
		if(GlobalUtils.getSessionUser()==null||AppGlobal.dbManager==null){
			intentMainFlag = true;
			setHeadLeft(new ButtonClickListener() {
				
				@Override
				public void setLeftListener() {
					IntentUtils.startActivity(context,MainActivity.class);
				}
			});
			IntentUtils.startActivity(context,LoginActivity.class,AppConstants.Intent_FROMMESSAGE_KEY,AppConstants.Intent_FROMMESSAGE_KEY,null);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
            if(intentMainFlag){
            	IntentUtils.startActivity(context,MainActivity.class);
            	return true;
            }
        }
        return super.onKeyDown(keyCode, event);
	}
}
