package com.liangjj.crm.pojo;

/**
 * @author 小亮
 * 页面参数接收类pojo类
 *
 */
public class QueryCus {
	
	private Integer size=5;
	private String custName;
	private String custIndustory;
	private String custIndustoryName;
	private Integer page=1;
	private Integer start;
	private Integer pid;
	
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustIndustory() {
		return custIndustory;
	}
	public void setCustIndustory(String custIndustory) {
		this.custIndustory = custIndustory;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public String getCustIndustoryName() {
		return custIndustoryName;
	}
	public void setCustIndustoryName(String custIndustoryName) {
		this.custIndustoryName = custIndustoryName;
	}
	
	
	
}
