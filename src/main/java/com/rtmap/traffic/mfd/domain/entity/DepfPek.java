package com.rtmap.traffic.mfd.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 首都机场离港航班动态信息
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "flt_depf_pek")
public class DepfPek {
	@Id
	@Column(name = "depf_id", updatable = false)
	private String depfId;
	@Column(name = "flt_no")
	private String fltNo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sdt", columnDefinition = "DATETIME")
	private Date sdt;
	@Column(name = "domint", columnDefinition = "CHAR", length = 1)
	private String domint;
	@Column(name = "master_flt_no")
	private String masterFltNo;
	@Column(name = "flt_type", columnDefinition = "CHAR", length = 1)
	private String fltType;
	@Column(name = "iata", columnDefinition = "CHAR", length = 2)
	private String iata;
	@Column(name = "icao", columnDefinition = "CHAR", length = 3)
	private String icao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_time", columnDefinition = "DATETIME")
	private Date lastTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "est_time", columnDefinition = "DATETIME")
	private Date estTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "final_time", columnDefinition = "DATETIME")
	private Date finalTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "act_time", columnDefinition = "DATETIME")
	private Date actTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "begin_board_time", columnDefinition = "DATETIME")
	private Date beginBoardTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_board_time", columnDefinition = "DATETIME")
	private Date lastBoardTime;
	@Column(name = "task_nature", columnDefinition = "CHAR", length = 1)
	private String taskNature;
	@Column(name = "regist_no")
	private String registNo;
	@Column(name = "air_type")
	private String airType;
	@Column(name = "term")
	private String term;
	@Column(name = "park")
	private String park;
	@Column(name = "cnt_disp")
	private String cntDisp;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "first_cnt_ot", columnDefinition = "DATETIME")
	private Date firstCntOt;
	@Column(name = "gat_disp")
	private String gatDisp;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "first_gat_ot", columnDefinition = "DATETIME")
	private Date firstGatOt;
	@Column(name = "route")
	private String route;
	@Column(name = "dest_airport_code")
	private String destAirportCode;
	@Column(name = "dest_airport_cn")
	private String destAirportCn;
	@Column(name = "dest_airport_en")
	private String destAirportEn;
	@Column(name = "dest_sdt")
	private Date destSdt;
	@Column(name = "flt_state_code")
	private String fltStateCode;
	@Column(name = "flt_state_en")
	private String fltStateEn;
	@Column(name = "flt_state_en_abbr")
	private String fltStateEnAbbr;
	@Column(name = "flt_state_en_spec")
	private String fltStateEnSpec;
	@Column(name = "flt_state_cn")
	private String fltStateCn;
	@Column(name = "flt_state_cn_abbr")
	private String fltStateCnAbbr;
	@Column(name = "flt_state_cn_spec")
	private String fltStateCnSpec;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cancel_time", columnDefinition = "DATETIME")
	private Date cancelTime;
	@Column(name = "delay_reason_code")
	private String delayReasonCode;
	@Column(name = "delay_reason_en")
	private String delayReasonEn;
	@Column(name = "delay_reason_cn")
	private String delayReasonCn;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delay_begin_time", columnDefinition = "DATETIME")
	private Date delayBeginTime;
	@Column(name = "delay_dur", columnDefinition = "INT")
	private Integer delayDur;
	@Column(name = "ret_status")
	private String retStatus;
	@Column(name = "ret_reason")
	private String retReason;
	@Column(name = "div_airport_code")
	private String divAirportCode;
	@Column(name = "div_airport_en")
	private String divAirportEn;
	@Column(name = "div_airport_cn")
	private String divAirportCn;
	@Column(name = "div_status")
	private String divStatus;
	@Column(name = "div_dir")
	private String divDir;
	@Column(name = "sflight1")
	private String sflight1;
	@Column(name = "sflight2")
	private String sflight2;
	@Column(name = "sflight3")
	private String sflight3;
	@Column(name = "sflight4")
	private String sflight4;
	@Column(name = "sflight5")
	private String sflight5;
	@Column(name = "sflight6")
	private String sflight6;
	@Column(name = "sflight7")
	private String sflight7;
	@Column(name = "sflight8")
	private String sflight8;
	@Column(name = "sflight9")
	private String sflight9;
	@Column(name = "sflight10")
	private String sflight10;
	@Column(name = "sflight1_iata")
	private String sflight1Iata;
	@Column(name = "sflight2_iata")
	private String sflight2Iata;
	@Column(name = "sflight3_iata")
	private String sflight3Iata;
	@Column(name = "sflight4_iata")
	private String sflight4Iata;
	@Column(name = "sflight5_iata")
	private String sflight5Iata;
	@Column(name = "sflight6_iata")
	private String sflight6Iata;
	@Column(name = "sflight7_iata")
	private String sflight7Iata;
	@Column(name = "sflight8_iata")
	private String sflight8Iata;
	@Column(name = "sflight9_iata")
	private String sflight9Iata;
	@Column(name = "sflight10_iata")
	private String sflight10Iata;
	@Column(name = "route1")
	private String route1;
	@Column(name = "route2")
	private String route2;
	@Column(name = "route3")
	private String route3;
	@Column(name = "route4")
	private String route4;
	@Column(name = "route5")
	private String route5;
	@Column(name = "route6")
	private String route6;
	@Column(name = "route1_cn")
	private String route1Cn;
	@Column(name = "route2_cn")
	private String route2Cn;
	@Column(name = "route3_cn")
	private String route3Cn;
	@Column(name = "route4_cn")
	private String route4Cn;
	@Column(name = "route5_cn")
	private String route5Cn;
	@Column(name = "route6_cn")
	private String route6Cn;
	@Column(name = "route1_en")
	private String route1En;
	@Column(name = "route2_en")
	private String route2En;
	@Column(name = "route3_en")
	private String route3En;
	@Column(name = "route4_en")
	private String route4En;
	@Column(name = "route5_en")
	private String route5En;
	@Column(name = "route6_en")
	private String route6En;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "route1_sdt", columnDefinition = "DATETIME")
	private Date route1Sdt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "route2_sdt", columnDefinition = "DATETIME")
	private Date route2Sdt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "route3_sdt", columnDefinition = "DATETIME")
	private Date route3Sdt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "route4_sdt", columnDefinition = "DATETIME")
	private Date route4Sdt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "route5_sdt", columnDefinition = "DATETIME")
	private Date route5Sdt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "route6_sdt", columnDefinition = "DATETIME")
	private Date route6Sdt;
	@Column(name = "route1_flt_dur", columnDefinition = "INT")
	private Integer route1FltDur;
	@Column(name = "route2_flt_dur", columnDefinition = "INT")
	private Integer route2FltDur;
	@Column(name = "route3_flt_dur", columnDefinition = "INT")
	private Integer route3FltDur;
	@Column(name = "route4_flt_dur", columnDefinition = "INT")
	private Integer route4FltDur;
	@Column(name = "route5_flt_dur", columnDefinition = "INT")
	private Integer route5FltDur;
	@Column(name = "route6_flt_dur", columnDefinition = "INT")
	private Integer route6FltDur;
	@Column(name = "route1_stay_dur", columnDefinition = "INT")
	private Integer route1StayDur;
	@Column(name = "route2_stay_dur", columnDefinition = "INT")
	private Integer route2StayDur;
	@Column(name = "route3_stay_dur", columnDefinition = "INT")
	private Integer route3StayDur;
	@Column(name = "route4_stay_dur", columnDefinition = "INT")
	private Integer route4StayDur;
	@Column(name = "route5_stay_dur", columnDefinition = "INT")
	private Integer route5StayDur;
	@Column(name = "route6_stay_dur", columnDefinition = "INT")
	private Integer route6StayDur;
	@Column(name = "pre_flt_no")
	private String preFltNo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pre_sdt", columnDefinition = "DATETIME")
	private Date preSdt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pre_est_time", columnDefinition = "DATETIME")
	private Date preEstTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pre_act_time", columnDefinition = "DATETIME")
	private Date preActTime;
	@Column(name = "pre_flt_state_code")
	private String preFltStateCode;
	@Column(name = "pre_flt_state_en")
	private String preFltStateEn;
	@Column(name = "pre_flt_state_cn")
	private String preFltStateCn;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP", insertable = false, updatable = false)
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", columnDefinition = "TIMESTAMP", insertable = false)
	private Date updateTime;
	@Column(name = "rmk")
	private String rmk;

	public String getDepfId() {
		return depfId;
	}

	public void setDepfId(String depfId) {
		this.depfId = depfId == null ? null : depfId.trim();
	}

	public String getFltNo() {
		return fltNo;
	}

	public void setFltNo(String fltNo) {
		this.fltNo = fltNo == null ? null : fltNo.trim();
	}

	public Date getSdt() {
		return sdt;
	}

	public void setSdt(Date sdt) {
		this.sdt = sdt;
	}

	public String getDomint() {
		return domint;
	}

	public void setDomint(String domint) {
		this.domint = domint == null ? null : domint.trim();
	}

	public String getMasterFltNo() {
		return masterFltNo;
	}

	public void setMasterFltNo(String masterFltNo) {
		this.masterFltNo = masterFltNo == null ? null : masterFltNo.trim();
	}

	public String getFltType() {
		return fltType;
	}

	public void setFltType(String fltType) {
		this.fltType = fltType == null ? null : fltType.trim();
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata == null ? null : iata.trim();
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao == null ? null : icao.trim();
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getEstTime() {
		return estTime;
	}

	public void setEstTime(Date estTime) {
		this.estTime = estTime;
	}

	public Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}

	public Date getActTime() {
		return actTime;
	}

	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}

	public Date getBeginBoardTime() {
		return beginBoardTime;
	}

	public void setBeginBoardTime(Date beginBoardTime) {
		this.beginBoardTime = beginBoardTime;
	}

	public Date getLastBoardTime() {
		return lastBoardTime;
	}

	public void setLastBoardTime(Date lastBoardTime) {
		this.lastBoardTime = lastBoardTime;
	}

	public String getTaskNature() {
		return taskNature;
	}

	public void setTaskNature(String taskNature) {
		this.taskNature = taskNature == null ? null : taskNature.trim();
	}

	public String getRegistNo() {
		return registNo;
	}

	public void setRegistNo(String registNo) {
		this.registNo = registNo == null ? null : registNo.trim();
	}

	public String getAirType() {
		return airType;
	}

	public void setAirType(String airType) {
		this.airType = airType == null ? null : airType.trim();
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term == null ? null : term.trim();
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park == null ? null : park.trim();
	}

	public String getCntDisp() {
		return cntDisp;
	}

	public void setCntDisp(String cntDisp) {
		this.cntDisp = cntDisp == null ? null : cntDisp.trim();
	}

	public Date getFirstCntOt() {
		return firstCntOt;
	}

	public void setFirstCntOt(Date firstCntOt) {
		this.firstCntOt = firstCntOt;
	}

	public String getGatDisp() {
		return gatDisp;
	}

	public void setGatDisp(String gatDisp) {
		this.gatDisp = gatDisp == null ? null : gatDisp.trim();
	}

	public Date getFirstGatOt() {
		return firstGatOt;
	}

	public void setFirstGatOt(Date firstGatOt) {
		this.firstGatOt = firstGatOt;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route == null ? null : route.trim();
	}

	public String getDestAirportCode() {
		return destAirportCode;
	}

	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}

	public String getDestAirportCn() {
		return destAirportCn;
	}

	public void setDestAirportCn(String destAirportCn) {
		this.destAirportCn = destAirportCn;
	}

	public String getDestAirportEn() {
		return destAirportEn;
	}

	public void setDestAirportEn(String destAirportEn) {
		this.destAirportEn = destAirportEn;
	}

	public Date getDestSdt() {
		return destSdt;
	}

	public void setDestSdt(Date destSdt) {
		this.destSdt = destSdt;
	}

	public String getFltStateCode() {
		return fltStateCode;
	}

	public void setFltStateCode(String fltStateCode) {
		this.fltStateCode = fltStateCode == null ? null : fltStateCode.trim();
	}

	public String getFltStateEn() {
		return fltStateEn;
	}

	public void setFltStateEn(String fltStateEn) {
		this.fltStateEn = fltStateEn == null ? null : fltStateEn.trim();
	}

	public String getFltStateEnAbbr() {
		return fltStateEnAbbr;
	}

	public void setFltStateEnAbbr(String fltStateEnAbbr) {
		this.fltStateEnAbbr = fltStateEnAbbr;
	}

	public String getFltStateEnSpec() {
		return fltStateEnSpec;
	}

	public void setFltStateEnSpec(String fltStateEnSpec) {
		this.fltStateEnSpec = fltStateEnSpec;
	}

	public String getFltStateCn() {
		return fltStateCn;
	}

	public void setFltStateCn(String fltStateCn) {
		this.fltStateCn = fltStateCn == null ? null : fltStateCn.trim();
	}

	public String getFltStateCnAbbr() {
		return fltStateCnAbbr;
	}

	public void setFltStateCnAbbr(String fltStateCnAbbr) {
		this.fltStateCnAbbr = fltStateCnAbbr;
	}

	public String getFltStateCnSpec() {
		return fltStateCnSpec;
	}

	public void setFltStateCnSpec(String fltStateCnSpec) {
		this.fltStateCnSpec = fltStateCnSpec;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getDelayReasonCode() {
		return delayReasonCode;
	}

	public void setDelayReasonCode(String delayReasonCode) {
		this.delayReasonCode = delayReasonCode == null ? null : delayReasonCode.trim();
	}

	public String getDelayReasonEn() {
		return delayReasonEn;
	}

	public void setDelayReasonEn(String delayReasonEn) {
		this.delayReasonEn = delayReasonEn == null ? null : delayReasonEn.trim();
	}

	public String getDelayReasonCn() {
		return delayReasonCn;
	}

	public void setDelayReasonCn(String delayReasonCn) {
		this.delayReasonCn = delayReasonCn == null ? null : delayReasonCn.trim();
	}

	public Date getDelayBeginTime() {
		return delayBeginTime;
	}

	public void setDelayBeginTime(Date delayBeginTime) {
		this.delayBeginTime = delayBeginTime;
	}

	public Integer getDelayDur() {
		return delayDur;
	}

	public void setDelayDur(Integer delayDur) {
		this.delayDur = delayDur;
	}

	public String getRetStatus() {
		return retStatus;
	}

	public void setRetStatus(String retStatus) {
		this.retStatus = retStatus == null ? null : retStatus.trim();
	}

	public String getRetReason() {
		return retReason;
	}

	public void setRetReason(String retReason) {
		this.retReason = retReason == null ? null : retReason.trim();
	}

	public String getDivAirportCode() {
		return divAirportCode;
	}

	public void setDivAirportCode(String divAirportCode) {
		this.divAirportCode = divAirportCode == null ? null : divAirportCode.trim();
	}

	public String getDivAirportEn() {
		return divAirportEn;
	}

	public void setDivAirportEn(String divAirportEn) {
		this.divAirportEn = divAirportEn == null ? null : divAirportEn.trim();
	}

	public String getDivAirportCn() {
		return divAirportCn;
	}

	public void setDivAirportCn(String divAirportCn) {
		this.divAirportCn = divAirportCn == null ? null : divAirportCn.trim();
	}

	public String getDivStatus() {
		return divStatus;
	}

	public void setDivStatus(String divStatus) {
		this.divStatus = divStatus == null ? null : divStatus.trim();
	}

	public String getDivDir() {
		return divDir;
	}

	public void setDivDir(String divDir) {
		this.divDir = divDir == null ? null : divDir.trim();
	}

	public String getSflight1() {
		return sflight1;
	}

	public void setSflight1(String sflight1) {
		this.sflight1 = sflight1 == null ? null : sflight1.trim();
	}

	public String getSflight2() {
		return sflight2;
	}

	public void setSflight2(String sflight2) {
		this.sflight2 = sflight2 == null ? null : sflight2.trim();
	}

	public String getSflight3() {
		return sflight3;
	}

	public void setSflight3(String sflight3) {
		this.sflight3 = sflight3 == null ? null : sflight3.trim();
	}

	public String getSflight4() {
		return sflight4;
	}

	public void setSflight4(String sflight4) {
		this.sflight4 = sflight4 == null ? null : sflight4.trim();
	}

	public String getSflight5() {
		return sflight5;
	}

	public void setSflight5(String sflight5) {
		this.sflight5 = sflight5 == null ? null : sflight5.trim();
	}

	public String getSflight6() {
		return sflight6;
	}

	public void setSflight6(String sflight6) {
		this.sflight6 = sflight6 == null ? null : sflight6.trim();
	}

	public String getSflight7() {
		return sflight7;
	}

	public void setSflight7(String sflight7) {
		this.sflight7 = sflight7 == null ? null : sflight7.trim();
	}

	public String getSflight8() {
		return sflight8;
	}

	public void setSflight8(String sflight8) {
		this.sflight8 = sflight8 == null ? null : sflight8.trim();
	}

	public String getSflight9() {
		return sflight9;
	}

	public void setSflight9(String sflight9) {
		this.sflight9 = sflight9 == null ? null : sflight9.trim();
	}

	public String getSflight10() {
		return sflight10;
	}

	public void setSflight10(String sflight10) {
		this.sflight10 = sflight10 == null ? null : sflight10.trim();
	}

	public String getSflight1Iata() {
		return sflight1Iata;
	}

	public void setSflight1Iata(String sflight1Iata) {
		this.sflight1Iata = sflight1Iata == null ? null : sflight1Iata.trim();
	}

	public String getSflight2Iata() {
		return sflight2Iata;
	}

	public void setSflight2Iata(String sflight2Iata) {
		this.sflight2Iata = sflight2Iata == null ? null : sflight2Iata.trim();
	}

	public String getSflight3Iata() {
		return sflight3Iata;
	}

	public void setSflight3Iata(String sflight3Iata) {
		this.sflight3Iata = sflight3Iata == null ? null : sflight3Iata.trim();
	}

	public String getSflight4Iata() {
		return sflight4Iata;
	}

	public void setSflight4Iata(String sflight4Iata) {
		this.sflight4Iata = sflight4Iata == null ? null : sflight4Iata.trim();
	}

	public String getSflight5Iata() {
		return sflight5Iata;
	}

	public void setSflight5Iata(String sflight5Iata) {
		this.sflight5Iata = sflight5Iata == null ? null : sflight5Iata.trim();
	}

	public String getSflight6Iata() {
		return sflight6Iata;
	}

	public void setSflight6Iata(String sflight6Iata) {
		this.sflight6Iata = sflight6Iata == null ? null : sflight6Iata.trim();
	}

	public String getSflight7Iata() {
		return sflight7Iata;
	}

	public void setSflight7Iata(String sflight7Iata) {
		this.sflight7Iata = sflight7Iata == null ? null : sflight7Iata.trim();
	}

	public String getSflight8Iata() {
		return sflight8Iata;
	}

	public void setSflight8Iata(String sflight8Iata) {
		this.sflight8Iata = sflight8Iata == null ? null : sflight8Iata.trim();
	}

	public String getSflight9Iata() {
		return sflight9Iata;
	}

	public void setSflight9Iata(String sflight9Iata) {
		this.sflight9Iata = sflight9Iata == null ? null : sflight9Iata.trim();
	}

	public String getSflight10Iata() {
		return sflight10Iata;
	}

	public void setSflight10Iata(String sflight10Iata) {
		this.sflight10Iata = sflight10Iata == null ? null : sflight10Iata.trim();
	}

	public String getRoute1() {
		return route1;
	}

	public void setRoute1(String route1) {
		this.route1 = route1 == null ? null : route1.trim();
	}

	public String getRoute2() {
		return route2;
	}

	public void setRoute2(String route2) {
		this.route2 = route2 == null ? null : route2.trim();
	}

	public String getRoute3() {
		return route3;
	}

	public void setRoute3(String route3) {
		this.route3 = route3 == null ? null : route3.trim();
	}

	public String getRoute4() {
		return route4;
	}

	public void setRoute4(String route4) {
		this.route4 = route4 == null ? null : route4.trim();
	}

	public String getRoute5() {
		return route5;
	}

	public void setRoute5(String route5) {
		this.route5 = route5 == null ? null : route5.trim();
	}

	public String getRoute6() {
		return route6;
	}

	public void setRoute6(String route6) {
		this.route6 = route6 == null ? null : route6.trim();
	}

	public String getRoute1Cn() {
		return route1Cn;
	}

	public void setRoute1Cn(String route1Cn) {
		this.route1Cn = route1Cn == null ? null : route1Cn.trim();
	}

	public String getRoute2Cn() {
		return route2Cn;
	}

	public void setRoute2Cn(String route2Cn) {
		this.route2Cn = route2Cn == null ? null : route2Cn.trim();
	}

	public String getRoute3Cn() {
		return route3Cn;
	}

	public void setRoute3Cn(String route3Cn) {
		this.route3Cn = route3Cn == null ? null : route3Cn.trim();
	}

	public String getRoute4Cn() {
		return route4Cn;
	}

	public void setRoute4Cn(String route4Cn) {
		this.route4Cn = route4Cn == null ? null : route4Cn.trim();
	}

	public String getRoute5Cn() {
		return route5Cn;
	}

	public void setRoute5Cn(String route5Cn) {
		this.route5Cn = route5Cn == null ? null : route5Cn.trim();
	}

	public String getRoute6Cn() {
		return route6Cn;
	}

	public void setRoute6Cn(String route6Cn) {
		this.route6Cn = route6Cn == null ? null : route6Cn.trim();
	}

	public String getRoute1En() {
		return route1En;
	}

	public void setRoute1En(String route1En) {
		this.route1En = route1En == null ? null : route1En.trim();
	}

	public String getRoute2En() {
		return route2En;
	}

	public void setRoute2En(String route2En) {
		this.route2En = route2En == null ? null : route2En.trim();
	}

	public String getRoute3En() {
		return route3En;
	}

	public void setRoute3En(String route3En) {
		this.route3En = route3En == null ? null : route3En.trim();
	}

	public String getRoute4En() {
		return route4En;
	}

	public void setRoute4En(String route4En) {
		this.route4En = route4En == null ? null : route4En.trim();
	}

	public String getRoute5En() {
		return route5En;
	}

	public void setRoute5En(String route5En) {
		this.route5En = route5En == null ? null : route5En.trim();
	}

	public String getRoute6En() {
		return route6En;
	}

	public void setRoute6En(String route6En) {
		this.route6En = route6En == null ? null : route6En.trim();
	}

	public Date getRoute1Sdt() {
		return route1Sdt;
	}

	public void setRoute1Sdt(Date route1Sdt) {
		this.route1Sdt = route1Sdt;
	}

	public Date getRoute2Sdt() {
		return route2Sdt;
	}

	public void setRoute2Sdt(Date route2Sdt) {
		this.route2Sdt = route2Sdt;
	}

	public Date getRoute3Sdt() {
		return route3Sdt;
	}

	public void setRoute3Sdt(Date route3Sdt) {
		this.route3Sdt = route3Sdt;
	}

	public Date getRoute4Sdt() {
		return route4Sdt;
	}

	public void setRoute4Sdt(Date route4Sdt) {
		this.route4Sdt = route4Sdt;
	}

	public Date getRoute5Sdt() {
		return route5Sdt;
	}

	public void setRoute5Sdt(Date route5Sdt) {
		this.route5Sdt = route5Sdt;
	}

	public Date getRoute6Sdt() {
		return route6Sdt;
	}

	public void setRoute6Sdt(Date route6Sdt) {
		this.route6Sdt = route6Sdt;
	}

	public Integer getRoute1FltDur() {
		return route1FltDur;
	}

	public void setRoute1FltDur(Integer route1FltDur) {
		this.route1FltDur = route1FltDur;
	}

	public Integer getRoute2FltDur() {
		return route2FltDur;
	}

	public void setRoute2FltDur(Integer route2FltDur) {
		this.route2FltDur = route2FltDur;
	}

	public Integer getRoute3FltDur() {
		return route3FltDur;
	}

	public void setRoute3FltDur(Integer route3FltDur) {
		this.route3FltDur = route3FltDur;
	}

	public Integer getRoute4FltDur() {
		return route4FltDur;
	}

	public void setRoute4FltDur(Integer route4FltDur) {
		this.route4FltDur = route4FltDur;
	}

	public Integer getRoute5FltDur() {
		return route5FltDur;
	}

	public void setRoute5FltDur(Integer route5FltDur) {
		this.route5FltDur = route5FltDur;
	}

	public Integer getRoute6FltDur() {
		return route6FltDur;
	}

	public void setRoute6FltDur(Integer route6FltDur) {
		this.route6FltDur = route6FltDur;
	}

	public Integer getRoute1StayDur() {
		return route1StayDur;
	}

	public void setRoute1StayDur(Integer route1StayDur) {
		this.route1StayDur = route1StayDur;
	}

	public Integer getRoute2StayDur() {
		return route2StayDur;
	}

	public void setRoute2StayDur(Integer route2StayDur) {
		this.route2StayDur = route2StayDur;
	}

	public Integer getRoute3StayDur() {
		return route3StayDur;
	}

	public void setRoute3StayDur(Integer route3StayDur) {
		this.route3StayDur = route3StayDur;
	}

	public Integer getRoute4StayDur() {
		return route4StayDur;
	}

	public void setRoute4StayDur(Integer route4StayDur) {
		this.route4StayDur = route4StayDur;
	}

	public Integer getRoute5StayDur() {
		return route5StayDur;
	}

	public void setRoute5StayDur(Integer route5StayDur) {
		this.route5StayDur = route5StayDur;
	}

	public Integer getRoute6StayDur() {
		return route6StayDur;
	}

	public void setRoute6StayDur(Integer route6StayDur) {
		this.route6StayDur = route6StayDur;
	}

	public String getPreFltNo() {
		return preFltNo;
	}

	public void setPreFltNo(String preFltNo) {
		this.preFltNo = preFltNo;
	}

	public Date getPreSdt() {
		return preSdt;
	}

	public void setPreSdt(Date preSdt) {
		this.preSdt = preSdt;
	}

	public Date getPreEstTime() {
		return preEstTime;
	}

	public void setPreEstTime(Date preEstTime) {
		this.preEstTime = preEstTime;
	}

	public Date getPreActTime() {
		return preActTime;
	}

	public void setPreActTime(Date preActTime) {
		this.preActTime = preActTime;
	}

	public String getPreFltStateCode() {
		return preFltStateCode;
	}

	public void setPreFltStateCode(String preFltStateCode) {
		this.preFltStateCode = preFltStateCode;
	}

	public String getPreFltStateEn() {
		return preFltStateEn;
	}

	public void setPreFltStateEn(String preFltStateEn) {
		this.preFltStateEn = preFltStateEn;
	}

	public String getPreFltStateCn() {
		return preFltStateCn;
	}

	public void setPreFltStateCn(String preFltStateCn) {
		this.preFltStateCn = preFltStateCn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk == null ? null : rmk.trim();
	}
}