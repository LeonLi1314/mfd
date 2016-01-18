package com.rtmap.traffic.mfd.domain.cond;

import java.util.Date;

import com.rtmap.traffic.mfd.domain.ArrdepFlag;

/**
 * 出发到达地查询条件
 * 
 * @author liqingshan 2016-01-11
 *
 */
public class ArrdepPlaceCond {
	private String code;
	private ArrdepFlag arrdep;
	private int type;
	private String airlineCode;
	private Date queryDate;

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

	/**
	 * 获取类型
	 * @return 对象类型（0:城市；1:机场）
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            对象类型（0:城市；1:机场）
	 */
	public void setType(int type) {
		this.type = type;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
}
