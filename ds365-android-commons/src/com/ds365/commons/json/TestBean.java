package com.ds365.commons.json;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 */
public class TestBean {

	//@GsonDataFotmat(dateFormat="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date  createDate;
	
	private Long id;
	
	private String name;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestBean(Date createDate, Long id, String name) {
		super();
		this.createDate = createDate;
		this.id = id;
		this.name = name;
	}

	public TestBean() {
		super();
	}
	
	
	
	public static void main(String args[]){
		TestBean tb = new TestBean(new Date(),1000000L,"tbName");
//		System.out.println(GsonHelper.toJson(tb));
//		System.out.println(GsonHelper.fromJson("{\"createDate\":\"2015-10-16\",\"id\":1000000,\"name\":\"tbName\"}", TestBean.class));
	}
	
}