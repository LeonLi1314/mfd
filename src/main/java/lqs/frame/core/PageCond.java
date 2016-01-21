package lqs.frame.core;

/**
 * 分页查询条件
 * 
 * @author liqingshan
 *
 */
public class PageCond<T> {
	private int pageNo;
	private int pageSize = 10;
	private T cond;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public T getCond() {
		return cond;
	}

	public void setCond(T cond) {
		this.cond = cond;
	}
}
