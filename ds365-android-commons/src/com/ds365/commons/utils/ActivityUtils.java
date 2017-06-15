package com.ds365.commons.utils;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;

public class ActivityUtils {
	// Activity 记录管理
	public static Set<Activity> listActivity = new HashSet<Activity>();

	public static void add(Activity item) {
		if (!listActivity.contains(item)) {
			listActivity.add(item);
		}
	}

	public static void remove(Activity item) {
		if (listActivity.contains(item)) {
			listActivity.remove(item);
		}
	}

	public static void clear() {
		for (Activity activity : listActivity) {
			activity.finish();
		}
		System.exit(0);
	}

}
