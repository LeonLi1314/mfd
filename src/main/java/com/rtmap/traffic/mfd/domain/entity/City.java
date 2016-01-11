package com.rtmap.traffic.mfd.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * 城市
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "bas_city")
@JSONType(ignores = { "countryCode", "domint", "airportCount", "timezone", "dstFlag", "dstResult" })
public class City {
	@Id
	@Column(name = "city_code", columnDefinition = "CHAR", length = 3)
	private String cityCode;
	@Column(name = "country_code", nullable = false, columnDefinition = "CHAR", length = 2)
	private String countryCode;
	@Column(name = "domint", nullable = false, columnDefinition = "CHAR", length = 1)
	private String domint;
	@Column(name = "airport_count")
	private Integer airportCount;
	@Column(name = "name_cn")
	private String nameCn;
	@Column(name = "name_en")
	private String nameEn;
	@Column(name = "full_pinyin")
	private String fullPinyin;
	@Column(name = "first_letter")
	private String firstLetter;
	@Column(name = "timezone")
	private String timezone;
	@Column(name = "dst_flag", nullable = false, columnDefinition = "CHAR", length = 1)
	private String dstFlag;
	@Column(name = "dst_result")
	private String dstResult;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDomint() {
		return domint;
	}

	public void setDomint(String domint) {
		this.domint = domint;
	}

	public Integer getAirportCount() {
		return airportCount;
	}

	public void setAirportCount(Integer airportCount) {
		this.airportCount = airportCount;
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

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getDstFlag() {
		return dstFlag;
	}

	public void setDstFlag(String dstFlag) {
		this.dstFlag = dstFlag;
	}

	public String getDstResult() {
		return dstResult;
	}

	public void setDstResult(String dstResult) {
		this.dstResult = dstResult;
	}

}
