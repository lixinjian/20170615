package com.ds365.erp.wms.pda.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库
 * 
 * @author LiXinjian
 * @date 2016年7月4日.
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "pda.db";

	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	private volatile static DatabaseHelper singleton;
	private volatile static SQLiteDatabase singletonDB;

	private static DatabaseHelper getInstance(Context context) {
		if (singleton == null) {
			synchronized (DatabaseHelper.class) {
				if (singleton == null) {
					singleton = new DatabaseHelper(context);
				}
			}
		}
		return singleton;
	}

	public static SQLiteDatabase getInstanceDB(Context context) {
		if (singletonDB == null) {
			singletonDB = getInstance(context).getWritableDatabase();
		}
		return singletonDB;
	}

	/**
	 * 创建表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 收货入库 (_id,订单号,sku码,sku名称,包装单位,最小单位,库区,货位,生产日期,批号,实收件数,实收散数)
		db.execSQL("CREATE TABLE IF NOT EXISTS purchaseOrderBill"
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, billCode VARCHAR, skuCode VARCHAR,skuName VARCHAR,packUnit VARCHAR,minPack VARCHAR,wareHouse VARCHAR,shelfName VARCHAR,produceDate VARCHAR,produceBatchNo INTEGER,receivedUnitQty INTEGER, receivedMinUnitQty INTEGER)");
		
		// 退货入库 (_id,sku码,sku名称,包装单位,最小单位,库区,货位,生产日期,批号,批次,实收件数,实收散数)
		db.execSQL("CREATE TABLE IF NOT EXISTS saleReturnBill"
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, billCode VARCHAR, skuCode VARCHAR,skuName VARCHAR,packUnit VARCHAR,minPack VARCHAR,wareHouse VARCHAR,shelfName VARCHAR,produceDate VARCHAR,produceBatchNo INTEGER,produceBatch INTEGER,receivedUnitQty INTEGER, receivedMinUnitQty INTEGER)");
		
		// 调拨入库	TODO:
		db.execSQL("CREATE TABLE IF NOT EXISTS allotWareHouse"
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT,receivedQty INTEGER, receivedUnitQty INTEGER)");
		
		// 采购退货 (_id,sku码,sku名称,包装单位,最小单位,库区,货位,退货件数,退货散数)
		db.execSQL("CREATE TABLE IF NOT EXISTS purchaseEnterBill"																						//TODO:下行的receivedUnitQty,receivedMinUnitQty命名不确定		
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT,skuCode VARCHAR,skuName VARCHAR,packUnit VARCHAR,minPack VARCHAR,wareHouse VARCHAR,shelfName VARCHAR,unitQty INTEGER, minUnitQty INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// db.execSQL("ALTER TABLE search ADD COLUMN other STRING");
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
}
