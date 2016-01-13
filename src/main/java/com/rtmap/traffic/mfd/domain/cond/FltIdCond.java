package com.rtmap.traffic.mfd.domain.cond;

import com.rtmap.traffic.mfd.domain.ArrdepFlag;

/**
 * 航班Id查询条件
 * @author liqingshan 2016-01-12
 *
 */
public class FltIdCond {
	private String fltId;
	private ArrdepFlag arrdep;
	public String getFltId() {
		return fltId;
	}
	public void setFltId(String fltId) {
		this.fltId = fltId;
	}
	public ArrdepFlag getArrdep() {
		return arrdep;
	}
	public void setArrdep(ArrdepFlag arrdep) {
		this.arrdep = arrdep;
	}
}
