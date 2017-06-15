package com.ds365.erp.wms.pda.view.common.fragment;

import com.ds365.commons.json.RequestParamsModel;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.headtitlebar.MainHeadTitleBar;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 这里是关于Fragment的基础封装类
 */
@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment{
	
	protected int start; //控制分页数据开始的条数
	protected RequestParamsModel params = new RequestParamsModel();
	
	protected MainHeadTitleBar headView;
	//定义一个View用来保存Fragment创建的时候使用打气筒工具进行的布局获取对象的存储
	protected View view;
	/**
	 * 当Fragment进行创建的时候执行的方法
	 * 在这里发送网络请求
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initFragmentRequestNet();
	}
	
	/**
	 * 这个方法是关于Fragment完成创建的过程中，进行界面填充的方法,该方法返回的是一个view对象
	 * 在这个对象中封装的就是Fragment对应的布局
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view=initFragmentView(inflater);
		return view;
	}
	/**
	 * 这个方法当onCreateView方法中的view创建完成之后，执行
	 * 在inflate完成view的创建之后，可以将对应view中的各个控件进行查找findViewById
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		initFragmentChildView(view);
	}

	/**
	 * 这个方法是在Fragment完成创建操作之后，进行数据填充操作的时候执行的方法
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initFragmentData(savedInstanceState);
	}

	//下面是关于抽象方法的定义，需要子类必须进行实现
	protected abstract void initFragmentRequestNet();
	protected abstract View initFragmentView(LayoutInflater inflater);
	protected abstract void initFragmentChildView(View view);
	protected abstract void initFragmentData(Bundle savedInstanceState);
	
	
	/**
	 * 实例化头部
	 */
	public void initHeadView(int headViewId, int titleId, int warehouseAddressId, int userNameId, int rightImageId) {
		headView = (MainHeadTitleBar) findViewById(headViewId);
		if (headView != null) {
			
			headView.setTitle(titleId);
			
//			headView.setWarehouseAddress(warehouseAddressId);
			
			headView.setUserName(userNameId);
			
			headView.setRightImg(rightImageId);
			
		}
	}
	
	public View findViewById(int id) {
		View itemView = null;
		if (view != null) {
			itemView = view.findViewById(id);
		}
		return itemView;
	}
	public ImageView getRightImage(){
		if(headView != null){
			return headView.getRightImage();
		}
		return  null;
	}
	
	/**
	 * 设置第一页的start,limit
	 */
	public void setFirstPage(){ 
		start = 0;
		params.getParams().put("start", String.valueOf(start));
		params.getParams().put("limit", String.valueOf(PdaConstants.LIMIT));
	}

	/**
	 * 设置下一页的start,limit
	 */
	public void setNextPage(){
		start += Integer.valueOf(PdaConstants.LIMIT);
		params.getParams().put("start", String.valueOf(start));
		params.getParams().put("limit", String.valueOf(PdaConstants.LIMIT));
	}
	
}
