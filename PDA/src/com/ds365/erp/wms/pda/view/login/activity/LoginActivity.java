package com.ds365.erp.wms.pda.view.login.activity;

import com.ds365.appupdate.AppCheckUpdateParamsModel;
import com.ds365.appupdate.AppUpdateCallback;
import com.ds365.appupdate.AppUpdateService;
import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.ResultCodeEnum;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.message.database.DBManager;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.PackageUtil;
import com.ds365.commons.utils.SharedPreferencesUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.ChangeAppIpView;
import com.ds365.commons.widget.ChangeAppIpView.NewIpSaveListener;
import com.ds365.commons.widget.DropDownListView;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.model.user.LoginAuthParamModel;
import com.ds365.erp.wms.pda.model.user.SessionUser;
import com.ds365.erp.wms.pda.model.user.VerifyCodeModel;
import com.ds365.erp.wms.pda.service.user.UserService;
import com.ds365.erp.wms.pda.service.warehouse.WarehouseService;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{
	private TextView versionNameValue;
	private Button loginButton;
	private DropDownListView<Long> wareHouseNameValue;
	private EditText userNameValue,passwordValue,codeValue;
	private String userName,password;
	private CheckBox checkBox;
	private ImageView backButton;
	private String captchaCode,captchaToken;
	private ImageView captchCodeImage;
	private ChangeAppIpView changeIp;
	private boolean intentFromMessageFlag = false;
	private UserService userService = new UserService();
	private WarehouseService warehouseService = new WarehouseService();
	private AppUpdateService appUpdateService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 禁止横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//引用layout布局
		setContentView(getContentViewId());
		initNavigation();
		initActivityView();
		setListener();
		if(AppConstants.Intent_FROMMESSAGE_KEY.equals(getIntent().getStringExtra(AppConstants.Intent_FROMMESSAGE_KEY))){
			intentFromMessageFlag = true;
		}
	}
	
	protected int getContentViewId() {
		return R.layout.user_login;
	}
	
	protected void initActivityView() {
		initView();
//		getCaptchaToken();
		
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		this.getCaptchaToken();
	}

	protected void initNavigation() {
		appUpdateService = new AppUpdateService(new AppCheckUpdateParamsModel(this,PdaConstants.DOWNLOADAPKNAME,PdaConstants.SYSTEMCODE,new AppUpdateCallback() {
			
			@Override
			public void onVersionRequestSuccess() {
				getCaptchaToken();
				searchWarehouse();
			}
			
			@Override
			public void onVersionRequestFail(String str) {
				T.showShort(LoginActivity.this,str);
				getCaptchaToken();
				searchWarehouse();
			}
			
			@Override
			public void onUpdateRefuseCallBack() {
				getCaptchaToken();
				searchWarehouse(); 
			}
			
			@Override
			public void onForceUpdateRefuseCallBack() {
				finish();
			}
		}));
		appUpdateService.checkUpdate(ConstantUrl.host_update);
	}

	protected void setListener() {
		backButton.setOnClickListener(this);
		loginButton.setOnClickListener(this);
		captchCodeImage.setOnClickListener(this);
		changeIp.setIpSaveListener(new NewIpSaveListener() {

			@Override
			public void onIpSave(String newHost, String newUpdateHost, String newMessageHost) {
				ConstantUrl.ip = newHost;
				ConstantUrl.host_update = newUpdateHost;
				ConstantUrl.host_message = newMessageHost;
				ConstantUrl.init();
				appUpdateService.checkUpdate(ConstantUrl.host_update);
			}
		});
	}
	
	private void initView() {
		versionNameValue = (TextView) findViewById(R.id.login_version_value);
		versionNameValue.setText(PackageUtil.getAppVersionName(this));
		changeIp = (ChangeAppIpView) findViewById(R.id.login_changeIP);
		changeIp.setAppDefaultIp(ConstantUrl.ip,ConstantUrl.host_update,ConstantUrl.host_message);
		
		backButton = (ImageView) findViewById(R.id.login_backButton);
		loginButton = (Button) findViewById(R.id.login_button);
		userNameValue = (EditText) findViewById(R.id.login_userName_editText);
		passwordValue = (EditText) findViewById(R.id.login_password_editText);
		codeValue = (EditText) findViewById(R.id.login_verifyCode_value);
		checkBox = (CheckBox) findViewById(R.id.login_checkBox_rememberPassword);
		checkBox.setChecked(true);
		wareHouseNameValue = (DropDownListView<Long>) findViewById(R.id.login_warehouseName_value);
		captchCodeImage = (ImageView) findViewById(R.id.login_verifyCode_image);
		userNameValue.setText(SharedPreferencesUtils.getData(this, "userName", ""));
		passwordValue.setText(SharedPreferencesUtils.getData(this, "password", ""));
	}
	
	/**
	 * 获取验证码
	 */
	private void getCaptchaToken(){
		userService.getCaptchaToken(new AbstractServiceCallBack<VerifyCodeModel>(this) {
			
			@Override
			public void doSuccess(VerifyCodeModel result) {
				captchaToken = result.getToken();
				StringBuilder sb=new StringBuilder(ConstantUrl.image);
				sb.append("?").append(AppConstants.captchTokenParamsName).append("=").append(captchaToken);
				sb.append("&").append(AppConstants.requestSourceParamsName).append("=").append(AppConstants.requestSourceValue_app);
				ImageLoader.getInstance().displayImage(sb.toString(),captchCodeImage);
			}
		});
	}

	/**
	 * 登陆的网络请求
	 */
	private void login(){
		captchaCode = codeValue.getText().toString().trim();
		userName = userNameValue.getText().toString().trim();
		password = passwordValue.getText().toString().trim();
		
		LoginAuthParamModel loginAuthParamModel = new LoginAuthParamModel();
		loginAuthParamModel.setUserName(userName);
		loginAuthParamModel.setPassword(password);
		loginAuthParamModel.setCaptchaCode(captchaCode);
		loginAuthParamModel.setCaptchaTokenKey(captchaToken);
		loginAuthParamModel.setWarehouseId(wareHouseNameValue.getValue());
		
		userService.login(loginAuthParamModel, new AbstractServiceCallBack<JsonResult<SessionUser>>(this) {
			
			@Override
			public void doSuccess(JsonResult<SessionUser> result) {
				if(result.getCode().equals(ResultCodeEnum.LOGIN_SUCESS.getId())){
					
					if(com.ds365.commons.utils.StringUtils.isBlank(result.getToken())){
						T.showShort(LoginActivity.this, "服务器返回数据非法");//没有返回token
						return;
					}
					AppGlobal.TOKEN=result.getToken();
					
					if (checkBox.isChecked()) {
						SharedPreferencesUtils.saveData(LoginActivity.this, "userName", userName);
						SharedPreferencesUtils.saveData(LoginActivity.this, "password", password);
					} else {
						SharedPreferencesUtils.saveData(LoginActivity.this, "userName", "");
						SharedPreferencesUtils.saveData(LoginActivity.this, "password", "");
					}
					//登录成功之后,把仓库信息和用户信息进行保存.
					GlobalUtils.setSessionUser(result.getData());
					
					GlobalUtils.getSessionUser().setWarehouseId(wareHouseNameValue.getValue());
					GlobalUtils.getSessionUser().setWarehouseName(wareHouseNameValue.getDisplayValue());
					// 收到消息存放 数据库用
					AppGlobal.dbManager = new DBManager(LoginActivity.this,PdaConstants.MESSAGETABLENAME);
					if(!intentFromMessageFlag){
						IntentUtils.startActivity(LoginActivity.this, MainActivity.class);
					}
					finish();
					return;
					
				}else if (result.getCode().equals(ResultCodeEnum.LOGIN_FAILURE.getId())){
					T.showLong(LoginActivity.this, result.getMessage()); 
				}else {
					T.showShort(LoginActivity.this, "服务器返回数据异常");
				}
				getCaptchaToken();
			}
		});
	}
	
	private void searchWarehouse(){
		
		/*warehouseService.searchWarehouse(new AbstractServiceCallBack<List<WarehouseModel>>(context) {
			
			@Override
			public void doSuccess(List<WarehouseModel> result) {
				List<WarehouseModel> wareHouseList=new ArrayList<WarehouseModel>();// 存放仓库对象的List
				wareHouseList.addAll(result);
				Map<Integer,MapItem<Long>> map = new HashMap<Integer,MapItem<Long>>();
				MapItem childMap ;
				for(int i = 0; i < wareHouseList.size(); i++){
					childMap = new MapItem(wareHouseList.get(i).getId(), wareHouseList.get(i).getName());
					map.put(i, childMap);
				}
				wareHouseNameValue.setItemsData(map);
			}
		});*/
		wareHouseNameValue.setUrl(ConstantUrl.warehouse_warehouse_searchByParams);
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button:
			login();
			break;
		case R.id.login_backButton:
			LoginActivity.this.finish();
			break;
		case R.id.login_verifyCode_image:
			getCaptchaToken();
			break;
		}
	}
}
