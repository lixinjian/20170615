package com.ds365.commons.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ds365.commons.json.JacksonUtils;

import android.widget.ListView;

public class ListViewFindByFieldUtils<E extends Serializable> {

	private ListView listView;
	private List<E> list;
	private CommonSelectAdapter<E> adapter;

	public ListViewFindByFieldUtils(ListView listView,List<E> list,CommonSelectAdapter<E> adapter){
		this.adapter = adapter;
		this.list = list;
		this.listView = listView;
	}

	public void findItemByField(String valueField,String value){
		for(int i = 0; i<list.size();i++){
			String fieldValue = findItem(list.get(i),valueField);
			if(fieldValue.contains(value)){
				changeVisiblePosition(i);
				return;
			}
		}
		T.showShort(ApplicationContextUtils.getContext(),"未找到符合条件的子项！");
	}
	
	public void findItemByFields(String firsetValueField,String secondValueField,String value){
		for(int i = 0; i<list.size();i++){
			String fieldValue = findItem(list.get(i),firsetValueField);
			String secondFieldValue = findItem(list.get(i),secondValueField);
			if(fieldValue.contains(value)||secondFieldValue.contains(value)){
				changeVisiblePosition(i);
				return;
			}
		}
		T.showShort(ApplicationContextUtils.getContext(),"未找到符合条件的子项！");
	}
	
	private void changeVisiblePosition(int showPosition){
		adapter.changeSelected(showPosition);
		if(listView.getFirstVisiblePosition()>showPosition){
			listView.setSelection(showPosition);
		}else{
			listView.setSelectionFromTop(showPosition,listView.getChildAt(listView.getFirstVisiblePosition()).getTop());
		}
	}
	
	private String findItem(E listItem,String valueField){
		Map<String, Object> map = JacksonUtils.objectToMap(listItem);
		Object fieldValue = null;
		String[] valueFields = valueField.split("\\.");
		if(valueFields.length>1){
			fieldValue = map.get(valueFields[0]);
			for(int j = 1;j<valueFields.length;j++){
				Map<String, Object> itemMap = JacksonUtils.objectToMap(fieldValue);
				fieldValue = itemMap.get(valueFields[j]);
			}
		}else{
			fieldValue = map.get(valueField);
		}
		return String.valueOf(fieldValue);
	}

	public void findItemByFields(String firstValueField,String firstValue,String secondValueField,String secondValue){
		for(int i = 0; i<list.size();i++){
			String firsetFieldValue = findItem(list.get(i),firstValueField);
			String secondFieldValue = findItem(list.get(i),secondValueField);
			if(firsetFieldValue.contains(firstValue)&&secondFieldValue.contains(secondValue)){
				changeVisiblePosition(i);
				return;
			}
		}
		T.showShort(ApplicationContextUtils.getContext(),"未找到符合条件的子项！");
	}
}
