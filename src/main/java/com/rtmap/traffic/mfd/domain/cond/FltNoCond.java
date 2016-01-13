package com.rtmap.traffic.mfd.domain.cond;

import java.util.Date;

import com.rtmap.traffic.mfd.domain.ArrdepFlag;

/**
 * 航班号查询条件
 * 
 * @author liqingshan 2016-01-12
 *
 */
public class FltNoCond {
	private String fltNo;
	private ArrdepFlag arrdep;
	private Date queryDate;
	public String getFltNo() {
		return fltNo;
	}
	public void setFltNo(String fltNo) {
		this.fltNo = fltNo;
	}
	public ArrdepFlag getArrdep() {
		return arrdep;
	}
	public void setArrdep(ArrdepFlag arrdep) {
		this.arrdep = arrdep;
	}
	public Date getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
}
