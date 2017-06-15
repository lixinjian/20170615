package com.ds365.erp.wms.pda.view.login.activity;

import com.ds365.commons.utils.PackageUtil;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;

import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
 * @author lxj
 *
 */
public class AboutActivity extends BasePdaActivity{
	private ImageView ds365AboutTrademark;
	private TextView ds365AboutAppName;
	private TextView ds365AboutVersion;
	private TextView ds365AboutCompany;
	
	@Override
	protected void initActivityView() {
		ds365AboutTrademark = (ImageView) findViewById(R.id.ds365about_trademark);
		ds365AboutAppName = (TextView) findViewById(R.id.ds365About_appName);
		ds365AboutVersion = (TextView) findViewById(R.id.ds365about_version_value);
		ds365AboutCompany = (TextView) findViewById(R.id.ds365about_company);
		
		//设置app版本信息
		ds365AboutVersion.setText(PackageUtil.getAppVersionName(context));
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.ds365_about_headview, R.string.about);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.user_about;
	}

	@Override
	protected void setListener() {
		
	}
}
