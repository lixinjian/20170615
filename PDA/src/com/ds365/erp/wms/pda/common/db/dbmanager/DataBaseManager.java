package com.ds365.erp.wms.pda.common.db.dbmanager;

import java.util.List;

import com.ds365.erp.wms.pda.common.db.DatabaseHelper;
import com.ds365.erp.wms.pda.common.db.dbmodel.PurchaseEnterBillInfo;
import com.ds365.erp.wms.pda.common.db.dbmodel.PurchaseOrderBillInfo;
import com.ds365.erp.wms.pda.common.db.dbmodel.SaleReturnBillInfo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {

	private SQLiteDatabase db;
	private static DataBaseManager dbManager;

	private DataBaseManager(Context context) {
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = DatabaseHelper.getInstanceDB(context);
	}

	public static DataBaseManager getDBManagerInstence(Context context) {
		if (dbManager == null) {
			dbManager = new DataBaseManager(context);
		}
		return dbManager;

	}

	/**
	 * 增加收货入库记录
	 */
	public void addPurchaseOrderBill(List<PurchaseOrderBillInfo> bill) {
		db.beginTransaction(); // 开始事务
		try {
			for (PurchaseOrderBillInfo pobi : bill) {
				db.execSQL("INSERT INTO purchaseOrderBill VALUES(null,?,?,?,?,?,?,?,?,?,?,?)",
						new Object[] { pobi.getBillCode(),pobi.getSkuCode(), pobi.getSkuName(), pobi.getPackUnit(), pobi.getMinPack(),
								pobi.getStoreName(), pobi.getShelfName(), pobi.getProduceDate(),
								pobi.getProduceBatchNo(), pobi.getReceivedUnitQty(), pobi.getReceivedMinUnitQty() });
			}
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}
	/**
	 * 查询收货入库记录
	 * @return PurchaseOrderBillInfo
	 */
	public PurchaseOrderBillInfo getPurchaseOrderBillInfo(String billCode,String skuCode){
		PurchaseOrderBillInfo purchaseOrderBillInfo = null;
        Cursor c = queryTheCursor("purchaseOrderBill",billCode,skuCode);
        while (c.moveToNext()) {
        	purchaseOrderBillInfo = new PurchaseOrderBillInfo();
        	purchaseOrderBillInfo.setStoreName(c.getString(c.getColumnIndex("storeName")));
        	purchaseOrderBillInfo.setShelfName(c.getString(c.getColumnIndex("shelfName")));
        	purchaseOrderBillInfo.setProduceDate(c.getString(c.getColumnIndex("produceDate")));
        	purchaseOrderBillInfo.setReceivedMinUnitQty(c.getInt(c.getColumnIndex("receivedMinUnitQty")));
        	purchaseOrderBillInfo.setReceivedUnitQty(c.getInt(c.getColumnIndex("receivedUnitQty")));
        	purchaseOrderBillInfo.setProduceBatchNo(c.getInt(c.getColumnIndex("produceBatchNo")));
        }
        c.close();
		return purchaseOrderBillInfo;
    }
	
	/**
	 * 删除收货入库表
	 */
	public void deletePurchaseOrderBill() {
		db.execSQL("DELETE FROM purchaseOrderBill");
	}

	/**
	 * 增加退货入库记录
	 */
	public void addSaleReturnBill(List<SaleReturnBillInfo> bill) {
		db.beginTransaction(); // 开始事务
		try {
			for (SaleReturnBillInfo srbi : bill) {
				db.execSQL("INSERT INTO saleReturnBill VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?)",
						new Object[] { srbi.getBillCode(),srbi.getSkuCode(), srbi.getSkuName(), srbi.getPackUnit(), srbi.getMinPack(),
								srbi.getWareHouseName(), srbi.getShelfName(), srbi.getProduceDate(), srbi.getProduceBatch(),
								srbi.getProduceBatchNo(), srbi.getReceivedUnitQty(), srbi.getReceivedMinUnitQty() });
			}
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	/**
	 * 查询退货入库记录
	 * @return PurchaseOrderBillInfo
	 */
	public SaleReturnBillInfo getSaleReturnBillInfo(String billCode,String skuCode){
		SaleReturnBillInfo saleReturnBillInfo = null;
        Cursor c = queryTheCursor("saleReturnBill",billCode,skuCode);
        while (c.moveToNext()) {
        	saleReturnBillInfo = new SaleReturnBillInfo();
        	saleReturnBillInfo.setWareHouseName(c.getString(c.getColumnIndex("wareHouse")));
        	saleReturnBillInfo.setShelfName(c.getString(c.getColumnIndex("shelfName")));
        	saleReturnBillInfo.setProduceDate(c.getString(c.getColumnIndex("produceDate")));
        	saleReturnBillInfo.setReceivedMinUnitQty(c.getInt(c.getColumnIndex("receivedMinUnitQty")));
        	saleReturnBillInfo.setReceivedUnitQty(c.getInt(c.getColumnIndex("receivedUnitQty")));
        	saleReturnBillInfo.setProduceBatchNo(c.getInt(c.getColumnIndex("produceBatchNo")));
        	saleReturnBillInfo.setProduceBatch(c.getInt(c.getColumnIndex("produceBatch")));
        }
        c.close();
		return saleReturnBillInfo;
    }
	
	/**
	 * 删除退货入库表数据
	 */
	public void deleteSaleReturnBill() {
		db.execSQL("DELETE FROM saleReturnBill");
	}

	/**
	 * 增加采购退货记录
	 */
	public void addPurchaseEnterBill(List<PurchaseEnterBillInfo> bill) {
		db.beginTransaction(); // 开始事务
		try {
			for (PurchaseEnterBillInfo pebi : bill) {
				db.execSQL("INSERT INTO purchaseEnterBill VALUES(null,?,?,?,?,?,?,?,?)",
						new Object[] { pebi.getSkuCode(), pebi.getSkuName(), pebi.getPackUnit(), pebi.getMinPack(),
								pebi.getWareHouseName(), pebi.getShelfName(), pebi.getUnitQty(), pebi.getMinUnitQty() });
			}
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}
	
	/**
	 * 查询采购退货记录
	 * @return PurchaseOrderBillInfo
	 */
	public PurchaseEnterBillInfo getPurchaseEnterBillInfo(String billCode,String skuCode){
		PurchaseEnterBillInfo purchaseOrderBillInfo = null;
        Cursor c = queryTheCursor("purchaseEnterBill",billCode,skuCode);
        while (c.moveToNext()) {
        	purchaseOrderBillInfo = new PurchaseEnterBillInfo();
        	purchaseOrderBillInfo.setWareHouseName(c.getString(c.getColumnIndex("wareHouse")));
        	purchaseOrderBillInfo.setShelfName(c.getString(c.getColumnIndex("shelfName")));
        	purchaseOrderBillInfo.setUnitQty(c.getInt(c.getColumnIndex("unitQty")));
        	purchaseOrderBillInfo.setMinUnitQty((c.getInt(c.getColumnIndex("minUnitQty"))));
        }
        c.close();
		return purchaseOrderBillInfo;
    }
	
	/**
	 * 删除采购退货表数据
	 */
	public void deletePurchaseEnterBill() {
		db.execSQL("DELETE FROM purchaseEnterBill");
	}
	
	/**
	 * 游标
	 * @param DBName	表名
	 * @param billCode	查询条件
	 * @param skuCode	查询条件
	 * @return
	 */
	public Cursor queryTheCursor(String DBName, String billCode,String skuCode) {
        Cursor c = db.rawQuery("SELECT * FROM "+DBName+" where billCode = "+ "'"+billCode+"'"+" and skuCode = "+"'"+skuCode+"'", null);
        return c;
    }
	
	public void closeDB() {
		db.close();
	}
}
