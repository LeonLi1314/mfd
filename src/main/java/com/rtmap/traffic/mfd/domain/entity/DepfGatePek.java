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

import com.rtmap.traffic.mfd.common.XmlDateAdapter;

/**
 * 首都机场航班登机口资源
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "flt_depf_gate_pek")
@XmlAccessorType(XmlAccessType.FIELD)
public class DepfGatePek {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gat_id", columnDefinition = "INT",unique=true, insertable = false, updatable = false)
    private Integer gatId;
    @Column(name = "depf_id")
	@XmlElement(name = "FLID")
    private String depfId;
    @Column(name = "flt_no")
    private String fltNo;
    @Temporal(TemporalType.TIMESTAMP)
   	@Column(name = "sdt", columnDefinition = "DATETIME")
    private Date sdt;
    @Column(name = "gat_code")
	@XmlElement(name = "GATC")
    private String gatCode;
    @Column(name = "gat_domint", columnDefinition = "CHAR", length = 1)
	@XmlElement(name = "DORI")
    private String gatDomint;
    @Column(name = "gat_cls_code")
	@XmlElement(name = "GATC")
    private String gatClsCode;
    @Column(name = "gat_cls_cn")
	@XmlElement(name = "GCLC")
    private String gatClsCn;
    @Column(name = "gat_cls_en")
	@XmlElement(name = "GCLE")
    private String gatClsEn;
    @Temporal(TemporalType.TIMESTAMP)
   	@Column(name = "gat_pot", columnDefinition = "DATETIME")
	@XmlElement(name = "SCST")
	@XmlJavaTypeAdapter(value = XmlDateAdapter.class)
    private Date gatPot;
    @Temporal(TemporalType.TIMESTAMP)
   	@Column(name = "gat_pct", columnDefinition = "DATETIME")
	@XmlElement(name = "SCET")
	@XmlJavaTypeAdapter(value = XmlDateAdapter.class)
    private Date gatPct;
    @Temporal(TemporalType.TIMESTAMP)
   	@Column(name = "gat_ot", columnDefinition = "DATETIME")
	@XmlElement(name = "ACST")
	@XmlJavaTypeAdapter(value = XmlDateAdapter.class)
    private Date gatOt;
    @Temporal(TemporalType.TIMESTAMP)
   	@Column(name = "gat_ct", columnDefinition = "DATETIME")
	@XmlElement(name = "ACET")
	@XmlJavaTypeAdapter(value = XmlDateAdapter.class)
    private Date gatCt;

    public Integer getGatId() {
        return gatId;
    }

    public void setGatId(Integer gatId) {
        this.gatId = gatId;
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

    public String getGatCode() {
        return gatCode;
    }

    public void setGatCode(String gatCode) {
        this.gatCode = gatCode == null ? null : gatCode.trim();
    }

    public String getGatDomint() {
        return gatDomint;
    }

    public void setGatDomint(String gatDomint) {
        this.gatDomint = gatDomint == null ? null : gatDomint.trim();
    }

    public String getGatClsCode() {
        return gatClsCode;
    }

    public void setGatClsCode(String gatClsCode) {
        this.gatClsCode = gatClsCode == null ? null : gatClsCode.trim();
    }

    public String getGatClsCn() {
        return gatClsCn;
    }

    public void setGatClsCn(String gatClsCn) {
        this.gatClsCn = gatClsCn == null ? null : gatClsCn.trim();
    }

    public String getGatClsEn() {
        return gatClsEn;
    }

    public void setGatClsEn(String gatClsEn) {
        this.gatClsEn = gatClsEn == null ? null : gatClsEn.trim();
    }

    public Date getGatPot() {
        return gatPot;
    }

    public void setGatPot(Date gatPot) {
        this.gatPot = gatPot;
    }

    public Date getGatPct() {
        return gatPct;
    }

    public void setGatPct(Date gatPct) {
        this.gatPct = gatPct;
    }

    public Date getGatOt() {
        return gatOt;
    }

    public void setGatOt(Date gatOt) {
        this.gatOt = gatOt;
    }

    public Date getGatCt() {
        return gatCt;
    }

    public void setGatCt(Date gatCt) {
        this.gatCt = gatCt;
    }
}