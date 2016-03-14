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
 * 城市天气实体
 * 
 * @author liqingshan
 *
 */
@Entity
@Table(name = "city_weather")
public class CityWeather {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weather_id", columnDefinition = "INT")
	private Integer weatherId;

	@Column(name = "area_id", columnDefinition = "INT")
	private Integer areaId;

	@Column(name = "city_code", columnDefinition = "CHAR", length = 3)
	private String cityCode;

	@Column(name = "city_name_cn")
	private String cityNameCn;

	@Column(name = "city_name_en")
	private String cityNameEn;

	@Column(name = "airport_code", columnDefinition = "CHAR", length = 3)
	private String airportCode;

	@Column(name = "airport_name_cn")
	private String airportNameCn;

	@Column(name = "airport_name_en")
	private String airportNameEn;

	@Temporal(TemporalType.DATE)
	@Column(name = "stat_date", columnDefinition = "DATE")
	private Date statDate;

	@Column(name = "dwph_code", columnDefinition = "CHAR", length = 2)
	private String dwphCode;

	@Column(name = "dwph_spec_cn")
	private String dwphSpecCn;

	@Column(name = "dwph_spec_en")
	private String dwphSpecEn;

	@Column(name = "dwph_image_url")
	private String dwphImageUrl;

	@Column(name = "nwph_code", columnDefinition = "CHAR", length = 2)
	private String nwphCode;

	@Column(name = "nwph_spec_cn")
	private String nwphSpecCn;

	@Column(name = "nwph_spec_en")
	private String nwphSpecEn;

	@Column(name = "nwph_image_url")
	private String nwphImageUrl;

	@Column(name = "dwpw_code", columnDefinition = "CHAR", length = 2)
	private String dwpwCode;

	@Column(name = "dwpw_spec_cn")
	private String dwpwSpecCn;

	@Column(name = "dwpw_spec_en")
	private String dwpwSpecEn;

	@Column(name = "nwpw_code", columnDefinition = "CHAR", length = 2)
	private String nwpwCode;

	@Column(name = "nwpw_spec_cn")
	private String nwpwSpecCn;

	@Column(name = "nwpw_spec_en")
	private String nwpwSpecEn;

	@Column(name = "day_temp", columnDefinition = "CHAR", length = 5)
	private String dayTemp;

	@Column(name = "night_temp", columnDefinition = "CHAR", length = 5)
	private String nightTemp;

	@Column(name = "dwdir_code", columnDefinition = "CHAR", length = 2)
	private String dwdirCode;

	@Column(name = "dwdir_spec_cn")
	private String dwdirSpecCn;

	@Column(name = "dwdir_spec_en")
	private String dwdirSpecEn;

	@Column(name = "nwdir_code", columnDefinition = "CHAR", length = 2)
	private String nwdirCode;

	@Column(name = "nwdir_spec_cn")
	private String nwdirSpecCn;

	@Column(name = "nwdir_spec_en")
	private String nwdirSpecEn;

	@Column(name = "index_disp")
	private String indexDisp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "release_time", columnDefinition = "DATETIME")
	private Date releaseTime;

	public Integer getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode == null ? null : cityCode.trim();
	}

	public String getCityNameCn() {
		return cityNameCn;
	}

	public void setCityNameCn(String cityNameCn) {
		this.cityNameCn = cityNameCn == null ? null : cityNameCn.trim();
	}

	public String getCityNameEn() {
		return cityNameEn;
	}

	public void setCityNameEn(String cityNameEn) {
		this.cityNameEn = cityNameEn == null ? null : cityNameEn.trim();
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode == null ? null : airportCode.trim();
	}

	public String getAirportNameCn() {
		return airportNameCn;
	}

	public void setAirportNameCn(String airportNameCn) {
		this.airportNameCn = airportNameCn == null ? null : airportNameCn.trim();
	}

	public String getAirportNameEn() {
		return airportNameEn;
	}

	public void setAirportNameEn(String airportNameEn) {
		this.airportNameEn = airportNameEn == null ? null : airportNameEn.trim();
	}

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public String getDwphCode() {
		return dwphCode;
	}

	public void setDwphCode(String dwphCode) {
		this.dwphCode = dwphCode == null ? null : dwphCode.trim();
	}

	public String getDwphSpecCn() {
		return dwphSpecCn;
	}

	public void setDwphSpecCn(String dwphSpecCn) {
		this.dwphSpecCn = dwphSpecCn == null ? null : dwphSpecCn.trim();
	}

	public String getDwphSpecEn() {
		return dwphSpecEn;
	}

	public void setDwphSpecEn(String dwphSpecEn) {
		this.dwphSpecEn = dwphSpecEn == null ? null : dwphSpecEn.trim();
	}

	public String getDwphImageUrl() {
		return dwphImageUrl;
	}

	public void setDwphImageUrl(String dwphImageUrl) {
		this.dwphImageUrl = dwphImageUrl == null ? null : dwphImageUrl.trim();
	}

	public String getNwphCode() {
		return nwphCode;
	}

	public void setNwphCode(String nwphCode) {
		this.nwphCode = nwphCode == null ? null : nwphCode.trim();
	}

	public String getNwphSpecCn() {
		return nwphSpecCn;
	}

	public void setNwphSpecCn(String nwphSpecCn) {
		this.nwphSpecCn = nwphSpecCn == null ? null : nwphSpecCn.trim();
	}

	public String getNwphSpecEn() {
		return nwphSpecEn;
	}

	public void setNwphSpecEn(String nwphSpecEn) {
		this.nwphSpecEn = nwphSpecEn == null ? null : nwphSpecEn.trim();
	}

	public String getNwphImageUrl() {
		return nwphImageUrl;
	}

	public void setNwphImageUrl(String nwphImageUrl) {
		this.nwphImageUrl = nwphImageUrl == null ? null : nwphImageUrl.trim();
	}

	public String getDwpwCode() {
		return dwpwCode;
	}

	public void setDwpwCode(String dwpwCode) {
		this.dwpwCode = dwpwCode == null ? null : dwpwCode.trim();
	}

	public String getDwpwSpecCn() {
		return dwpwSpecCn;
	}

	public void setDwpwSpecCn(String dwpwSpecCn) {
		this.dwpwSpecCn = dwpwSpecCn == null ? null : dwpwSpecCn.trim();
	}

	public String getDwpwSpecEn() {
		return dwpwSpecEn;
	}

	public void setDwpwSpecEn(String dwpwSpecEn) {
		this.dwpwSpecEn = dwpwSpecEn == null ? null : dwpwSpecEn.trim();
	}

	public String getNwpwCode() {
		return nwpwCode;
	}

	public void setNwpwCode(String nwpwCode) {
		this.nwpwCode = nwpwCode == null ? null : nwpwCode.trim();
	}

	public String getNwpwSpecCn() {
		return nwpwSpecCn;
	}

	public void setNwpwSpecCn(String nwpwSpecCn) {
		this.nwpwSpecCn = nwpwSpecCn == null ? null : nwpwSpecCn.trim();
	}

	public String getNwpwSpecEn() {
		return nwpwSpecEn;
	}

	public void setNwpwSpecEn(String nwpwSpecEn) {
		this.nwpwSpecEn = nwpwSpecEn == null ? null : nwpwSpecEn.trim();
	}

	public String getDayTemp() {
		return dayTemp;
	}

	public void setDayTemp(String dayTemp) {
		this.dayTemp = dayTemp == null ? null : dayTemp.trim();
	}

	public String getNightTemp() {
		return nightTemp;
	}

	public void setNightTemp(String nightTemp) {
		this.nightTemp = nightTemp == null ? null : nightTemp.trim();
	}

	public String getDwdirCode() {
		return dwdirCode;
	}

	public void setDwdirCode(String dwdirCode) {
		this.dwdirCode = dwdirCode == null ? null : dwdirCode.trim();
	}

	public String getDwdirSpecCn() {
		return dwdirSpecCn;
	}

	public void setDwdirSpecCn(String dwdirSpecCn) {
		this.dwdirSpecCn = dwdirSpecCn == null ? null : dwdirSpecCn.trim();
	}

	public String getDwdirSpecEn() {
		return dwdirSpecEn;
	}

	public void setDwdirSpecEn(String dwdirSpecEn) {
		this.dwdirSpecEn = dwdirSpecEn == null ? null : dwdirSpecEn.trim();
	}

	public String getNwdirCode() {
		return nwdirCode;
	}

	public void setNwdirCode(String nwdirCode) {
		this.nwdirCode = nwdirCode == null ? null : nwdirCode.trim();
	}

	public String getNwdirSpecCn() {
		return nwdirSpecCn;
	}

	public void setNwdirSpecCn(String nwdirSpecCn) {
		this.nwdirSpecCn = nwdirSpecCn == null ? null : nwdirSpecCn.trim();
	}

	public String getNwdirSpecEn() {
		return nwdirSpecEn;
	}

	public void setNwdirSpecEn(String nwdirSpecEn) {
		this.nwdirSpecEn = nwdirSpecEn == null ? null : nwdirSpecEn.trim();
	}

	public String getIndexDisp() {
		return indexDisp;
	}

	public void setIndexDisp(String indexDisp) {
		this.indexDisp = indexDisp == null ? null : indexDisp.trim();
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
}