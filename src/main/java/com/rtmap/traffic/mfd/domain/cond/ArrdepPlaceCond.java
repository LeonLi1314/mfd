package com.rtmap.traffic.mfd.domain.cond;

import java.util.Date;

import com.rtmap.traffic.mfd.domain.ArrdepFlag;

/**
 * 出发到达地查询条件
 * @author liqingshan 2016-01-11
 *
 */
public class ArrdepPlaceCond {
	private String code;
	private ArrdepFlag arrdep;
	private int codeType;
	private String airlineCode;
	private Date sdt;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ArrdepFlag getArrdep() {
		return arrdep;
	}
	public void setArrdep(ArrdepFlag arrdep) {
		this.arrdep = arrdep;
	}
	public int getCodeType() {
		return codeType;
	}
	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}
	public String getAirlineCode() {
		return airlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}
	public Date getSdt() {
		return sdt;
	}
	public void setSdt(Date sdt) {
		this.sdt = sdt;
	}
}
