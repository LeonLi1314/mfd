package com.rtmap.traffic.mfd.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 首都机场航班值班柜台资源
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "flt_depf_counter_pek")
public class DepfCounterPek {
	@Id
	@Column(name = "cnt_id", updatable = false)
	private String cntId;
	@Column(name = "depf_id")
	private String depfId;
	@Column(name = "flt_no")
	private String fltNo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sdt", columnDefinition = "DATETIME")
	private Date sdt;
	@Column(name = "cnt_code")
	private String cntCode;
	@Column(name = "cnt_domint", columnDefinition = "CHAR", length = 1)
	private String cntDomint;
	@Column(name = "cnt_cls")
	private String cntCls;
	@Column(name = "cnt_cls_cn")
	private String cntClsCn;
	@Column(name = "cnt_cls_en")
	private String cntClsEn;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cnt_pot", columnDefinition = "DATETIME")
	private Date cntPot;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cnt_pct", columnDefinition = "DATETIME")
	private Date cntPct;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cnt_ot", columnDefinition = "DATETIME")
	private Date cntOt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cnt_ct", columnDefinition = "DATETIME")
	private Date cntCt;

	public String getCntId() {
		return cntId;
	}

	public void setCntId(String cntId) {
		this.cntId = cntId == null ? null : cntId.trim();
	}

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

	public String getCntCode() {
		return cntCode;
	}

	public void setCntCode(String cntCode) {
		this.cntCode = cntCode == null ? null : cntCode.trim();
	}

	public String getCntDomint() {
		return cntDomint;
	}

	public void setCntDomint(String cntDomint) {
		this.cntDomint = cntDomint == null ? null : cntDomint.trim();
	}

	public String getCntCls() {
		return cntCls;
	}

	public void setCntCls(String cntCls) {
		this.cntCls = cntCls == null ? null : cntCls.trim();
	}

	public String getCntClsCn() {
		return cntClsCn;
	}

	public void setCntClsCn(String cntClsCn) {
		this.cntClsCn = cntClsCn == null ? null : cntClsCn.trim();
	}

	public String getCntClsEn() {
		return cntClsEn;
	}

	public void setCntClsEn(String cntClsEn) {
		this.cntClsEn = cntClsEn == null ? null : cntClsEn.trim();
	}

	public Date getCntPot() {
		return cntPot;
	}

	public void setCntPot(Date cntPot) {
		this.cntPot = cntPot;
	}

	public Date getCntPct() {
		return cntPct;
	}

	public void setCntPct(Date cntPct) {
		this.cntPct = cntPct;
	}

	public Date getCntOt() {
		return cntOt;
	}

	public void setCntOt(Date cntOt) {
		this.cntOt = cntOt;
	}

	public Date getCntCt() {
		return cntCt;
	}

	public void setCntCt(Date cntCt) {
		this.cntCt = cntCt;
	}
}