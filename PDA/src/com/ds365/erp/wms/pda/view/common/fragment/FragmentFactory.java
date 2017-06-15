package com.ds365.erp.wms.pda.view.common.fragment;

import java.util.HashMap;
import java.util.Map;

import com.ds365.erp.wms.pda.view.enterwarehouse.EnterWareHouseFragment;
import com.ds365.erp.wms.pda.view.message.MessageCategoryListFragment;
import com.ds365.erp.wms.pda.view.outwarehouse.OutWareHouseFragment;
import com.ds365.erp.wms.pda.view.query.QueryFragment;
import com.ds365.erp.wms.pda.view.stock.StockFragment;
import com.ds365.erp.wms.pda.view.stock.putaway.fragment.PutawayTaskListFragment;

import android.annotation.SuppressLint;
import android.app.Fragment;

/**
 * 这个类是关于创建Fragment的工厂类，用来简化创建过程
 * 并使用Map集合对创建的Fragment进行缓存，提高效率
 *
 */
public class FragmentFactory {
	
	/**
	 * 关于首页的RadioGroup位置的RadioButton的标示定义
	 */
	private static final int MAIN_RADIOBUT_ENTERWAREHOUSE=0;
	private static final int MAIN_RADIOBUT_OUTWAREHOUSE=1;
	private static final int MAIN_RADIOBUT_PUTAWAYOFF=2;
	private static final int MAIN_RADIOBUT_MESSAGE=4;
	private static final int MAIN_RADIOBUT_QUERY=3;
	private static final int PUTAWAYTASK_PUTAWAY_BY_CHOICE = 5;
	private static final int PUTAWAYTASK_PUTAWAY_BY_BILLCODE = 6;
	
	/**
	 * 定义一个Map用来完成Fragment的缓存操作，防止多次创建同一个Fragment产生内存的浪费
	 */
	@SuppressLint("UseSparseArrays")
	private static Map<Integer, BaseFragment> mainActivityFragmentMap = new HashMap<Integer, BaseFragment>();
	/**
	 * 这个方法表示：对主页位置的Fragment进行创建操作，当用户点击底部的RadioGroup的时候
	 * 根据用户的点击创建不同的Fragment对象
	 * @param checkedId
	 * @return
	 */
	public static Fragment createMainActiviryFragment(int checkedId){
		
		BaseFragment mainActiviryFragment=mainActivityFragmentMap.get(checkedId);
		if(null==mainActiviryFragment){
			switch(checkedId){
			
			case MAIN_RADIOBUT_ENTERWAREHOUSE:
				mainActiviryFragment=new EnterWareHouseFragment();
				break;
	        case MAIN_RADIOBUT_OUTWAREHOUSE:
	        	mainActiviryFragment=new OutWareHouseFragment();
				break;
	        case MAIN_RADIOBUT_PUTAWAYOFF:
	        	mainActiviryFragment=new StockFragment();
		        break;
	        case MAIN_RADIOBUT_MESSAGE:
	        	mainActiviryFragment=new MessageCategoryListFragment();
		        break;
	        case MAIN_RADIOBUT_QUERY:
	        	mainActiviryFragment=new QueryFragment();
	        	break;
//	        case PUTAWAYTASK_PUTAWAY_BY_CHOICE:		//选择上架功能先隐藏
//	        	mainActiviryFragment=new PutawayTaskByChoiceFragment();
//	        	break;
	        case PUTAWAYTASK_PUTAWAY_BY_BILLCODE:
	        	mainActiviryFragment=new PutawayTaskListFragment();
	        	break;
			}
			mainActivityFragmentMap.put(checkedId, mainActiviryFragment);
		}
		return mainActiviryFragment;
	}
}
