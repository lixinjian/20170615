package com.ds365.commons.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 基于adapter的一个封装里边主要是增删改查的一些操作
 * 
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	protected List<T> list;
	protected Context context;

	public CommonAdapter(Context context) {
		super();
		this.context = context;
	}

	public CommonAdapter(Context context, List<T> list) {
		super();
		this.list = list;
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public void addItemsBefore(T item) {
		if (list == null) {
			list = new ArrayList<T>();
		}
		if (!isSameItem(item)) {
			list.add(0, item);
		}
	}

	public void addItemsAfter(T item) {
		if (list == null) {
			list = new ArrayList<T>();
		}
		if (!isSameItem(item))
			list.add(item);
		notifyDataSetChanged();
	}

	public void addItemTargetLocation(int position, T item) {
		if (list == null) {
			list = new ArrayList<T>();
		}
		if (!isSameItem(item))
			list.add(position, item);
		notifyDataSetChanged();
	}

	public void addAllAfter(List<T> item) {
		if (list == null) {
			list = new ArrayList<T>();
		}
		if (item != null) {
			list.addAll(item);
			notifyDataSetChanged();
		}
	}

	public void addAllBefore(List<T> item) {
		if (list == null) {
			list = new ArrayList<T>();
		}
		if (item != null) {
			list.addAll(0, item);
			notifyDataSetChanged();
		}
	}

	public void remove(int position) {
		if (list != null) {
			list.remove(position);
			notifyDataSetChanged();
		}
	}

	public void clear() {
		if (list != null) {
			list.clear();
			notifyDataSetChanged();
		}
	}

	/**
	 * 去重 这时候建议复写item的equls 方法
	 * 
	 * @param item
	 * @return
	 */
	public boolean isSameItem(T item) {
		if (list != null) {
			return list.contains(item);
		}
		return false;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public T getItem(int arg0) {
		// TODO Auto-generated method stub
		return list == null ? null : list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		HolderView view = null;
		if (contentView == null) {

			contentView = View.inflate(context, getContentViewId(), null);
			view = getHoldView(position, contentView, parent);
			contentView.setTag(view);
		} else
			view = (HolderView) contentView.getTag();
		setItemView(position, view, list.get(position));
		return contentView;
	}

	/*** 设置 布局文件 */
	public abstract int getContentViewId();

	/*** 初始化 布局 文件 */
	public abstract HolderView getHoldView(int position, View contentView, ViewGroup parent);

	/*** 设置具体数值 **/
	public abstract void setItemView(int position, HolderView contentView, T item);

	public abstract class HolderView {

	}
}
