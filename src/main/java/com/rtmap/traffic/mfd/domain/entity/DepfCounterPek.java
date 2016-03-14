package com.rtmap.traffic.mfd.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.rtmap.traffic.mfd.common.XmlDateTimeAdapter;

/**
 * 首都机场航班值班柜台资源
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "flt_depf_counter_pek")
@XmlAccessorType(XmlAccessType.FIELD)
public class DepfCounterPek {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cnt_id", columnDefinition = "INT",unique=true, insertable = false, updatable = false)
	private Integer cntId;
	@Column(name = "depf_id")
	@XmlElement(name = "FLID")
	private String depfId;
	@Column(name = "flt_no")
	private String fltNo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sdt", columnDefinition = "DATETIME")
	private Date sdt;
	@Column(name = "cnt_code")
	@XmlElement(name = "COUC")
	private String cntCode;
	@Column(name = "cnt_domint", columnDefinition = "CHAR", length = 1)
	@XmlElement(name = "DORI")
	private String cntDomint;
	@Column(name = "cnt_cls")
	@XmlElement(name = "CCLS")
	private String cntCls;
	@Column(name = "cnt_cls_cn")
	@XmlElement(name = "CClC")
	private String cntClsCn;
	@Column(name = "cnt_cls_en")
	@XmlElement(name = "CClE")
	private String cntClsEn;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cnt_pot", columnDefinition = "DATETIME")
	@XmlElement(name = "SCST")
	@XmlJavaTypeAdapter(value = XmlDateTimeAdapter.class)
	private Date cntPot;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cnt_pct", columnDefinition = "DATETIME")
	@XmlElement(name = "SCET")
	@XmlJavaTypeAdapter(value = XmlDateTimeAdapter.class)
	private Date cntPct;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cnt_ot", columnDefinition = "DATETIME")
	@XmlElement(name = "ACST")
	@XmlJavaTypeAdapter(value = XmlDateTimeAdapter.class)
	private Date cntOt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cnt_ct", columnDefinition = "DATETIME")
	@XmlElement(name = "ACET")
	@XmlJavaTypeAdapter(value = XmlDateTimeAdapter.class)
	private Date cntCt;

	public Integer getCntId() {
		return cntId;
	}

	public void setCntId(Integer cntId) {
		this.cntId = cntId;
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