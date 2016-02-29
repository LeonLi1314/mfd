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

/**
 * 首都机场航班动态变更信息
 * 
 * @author liqingshan 2016-01-15
 *
 */
@Entity
@Table(name = "flt_changeinfo_pek")
public class FltChangeinfoPek {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rec_id", columnDefinition = "INT")
	private Integer recId;
	@Column(name = "flt_id")
	private String fltId;
	@Column(name = "alcd", columnDefinition = "CHAR", length = 2)
	private String alcd;
	@Column(name = "flt_no")
	private String fltNo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sdt", columnDefinition = "DATETIME")
	private Date sdt;
	@Column(name = "domint", columnDefinition = "CHAR", length = 1)
	private String domint;
	@Column(name = "arrdep", columnDefinition = "CHAR", length = 1)
	private String arrdep;
	@Column(name = "change_info")
	private String changeInfo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP")
	private Date createTime;
	@Column(name = "update_time", columnDefinition = "TIMESTAMP")
	private Date updateTime;
	@Column(name = "execute_flag", columnDefinition = "CHAR", length = 1)
	private String executeFlag = "N";

	public Integer getRecId() {
		return recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	public String getFltId() {
		return fltId;
	}

	public void setFltId(String fltId) {
		this.fltId = fltId == null ? null : fltId.trim();
	}

	public String getAlcd() {
		return alcd;
	}

	public void setAlcd(String alcd) {
		this.alcd = alcd == null ? null : alcd.trim();
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

	public String getArrdep() {
		return arrdep;
	}

	public void setArrdep(String arrdep) {
		this.arrdep = arrdep;
	}

	public String getChangeInfo() {
		return changeInfo;
	}

	public void setChangeInfo(String changeInfo) {
		this.changeInfo = changeInfo == null ? null : changeInfo.trim();
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

	public String getExecuteFlag() {
		return executeFlag;
	}

	public void setExecuteFlag(String executeFlag) {
		this.executeFlag = executeFlag == null ? null : executeFlag.trim();
	}
}