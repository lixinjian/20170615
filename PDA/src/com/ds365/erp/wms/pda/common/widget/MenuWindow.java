package com.ds365.erp.wms.pda.common.widget;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaApplication;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkRegisterModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkStateModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkStateQueryParamsModel;
import com.ds365.erp.wms.pda.service.employee.EmployeeWorkService;
import com.ds365.erp.wms.pda.service.user.UserService;
import com.ds365.erp.wms.pda.view.login.activity.AboutActivity;
import com.ds365.erp.wms.pda.view.login.activity.EditPasswordActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MenuWindow extends PopupWindow implements OnClickListener{
	private View conentView;
	private LinearLayout modifyPasswordButton;
	private LinearLayout aboutButton;
	private LinearLayout cancellationBitton;
	private LinearLayout signOutButton;
	private TextView workRegisterButton;
	private Activity context;
	
	private EmployeeWorkService employeeWorkService = new EmployeeWorkService();
	private UserService userService = new UserService();
	private String workOn = "上班";
	private String workOff = "下班";
	
	public MenuWindow(Activity context) {
		super(context);
		this.context = (Activity) context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.user_popup_window, null);

		Display display = context.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int w = size.x;
		int h = size.y;
//		int h = context.getWindowManager().getDefaultDisplay().getHeight();
//		int w = context.getWindowManager().getDefaultDisplay().getWidth();

		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w / 2);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.popwin_anim_style);
		initView();
//		searchPageForWorkStateByParams();
	}

	//在这里查询一下员工的上班状态,如果是为上班,按钮就显示未上班,如果是已上班就显示已上班
	private void searchPageForWorkStateByParams() {
		
		EmployeeWorkStateQueryParamsModel employeeWorkStateQueryParamsModel = new EmployeeWorkStateQueryParamsModel();
		
		employeeWorkService.searchPageForWorkStateByParams(employeeWorkStateQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<EmployeeWorkStateModel>>(context) {

					@Override
					public void doSuccess(QueryResult<EmployeeWorkStateModel> data) {
						if (workOn.equals(data.getRecords().get(0).getWorkState().getName())) {
							workRegisterButton.setText(workOn);
							workRegisterButton.setBackgroundResource(R.color.blue);
						}
						if (workOff.equals(data.getRecords().get(0).getWorkState().getName())) {
							workRegisterButton.setText(workOff);
							workRegisterButton.setBackgroundResource(R.color.red);
						}
					}
				});
	}
	
	private void initView() {
		modifyPasswordButton = (LinearLayout) conentView.findViewById(R.id.modifyPassword);
		aboutButton = (LinearLayout) conentView.findViewById(R.id.version);
		cancellationBitton = (LinearLayout) conentView.findViewById(R.id.cancellation);
		signOutButton = (LinearLayout) conentView.findViewById(R.id.logout);
		workRegisterButton = (TextView) conentView.findViewById(R.id.workRegister_button);
		cancellationBitton.setOnClickListener(this);
		signOutButton.setOnClickListener(this);
		modifyPasswordButton.setOnClickListener(this);
		aboutButton.setOnClickListener(this);
		workRegisterButton.setOnClickListener(this);
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
		} else {
			this.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		int ID = v.getId();
		switch (ID) {
		case R.id.modifyPassword:
			this.dismiss();
			IntentUtils.startActivity(context, EditPasswordActivity.class);
			break;
		case R.id.version:
			this.dismiss();
			IntentUtils.startActivity(context, AboutActivity.class);
			break;
		case R.id.cancellation:
			this.dismiss();
			DialogUtils.createConfirmDialog(context, context.getString(R.string.dialog_cancel_title)
					, context.getString(R.string.dialog_cancel_detail) , true, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							logout();
						}
					});
			break;
		case R.id.logout:
			this.dismiss();
			DialogUtils.createConfirmDialog(context, context.getString(R.string.dialog_signout_title)
					, context.getString(R.string.dialog_signout_detail) , true, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							exit();
						}
					});
			break;
		case R.id.workRegister_button:

			final EmployeeWorkRegisterModel employeeWorkRegisterModel = new EmployeeWorkRegisterModel();
			
			if (workOn.equals(workRegisterButton.getText().toString())) {
				DialogUtils.createConfirmDialog(context, context.getString(R.string.dialog_register_for_work_off)
						, context.getString(R.string.dialog_register_for_work_off_detail) , true, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//下班登记
								
								employeeWorkService.registerForWorkOff(employeeWorkRegisterModel, new AbstractServiceCallBack<JsonResult>(context) {
									
									@Override
									public void doSuccess(JsonResult data) {
										workRegisterButton.setText(workOff);
										workRegisterButton.setBackgroundResource(R.color.red);
									}
								});
							}
						});
			}
			if (workOff.equals(workRegisterButton.getText().toString())) {
				DialogUtils.createConfirmDialog(context, context.getString(R.string.dialog_register_for_work_on)
						, context.getString(R.string.dialog_register_for_work_on_detail) , true, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//上班登记
								
								employeeWorkService.registerForWorkOn(employeeWorkRegisterModel, new AbstractServiceCallBack<JsonResult>(context) {
									
									@Override
									public void doSuccess(JsonResult data) {
										workRegisterButton.setText(workOn);
										workRegisterButton.setBackgroundResource(R.color.blue);
									}
								});
							}
						});
			break;
			}
		}
	}
	
	/**
	 * 用户安全退出程序
	 */
	private void exit(){
		userService.logout(new AbstractServiceCallBack<String>(context) {
			
			@Override
			public void doSuccess(String data) {
				PdaApplication.exit();
			}

			@Override
			public void doFaile(String str) {
				PdaApplication.exit();
			}
		});
	}

	/**
	 * 用户注销
	 */
	private void logout() {
		userService.logout(new AbstractServiceCallBack<String>(context) {
			
			@Override
			public void doSuccess(String data) {
				PdaApplication.logout(context);
			}
		});
	}
}


