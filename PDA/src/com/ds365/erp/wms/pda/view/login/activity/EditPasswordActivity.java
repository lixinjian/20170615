package com.ds365.erp.wms.pda.view.login.activity;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.SharedPreferencesUtils;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.model.user.LoginUserCreateParamsModel;
import com.ds365.erp.wms.pda.service.user.UserService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 修改密码
 */
public class EditPasswordActivity extends BasePdaActivity{
	private Button confirmButton;
	private EditText oldPasswordValue;
	private EditText newPasswordValue;
	private EditText confirmNewPasswordValue;
	
	private UserService userService = new UserService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.user_edit_password;
	}
	
	@Override
	protected void initActivityView() {
		confirmButton = (Button) findViewById(R.id.editPassword_confirm_button);
		oldPasswordValue = (EditText) findViewById(R.id.editPassword_oldPassword_value);
		newPasswordValue = (EditText) findViewById(R.id.editPassword_newPassword_value);
		confirmNewPasswordValue = (EditText) findViewById(R.id.editPassword_confirmNewPassword_value);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.editPassword_headview, R.string.modify_password);
	}

	@Override
	protected void setListener() {
		confirmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editPassword();
			}
		});
	}
	
	protected void editPassword() {
		String oldPassword = oldPasswordValue.getText().toString();
		final String newPassword = newPasswordValue.getText().toString();
		String confirmNewPassword = confirmNewPasswordValue.getText().toString();
		Long userId = GlobalUtils.getSessionUser().getUserId();
		
		LoginUserCreateParamsModel loginUserCreateParamsModel = new LoginUserCreateParamsModel();
		loginUserCreateParamsModel.setPassword(newPassword);
		loginUserCreateParamsModel.setOldPassword(oldPassword);
		loginUserCreateParamsModel.setEmployeeId(userId);
		
		//判断输入是否为空
		StringBuilder message = new StringBuilder();
		if (TextUtils.isEmpty(oldPassword)) {
			message.append("请输入原密码\r\n");
		}
		if (TextUtils.isEmpty(newPassword)) {
			message.append("请输入新密码\r\n");
		}
		if (TextUtils.isEmpty(confirmNewPassword)) {
			message.append("请确认新密码\r\n");
		}
		if (!newPassword.equals(confirmNewPassword)) {
			message.append("两次密码不一致");
		}
		if (message.length() != 0) {
			T.showShort(context, message);
			return;
		}
		
		userService.editPassword(loginUserCreateParamsModel, new AbstractServiceCallBack<String>(context) {
			
			@Override
			public void doSuccess(String data) {
				SharedPreferencesUtils.saveData(context, "password", newPassword);
				T.showShort(context, R.string.modifypassword_success);
				finish();
			}
		});
	}
}
