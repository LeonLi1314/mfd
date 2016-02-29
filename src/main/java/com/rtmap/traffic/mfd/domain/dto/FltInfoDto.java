package com.rtmap.traffic.mfd.domain.dto;

import com.rtmap.traffic.mfd.domain.ArrdepFlag;

/**
 * 航班信息数据传输对象
 * 
 * @author liqingshan
 *
 */
public class FltInfoDto {
	/**
	 * 对应进、出港航班动态表中的主键
	 */
	private String fltId;
	private String fltNo;
	private String iata;
	private String airlineNameCn;
	private ArrdepFlag arrdep;
	private String startSdt;
	private String destSdt;
	private String startAirportCn;
	private String destAirportCn;
	private String stateCn="";
	private String stateColor = "green";
	private int contractId;

	public String getFltId() {
		return fltId;
	}

	public void setFltId(String fltId) {
		this.fltId = fltId;
	}

	public String getFltNo() {
		return fltNo;
	}

	public void setFltNo(String fltNo) {
		this.fltNo = fltNo;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getAirlineNameCn() {
		return airlineNameCn;
	}

	public void setAirlineNameCn(String airlineNameCn) {
		this.airlineNameCn = airlineNameCn;
	}

	public ArrdepFlag getArrdep() {
		return arrdep;
	}

	public void setArrdep(ArrdepFlag arrdep) {
		this.arrdep = arrdep;
	}

	/*
	 * fastjson日期注解
	 */
	// @JSONField(format = "HH:mm")
	// @JsonFormat(pattern = "HH:mm")
	public String getStartSdt() {
		return startSdt;
	}

	public void setStartSdt(String startSdt) {
		this.startSdt = startSdt;
	}
	/*
	 * fastjson日期注解
	 */
	// @JSONField(format = "HH:mm")
	// @JsonFormat(pattern = "HH:mm")
	public String getDestSdt() {
		return destSdt;
	}

	public void setDestSdt(String destSdt) {
		this.destSdt = destSdt;
	}

	public String getStartAirportCn() {
		return startAirportCn;
	}

	public void setStartAirportCn(String startAirportCn) {
		this.startAirportCn = startAirportCn;
	}

	public String getDestAirportCn() {
		return destAirportCn;
	}

	public void setDestAirportCn(String destAirportCn) {
		this.destAirportCn = destAirportCn;
	}

	public String getStateCn() {
		return stateCn;
	}

	public void setStateCn(String stateCn) {
		this.stateCn = stateCn;
	}

	public String getStateColor() {
		return stateColor;
	}

	public void setStateColor(String stateColor) {
		this.stateColor = stateColor;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
}
