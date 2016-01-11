package com.rtmap.traffic.mfd.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 机场航站
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "bas_airport")
public class Airport {
	@Id
	@Column(name = "airport_code", columnDefinition = "CHAR", length = 3)
	private String airportCode;
	@Column(name = "city_code", nullable = false, columnDefinition = "CHAR", length = 3)
	private String cityCode;
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
	@Column(name = "terminal_list")
	private String terminalList;

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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

	public String getTerminalList() {
		return terminalList;
	}

	public void setTerminalList(String terminalList) {
		this.terminalList = terminalList;
	}

}
