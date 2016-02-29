package com.rtmap.traffic.mfd.domain.dto;

/**
 * 航班详情数据传输对象
 * 
 * @author liqingshan 2016-01-12
 *
 */
public class FltDetailDto extends FltInfoDto {
	private boolean isFollow;
	private String fltType;
	/**
	 * 当fltType为A时不显示；为M时显示共享航班；为S时显示主航班
	 */
	private String relFltDesc = "";
	private String relFltNos = "";
	private String startTimeName = "";
	private String startTime = "";
	private String destTimeName = "";
	private String destTime = "";
	/*
	 * 到港资源信息
	 */
	private String bltDisp = "-";
	private String firstBltOt = "-";
	private String taxiWait = "-";
	/*
	 * 离港资源信息
	 */
	private String cntDisp = "-";
	private String firstCntOt = "-";
	private String gatDisp = "-";
	private String firstGatOt = "-";
	private String securityDur = "15";
	private String securityState = "畅通";
	private String securityColor = "green";

	public boolean isFollow() {
		return isFollow;
	}

	public void setFollow(boolean isFollow) {
		this.isFollow = isFollow;
	}

	public String getFltType() {
		return fltType;
	}

	public void setFltType(String fltType) {
		this.fltType = fltType;
	}

	public String getRelFltDesc() {
		return relFltDesc;
	}

	public void setRelFltDesc(String relFltDesc) {
		this.relFltDesc = relFltDesc;
	}

	public String getRelFltNos() {
		return relFltNos;
	}

	public void setRelFltNos(String relFltNos) {
		this.relFltNos = relFltNos;
	}

	public String getStartTimeName() {
		return startTimeName;
	}

	public void setStartTimeName(String startTimeName) {
		this.startTimeName = startTimeName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDestTimeName() {
		return destTimeName;
	}

	public void setDestTimeName(String destTimeName) {
		this.destTimeName = destTimeName;
	}

	public String getDestTime() {
		return destTime;
	}

	public void setDestTime(String destTime) {
		this.destTime = destTime;
	}

	public String getBltDisp() {
		return bltDisp;
	}

	public void setBltDisp(String bltDisp) {
		this.bltDisp = bltDisp;
	}

	public String getFirstBltOt() {
		return firstBltOt;
	}

	public void setFirstBltOt(String firstBltOt) {
		this.firstBltOt = firstBltOt;
	}

	public String getTaxiWait() {
		return taxiWait;
	}

	public void setTaxiWait(String taxiWait) {
		this.taxiWait = taxiWait;
	}

	public String getCntDisp() {
		return cntDisp;
	}

	public void setCntDisp(String cntDisp) {
		this.cntDisp = cntDisp;
	}

	public String getFirstCntOt() {
		return firstCntOt;
	}

	public void setFirstCntOt(String firstCntOt) {
		this.firstCntOt = firstCntOt;
	}

	public String getGatDisp() {
		return gatDisp;
	}

	public void setGatDisp(String gatDisp) {
		this.gatDisp = gatDisp;
	}

	public String getFirstGatOt() {
		return firstGatOt;
	}

	public void setFirstGatOt(String firstGatOt) {
		this.firstGatOt = firstGatOt;
	}

	public String getSecurityDur() {
		return securityDur;
	}

	public void setSecurityDur(String securityDur) {
		this.securityDur = securityDur;
	}

	public String getSecurityState() {
		return securityState;
	}

	public void setSecurityState(String securityState) {
		this.securityState = securityState;
	}

	public String getSecurityColor() {
		return securityColor;
	}

	public void setSecurityColor(String securityColor) {
		this.securityColor = securityColor;
	}
}
