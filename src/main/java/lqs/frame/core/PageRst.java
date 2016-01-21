package lqs.frame.core;

import java.util.List;

/**
 * 分页结果
 * 
 * @author liqingshan
 *
 */
public class PageRst<T> {
	private int totalPage;
	private int totalCount;
	private boolean firstPage;
	private boolean lastPage;
	private List<T> rst;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getRst() {
		return rst;
	}

	public void setRst(List<T> rst) {
		this.rst = rst;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
}
