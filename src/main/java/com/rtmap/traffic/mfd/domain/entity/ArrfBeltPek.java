package com.rtmap.traffic.mfd.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 首都机场航班行李转盘资源
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "flt_arrf_belt_pek")
public class ArrfBeltPek {
	@Id
	@Column(name = "blt_id", updatable = false)
    private String bltId;
	@Column(name = "arrf_id")
    private String arrfId;
	@Column(name = "flt_no")
    private String fltNo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sdt", columnDefinition = "DATETIME")
    private Date sdt;
    @Column(name = "blt_code")
    private String bltCode;
    @Column(name = "blt_name")
    private String bltName;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "blt_ot", columnDefinition = "DATETIME")
    private Date bltOt;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "blt_ct", columnDefinition = "DATETIME")
    private Date bltCt;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "blt_fbag_time", columnDefinition = "DATETIME")
    private Date bltFbagTime;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "blt_lbag_time", columnDefinition = "DATETIME")
    private Date bltLbagTime;
    @Column(name = "blt_domint", columnDefinition = "CHAR", length = 1)
    private String bltDomint;
    @Column(name = "blt_cls_code")
    private String bltClsCode;
    @Column(name = "blt_cls_cn")
    private String bltClsCn;
    @Column(name = "blt_cls_en")
    private String bltClsEn;

    public String getBltId() {
        return bltId;
    }

    public void setBltId(String bltId) {
        this.bltId = bltId == null ? null : bltId.trim();
    }

    public String getArrfId() {
        return arrfId;
    }

    public void setArrfId(String arrfId) {
        this.arrfId = arrfId == null ? null : arrfId.trim();
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

    public String getBltCode() {
        return bltCode;
    }

    public void setBltCode(String bltCode) {
        this.bltCode = bltCode == null ? null : bltCode.trim();
    }

    public String getBltName() {
        return bltName;
    }

    public void setBltName(String bltName) {
        this.bltName = bltName == null ? null : bltName.trim();
    }

    public Date getBltOt() {
        return bltOt;
    }

    public void setBltOt(Date bltOt) {
        this.bltOt = bltOt;
    }

    public Date getBltCt() {
        return bltCt;
    }

    public void setBltCt(Date bltCt) {
        this.bltCt = bltCt;
    }

    public Date getBltFbagTime() {
        return bltFbagTime;
    }

    public void setBltFbagTime(Date bltFbagTime) {
        this.bltFbagTime = bltFbagTime;
    }

    public Date getBltLbagTime() {
        return bltLbagTime;
    }

    public void setBltLbagTime(Date bltLbagTime) {
        this.bltLbagTime = bltLbagTime;
    }

    public String getBltDomint() {
        return bltDomint;
    }

    public void setBltDomint(String bltDomint) {
        this.bltDomint = bltDomint == null ? null : bltDomint.trim();
    }

    public String getBltClsCode() {
        return bltClsCode;
    }

    public void setBltClsCode(String bltClsCode) {
        this.bltClsCode = bltClsCode == null ? null : bltClsCode.trim();
    }

    public String getBltClsCn() {
        return bltClsCn;
    }

    public void setBltClsCn(String bltClsCn) {
        this.bltClsCn = bltClsCn == null ? null : bltClsCn.trim();
    }

    public String getBltClsEn() {
        return bltClsEn;
    }

    public void setBltClsEn(String bltClsEn) {
        this.bltClsEn = bltClsEn == null ? null : bltClsEn.trim();
    }
}