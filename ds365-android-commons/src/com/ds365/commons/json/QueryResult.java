package com.ds365.commons.json;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author cgb
 *
 * @param <T>
 */
public class QueryResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6808064410049097041L;

	private List<T> records;

	private long totalCount;
	
	public QueryResult(){
		
	}
	
	public QueryResult(List<T> records,long totalCount){
		this.records=records;
		this.totalCount=totalCount;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public static <T> QueryResult<T> newResult(List<T> records,long totalCount){
		
		return new QueryResult<T>(records,totalCount);
		
		
	}

	@Override
	public String toString() {
		return "QueryResult [records=" + records + ", totalCount=" + totalCount
				+ "]";
	}

	
	
}
