package com.rtmap.traffic.mfd.domain.cond;

/**
 * 分页查询条件
 * @author liqingshan
 *
 */
public class PageCond <T>{
	private int index;
	private int size;
	private T cond;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public T getCond() {
		return cond;
	}
	public void setCond(T cond) {
		this.cond = cond;
	}
}
