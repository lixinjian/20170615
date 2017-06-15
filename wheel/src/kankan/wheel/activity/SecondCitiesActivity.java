package kankan.wheel.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import kankan.wheel.R;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/** 
 * @author  作者 yxz 
 * @date 创建时间：2016-1-11 下午4:19:13 
 */

public class SecondCitiesActivity extends Activity implements
OnWheelChangedListener {
	/**
	 * 把全国的省市区的信息以json的格式保存，解析完成后赋值为null
	 */
	/**
	 * 省的WheelView控件
	 */
	private WheelView mProvince;
	/**
	 * 市的WheelView控件d
	 */
	private WheelView mCity;
	/**
	 * 区的WheelView控件
	 */
	private WheelView mArea;
	/**
	 * 所有省
	 */
	private String[] mProvinceDatas;
	private String[] mProvinceDatasId;
	/**
	 * key - 省 value - 市s
	 */
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	private Map<String, String[]> mCitisDatasMapId = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区s
	 */
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();
	private Map<String, String[]> mAreaDatasMapId = new HashMap<String, String[]>();

	/**
	 * 当前省的名称和 id
	 */
	private String mCurrentProviceName;
	private String mCurrentProviceId;
	/**
	 * 当前市的名称和id
	 */
	private String mCurrentCityName;
	private String mCurrentCityId;

	Map<String, String> paramMap = new HashMap<String, String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.citys);
		mProvince = (WheelView) findViewById(R.id.id_province);
		mCity = (WheelView) findViewById(R.id.id_city);
		mArea = (WheelView) findViewById(R.id.id_area);
		mArea.setVisibility(View.GONE);
		try {
			JSONObject mJsonObj = new JSONObject(initJsonData());
			JSONArray jsonArray = mJsonObj.getJSONArray("location");
			mProvinceDatas = new String[jsonArray.length()];
			mProvinceDatasId = new String[jsonArray.length()];

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
				String province = jsonP.getString("province_name");// 省名字
				String province_id = jsonP.getString("province_id");// 省名字

				mProvinceDatas[i] = province;
				mProvinceDatasId[i] = province_id;

				JSONArray jsonCs = jsonP.getJSONArray("city");

				String[] mCitiesDatas = new String[jsonCs.length()];
				String[] mCitiesDatasId = new String[jsonCs.length()];
				for (int j = 0; j < jsonCs.length(); j++) {
					JSONObject jsonCity = jsonCs.getJSONObject(j);
					String city = jsonCity.getString("city_name");// 市名字
					String cityId = jsonCity.getString("city_id");// 市名字
					mCitiesDatas[j] = city;
					mCitiesDatasId[j] = cityId;
					JSONArray jsonAreas = jsonCity.getJSONArray("county");
					String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
					String[] mAreasDatasId = new String[jsonAreas.length()];// 当前市的所有区
					for (int k = 0; k < jsonAreas.length(); k++) {
						String area = jsonAreas.getJSONObject(k).optString(
								"county_name");// 区域的名称
						String areaId = jsonAreas.getJSONObject(k).optString(
								"county_id");// 区域的名称
						mAreasDatas[k] = area;
						mAreasDatasId[k] = areaId;
					}
					mAreaDatasMap.put(city, mAreasDatas);
					mAreaDatasMapId.put(cityId, mAreasDatasId);
				}
				mCitisDatasMap.put(province, mCitiesDatas);
				mCitisDatasMapId.put(province_id, mCitiesDatasId);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		updateCities();

		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(
				this, mProvinceDatas));
		// 添加change事件
		mProvince.addChangingListener(this);
		// 添加change事件
		mCity.addChangingListener(this);
		mProvince.setVisibleItems(3);
		mCity.setVisibleItems(3);
	}

	private void updateAreas() {
		int pCurrent = mCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		mCurrentCityId = mCitisDatasMapId.get(mCurrentProviceId)[pCurrent];
	}
	
	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		mCurrentProviceId = mProvinceDatasId[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mCity.setCurrentItem(0);
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[0];
		mCurrentCityId = mCitisDatasMapId.get(mCurrentProviceId)[0];
	}

	/**
	 * change事件的处理
	 */
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mProvince) {
			updateCities();
		} else if (wheel == mCity) {
			updateAreas();
		}
	}

	public void showChoose(View view) {
		Intent intent = new Intent();
		intent.putExtra("provinceName", mCurrentProviceName);
		intent.putExtra("provinceId", mCurrentProviceId);
		intent.putExtra("cityName", mCurrentCityName);
		intent.putExtra("cityId", mCurrentCityId);
		setResult(RESULT_OK, intent);
		finish();
	}

	public void showClose(View view) {
		finish();
	}

	private String initJsonData(){
		InputStream in = null;
		ByteArrayOutputStream outStream = null;
		try {
			in = getResources().getAssets().open("citys.json");
			outStream = new ByteArrayOutputStream();
			byte[] data = new byte[1024];
			int count = -1;
			while ((count = in.read(data, 0, 1024)) != -1)
				outStream.write(data, 0, count);

			data = null;
			return new String(outStream.toByteArray(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}

