package com.ds365.erp.wms.pda.view.login.activity;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.message.CheckUserLoginListener;
import com.ds365.commons.message.CheckUserLoginResultListener;
import com.ds365.commons.message.MessageReceivedListener;
import com.ds365.commons.message.NotificationService;
import com.ds365.commons.message.model.MessageModel;
import com.ds365.commons.utils.ActivityUtils;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.BadgeView;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaApplication;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkRegisterModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkStateModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkStateQueryParamsModel;
import com.ds365.erp.wms.pda.model.user.CheckUserLoginModel;
import com.ds365.erp.wms.pda.service.employee.EmployeeWorkService;
import com.ds365.erp.wms.pda.service.user.UserService;
import com.ds365.erp.wms.pda.view.common.fragment.BaseFragment;
import com.ds365.erp.wms.pda.view.common.fragment.FragmentFactory;
import com.ds365.erp.wms.pda.view.message.MessageCategoryListFragment;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements View.OnClickListener{

	private long exitTime = 0;
    private RadioButton enterWarehouse;
    private RadioButton outWarehouse;
    private RadioButton stock;
    private RadioButton message;
    private RadioButton query;
    
	private BadgeView messageBadge;
	private ImageView badgeView;
	private UserService userService = new UserService();
    //定义BaseFragment，辅助完成FragmentFactory的操作
    private BaseFragment baseFragment;

	private static final int CHANGECOUNT=131;
	
	private int select = 0;
	
	private EmployeeWorkService employeeWorkService = new EmployeeWorkService();
    
    private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CHANGECOUNT:
				setMessageCount();
				if(select == 4){
					MessageCategoryListFragment messageCategoryListFragment =(MessageCategoryListFragment)baseFragment;
					messageCategoryListFragment.resetBadgeCount();
				}
				break;
			}
		}
	};
	
	@Override
	protected void onStart() {
		super.onStart();
		setMessageCount();
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.main_activity);

        enterWarehouse=(RadioButton) findViewById(R.id.mainActivity_radioButton_enterWarehouse);
        outWarehouse=(RadioButton) findViewById(R.id.mainActivity_radioButton_outWarehouse);
        stock=(RadioButton) findViewById(R.id.mainActivity_radioButton_stock);
        message=(RadioButton) findViewById(R.id.mainActivity_radioButton_message);
        query=(RadioButton) findViewById(R.id.mainActivity_radioButton_query);
        badgeView = (ImageView) findViewById(R.id.mainActivity_badgeView);

        enterWarehouse.setOnClickListener(this);
        outWarehouse.setOnClickListener(this);
        stock.setOnClickListener(this);
        message.setOnClickListener(this);
        query.setOnClickListener(this);

        enterWarehouse.setChecked(true);
        crateFragment(0);
        ActivityUtils.add(this);
        
        messageBadge = new BadgeView(MainActivity.this, badgeView);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        messageBadge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        
       	//设置接收消息地址
		AppGlobal.appServiceUrl = ConstantUrl.userServiceUrl+AppGlobal.TOKEN;
		
		AppGlobal.checkUserLoginListener = new CheckUserLoginListener() {

			@Override
			public void checkUserLogin(final CheckUserLoginResultListener checkUserLoginResultListener) {
				JsonParser<JsonResult<CheckUserLoginModel>> checkUserLoginParser = new JsonParser<JsonResult<CheckUserLoginModel>>(){};
				userService.checkLogin(new AbstractResponseHandler<JsonResult<CheckUserLoginModel>>(checkUserLoginParser) {

					@Override
					public void doSuccess(JsonResult result) {
						checkUserLoginResultListener.onCheckOk();
					}

					@Override
					public void doFaile(String str) {
						checkUserLoginResultListener.onCheckFail();
					}
					
				});
			}
		};
		
		//设置消息回调重新查询数量
		AppGlobal.messageReceivedListener = new MessageReceivedListener(){
			@Override
			public void onMessageReceived() {
				mHandler.sendEmptyMessage(CHANGECOUNT);
			}

			@Override
			public JsonParser getParser() {
				JsonParser<MessageModel> jsonParser = new JsonParser<MessageModel>(){};
				return jsonParser;
			}
		};
		//开启消息服务				
		startService(new Intent(this,NotificationService.class));

//		searchPageForWorkStateByParams();
        
    }
    
    /**
     * 当用户登录成功之后首先去查询员工上班状态,如果处于未上班,则弹窗提示是否要进行上班登记,如果已上班则不做操作
     */
    private void searchPageForWorkStateByParams(){
    	
		final EmployeeWorkStateQueryParamsModel employeeWorkStateQueryParamsModel = new EmployeeWorkStateQueryParamsModel();

		employeeWorkService.searchPageForWorkStateByParams(employeeWorkStateQueryParamsModel, new AbstractServiceCallBack<QueryResult<EmployeeWorkStateModel>>(MainActivity.this) {
			
			@Override
			public void doSuccess(QueryResult<EmployeeWorkStateModel> result) {
				//如果未上班,弹窗提示,否则不做操作
				if (result != null) {
					DialogUtils.createConfirmDialog(MainActivity.this, getString(R.string.dialog_register_for_work_on)
							, getString(R.string.dialog_register_for_work_on_detail), true, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									//点击弹窗确定按钮,进行上班登记
									
									final EmployeeWorkRegisterModel employeeWorkRegisterModel = new EmployeeWorkRegisterModel();
									
									employeeWorkService.registerForWorkOn(employeeWorkRegisterModel, new AbstractServiceCallBack<JsonResult>(MainActivity.this) {
										
										@Override
										public void doSuccess(JsonResult data) {
											T.showShort(MainActivity.this, "上班登记成功");
										}
									});
								}
							});
				}
			}
		});
    }

	private void setMessageCount(){
		int messageCount = AppGlobal.dbManager.getAllNotReadCount();
		if(messageCount!=0){
			messageBadge.setText(String.valueOf(messageCount));
			messageBadge.setTextSize(9);
			messageBadge.show();
		}else{
			messageBadge.hide();
		}
	}
	
    @Override
    public void onClick(View v) {
        /**
         * 根据不同的点击创建不同的Fragment
         * 一个逻辑需求：
         * ？？用户从任何模块进入到登陆模块，在登录模块点击返回操作的时候，要返回到前一次的位置
         * 思路：设置一个sp变量存储点击所在的位置，当用户点击登录位置的返回操作的时候，读取该位置信息，并返回到之前的界面
         */
        switch(v.getId()){

            case R.id.mainActivity_radioButton_enterWarehouse:
            	select = 0;
                crateFragment(0);
                break;
            case R.id.mainActivity_radioButton_outWarehouse:
            	select = 1;
                crateFragment(1);
                break;
            case R.id.mainActivity_radioButton_stock:
            	select = 2;
                crateFragment(2);
                break;
            case R.id.mainActivity_radioButton_query:
            	select = 3;
            	crateFragment(3);
            	break;
            case R.id.mainActivity_radioButton_message:
            	select = 4;
                crateFragment(4);
                break;
            
        }
    }

    /**
     * 该方法完成的操作是将上部分的FrameLayout使用创建的Fragment进行替换
     * @param checkedId
     */
	private void crateFragment(int checkedId){
        baseFragment=(BaseFragment) FragmentFactory.createMainActiviryFragment(checkedId);
        if(null!=baseFragment){
            System.out.println("BaseFragment==========替换了新的Fragment");

            /**
             * getSupportFragmentManager()方法在继承自FragmentActivity的Activity中才可以使用
             */
            getFragmentManager().beginTransaction().replace(R.id.mainActivity_framLayout,baseFragment).commitAllowingStateLoss();
        }else{
            Toast.makeText(this, "空的", Toast.LENGTH_LONG).show();
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
            if((System.currentTimeMillis()-exitTime) > 2000){  
                T.showShort(getApplicationContext(), R.string.signout_pressagain);
                exitTime = System.currentTimeMillis();   
            } else {
        		userService.logout(new AbstractServiceCallBack<String>(this) {

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
            return true;   
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	ActivityUtils.remove(this);
    }
}
