package com.rtmap.traffic.mfd.domain;

import java.util.List;

/**
 * 分页结果
 * @author liqingshan
 *
 */
public class PageRst <T>{
	private int totalPage;
	private List<T> rst;
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getRst() {
		return rst;
	}
	public void setRst(List<T> rst) {
		this.rst = rst;
	}
}
