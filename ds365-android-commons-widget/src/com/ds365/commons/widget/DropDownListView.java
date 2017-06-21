package com.ds365.commons.widget;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.MapItem;
import com.ds365.commons.utils.T;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 下拉列表框控件
 *
 */
public class DropDownListView<E extends Serializable> extends LinearLayout{

	private TextView editText;
	private PopupWindow popupWindow = null;
	private Map<Integer,MapItem<E>> map;
	private View mView;
	private int select = 0;
	private SelectChangeListener<E> selectChangeListener;
	private String displayFiled,valueFiled;
	private Context context;
	private int valueFiledType;
	private boolean allFlag;

	public DropDownListView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public void setSelectChangeListener(SelectChangeListener<E> selectChangeListener){
		this.selectChangeListener = selectChangeListener;
	}

	public DropDownListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.dropdownAttrs);//TypedArray是一个数组容器 
		valueFiledType = a.getInt(R.styleable.dropdownAttrs_valueFiledType, 1);
		displayFiled = a.getString(R.styleable.dropdownAttrs_displayFiled);//防止在XML文件里没有定义，就加上
		valueFiled = a.getString(R.styleable.dropdownAttrs_valueFiled);//同上,这里的属性是:名字_属性名 
		allFlag = a.getBoolean(R.styleable.dropdownAttrs_allFlag, false);
		a.recycle();//我的理解是：返回以前取回的属性，供以后使用。以前取回的可能就是textSize和textColor初始化的那段 
		this.context = context;
		initView();
	}

	public void setPosition(int position){
		select = position;
		editText.setText(map.get(select).getDisplayValue());
		if(selectChangeListener!=null){
			selectChangeListener.onItemSelect(map.get(select).getValue());
		}
	}

	public void setValue(T value){
		for(int i = 0;i<map.size();i++){
			if(map.get(i).getValue().equals(value)){
				setPosition(i);
				break;
			}
		}
	}

	public String getDisplayValue(){
		return map.get(select).getDisplayValue();
	}

	public int getPosition(){
		return select;
	}

	public E getValue(){
		return map.get(select).getValue();
	}

	public void initView(){
		LayoutInflater layoutInflater =  (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = layoutInflater.inflate(R.layout.dropdownlist_view, this,true);
		editText= (TextView)mView.findViewById(R.id.text);
		mView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(map!=null&&map.size()>0){
					if(popupWindow == null ){
						showPopWindow();
					}else{
						closePopWindow();
					}
				}else{
					T.showShort(getContext(), R.string.toastinfo);
				}
			}
		});
	}
	/**
	 * 打开下拉列表弹窗
	 */
	private void showPopWindow() {  
		// 加载popupWindow的布局文件  
		LayoutInflater layoutInflater =  (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View contentView  = layoutInflater.inflate(R.layout.dropdownlist_popupwindow, null,false);
		ListView listView = (ListView)contentView.findViewById(R.id.listView);
		listView.setAdapter(new XCDropDownListAdapter(getContext()));
		popupWindow = new PopupWindow(contentView,mView.getWidth(),LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.transparent));
		popupWindow.setOutsideTouchable(true);
		popupWindow.showAsDropDown(this);
	}
	/**
	 * 关闭下拉列表弹窗
	 */
	private void closePopWindow(){
		popupWindow.dismiss();
		popupWindow = null;
	}
	/**
	 * 设置数据
	 * @param
	 */
	public void setItemsData(Map<Integer,MapItem<E>> hashMap){
		map = hashMap;
		setPosition(0);
	}

	public void setItemsDataAddDefault(Map<Integer,MapItem<E>> map){
		Map<Integer,MapItem<E>> newMap = new HashMap<Integer,MapItem<E>>();
		MapItem<E> childmap;
		childmap = new MapItem<E>(null,"全部");
		newMap.put(0, childmap);
		for(int i=1;i<=map.size();i++){
			newMap.put(i,map.get(i-1));
		}
		setItemsData(newMap);
	}

	public void setUrl(String url){
		JsonParser<JsonResult<List<JsonNode>>> jsonParser = new JsonParser<JsonResult<List<JsonNode>>>(){};
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(url);
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<JsonNode>>>(jsonParser,new AbstractServiceCallBack<List<JsonNode>>(context) {

			@Override
			public void doSuccess(List<JsonNode> data) {
				if (null != data && data.size() > 0) {
					Map<Integer,MapItem<E>> map = new HashMap<Integer,MapItem<E>>();
					MapItem<E> childmap;
					for(int i=0;i<data.size();i++){

						JsonNode value=data.get(i);
						for(String v: valueFiled.split("\\.")){
							value=value.get(v);
						}
						JsonNode displayValue = data.get(i);
						for(String v: displayFiled.split("\\.")){
							displayValue=displayValue.get(v);
						}
						if(valueFiledType==0){
							childmap = new MapItem(value.asInt(), displayValue.asText());
						}else if(valueFiledType==2){
							childmap = new MapItem(value.asLong(),displayValue.asText());
						}else{
							childmap = new MapItem(value.asText(),displayValue.asText());
						}
						map.put(i,childmap);
					}
					if(allFlag){
						setItemsDataAddDefault(map);
					}else{
						setItemsData(map);
					}
				}
			}
		}) {});
	}

	/**
	 * 数据适配器
	 */
	class XCDropDownListAdapter extends BaseAdapter{

		Context mContext;
		LayoutInflater inflater;

		public XCDropDownListAdapter(Context ctx){
			mContext  = ctx;
			inflater = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return map.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// 自定义视图
			ListItemView listItemView = null;
			if (convertView == null) {
				// 获取list_item布局文件的视图
				convertView = inflater.inflate(R.layout.dropdown_list_item, null);

				listItemView = new ListItemView();
				// 获取控件对象
				listItemView.tv = (TextView) convertView
						.findViewById(R.id.tv);
				// 设置控件集到convertView
				convertView.setTag(listItemView);
			} else {
				listItemView = (ListItemView) convertView.getTag();
			}
			// 设置数据
			listItemView.tv.setText(map.get(position).getDisplayValue());
			listItemView.tv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					setPosition(position);
					closePopWindow();
				}
			});
			return convertView;
		}

	}
	private class ListItemView{
		TextView tv;
	}

	public interface SelectChangeListener<T extends Serializable> {
		void onItemSelect(T selectValue);
	}
}
