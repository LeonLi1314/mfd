package com.rtmap.traffic.mfd.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * 机场航站
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "bas_airline")
@JSONType(ignores = {"domint"})
public class Airline {
	@Id
	@Column(name = "airline_code", columnDefinition = "CHAR", length = 2)
	private String airlineCode;
	@Column(name = "domint", nullable = false, columnDefinition = "CHAR", length = 1)
	private String domint;
	@Column(name = "name_abbr_cn")
	private String nameAbbrCn;
	@Column(name = "name_cn")
	private String nameCn;
	@Column(name = "name_en")
	private String nameEn;
	@Column(name = "full_pinyin")
	private String fullPinyin;
	@Column(name = "first_letter")
	private String firstLetter;

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getDomint() {
		return domint;
	}

	public void setDomint(String domint) {
		this.domint = domint;
	}

	public String getNameAbbrCn() {
		return nameAbbrCn;
	}

	public void setNameAbbrCn(String nameAbbrCn) {
		this.nameAbbrCn = nameAbbrCn;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

}
