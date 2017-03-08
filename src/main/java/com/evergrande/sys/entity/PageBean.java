/**
 * 
 */
package com.evergrande.sys.entity;

import java.util.List;

public class PageBean<T> {
	/**
	 * 当前页
	 */
	private Integer currPage;
	/**
	 * 每页记录数量
	 */
	private Integer pageSize;
	/**
	 * 分页总数
	 */
	private Integer totalNum;
	private List<T> list;

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageBean {currPage:" + currPage + ", pageSize:" + pageSize + ", totalNum:" + totalNum + ", list:" + list
				+ "}";
	}

	/**
	 * @return the totalNum
	 */
	public Integer getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum
	 *            the totalNum to set
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

}
