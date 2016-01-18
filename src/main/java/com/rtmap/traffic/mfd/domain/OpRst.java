package com.rtmap.traffic.mfd.domain;

/**
 * 操作结果
 * 
 * @author liqingshan 2016-01-11
 *
 */
public class OpRst {
	public OpRst() {
	}

	public OpRst(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public OpRst(boolean isSuccess, String msg) {
		this.isSuccess = isSuccess;
		this.msg = msg;
	}

	private boolean isSuccess;
	private String msg;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
