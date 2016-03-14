package com.rtmap.traffic.mfd.domain;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.rtmap.traffic.mfd.common.XmlDateAdapter;
import com.rtmap.traffic.mfd.common.XmlDateTimeAdapter;

/**
 * 天气元素
 * @author liqingshan
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherEle {
	@XmlElement(name = "WTWD")
	@XmlJavaTypeAdapter(value = XmlDateAdapter.class)
	private Date statDate;

	@XmlElement(name = "WTFA")
	private String dwphCode;

	@XmlElement(name = "FACN")
	private String dwphSpecCn;

	@XmlElement(name = "FAEN")
	private String dwphSpecEn;

	@XmlElement(name = "FAIM")
	private String dwphImageUrl;

	@XmlElement(name = "WTFB")
	private String nwphCode;

	@XmlElement(name = "FBCN")
	private String nwphSpecCn;

	@XmlElement(name = "FBEN")
	private String nwphSpecEn;

	@XmlElement(name = "FBIM")
	private String nwphImageUrl;

	@XmlElement(name = "WTFG")
	private String dwpwCode;

	@XmlElement(name = "FGCN")
	private String dwpwSpecCn;

	@XmlElement(name = "FGEN")
	private String dwpwSpecEn;

	@XmlElement(name = "WTFH")
	private String nwpwCode;

	@XmlElement(name = "FHCN")
	private String nwpwSpecCn;

	@XmlElement(name = "FHEN")
	private String nwpwSpecEn;

	@XmlElement(name = "WTFC")
	private String dayTemp;

	@XmlElement(name = "WTFD")
	private String nightTemp;

	@XmlElement(name = "WTFE")
	private String dwdirCode;

	@XmlElement(name = "FECN")
	private String dwdirSpecCn;

	@XmlElement(name = "FEEN")
	private String dwdirSpecEn;

	@XmlElement(name = "WTFF")
	private String nwdirCode;

	@XmlElement(name = "FFCN")
	private String nwdirSpecCn;

	@XmlElement(name = "FFEN")
	private String nwdirSpecEn;

	@XmlElement(name = "WTDT")
	@XmlJavaTypeAdapter(value = XmlDateTimeAdapter.class)
	private Date releaseTime;

	@XmlElements(@XmlElement(name = "WTI1"))
	private List<String> weatherIndexes;

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
		this.dwphCode = dwphCode;
	}

	public String getDwphSpecCn() {
		return dwphSpecCn;
	}

	public void setDwphSpecCn(String dwphSpecCn) {
		this.dwphSpecCn = dwphSpecCn;
	}

	public String getDwphSpecEn() {
		return dwphSpecEn;
	}

	public void setDwphSpecEn(String dwphSpecEn) {
		this.dwphSpecEn = dwphSpecEn;
	}

	public String getDwphImageUrl() {
		return dwphImageUrl;
	}

	public void setDwphImageUrl(String dwphImageUrl) {
		this.dwphImageUrl = dwphImageUrl;
	}

	public String getNwphCode() {
		return nwphCode;
	}

	public void setNwphCode(String nwphCode) {
		this.nwphCode = nwphCode;
	}

	public String getNwphSpecCn() {
		return nwphSpecCn;
	}

	public void setNwphSpecCn(String nwphSpecCn) {
		this.nwphSpecCn = nwphSpecCn;
	}

	public String getNwphSpecEn() {
		return nwphSpecEn;
	}

	public void setNwphSpecEn(String nwphSpecEn) {
		this.nwphSpecEn = nwphSpecEn;
	}

	public String getNwphImageUrl() {
		return nwphImageUrl;
	}

	public void setNwphImageUrl(String nwphImageUrl) {
		this.nwphImageUrl = nwphImageUrl;
	}

	public String getDwpwCode() {
		return dwpwCode;
	}

	public void setDwpwCode(String dwpwCode) {
		this.dwpwCode = dwpwCode;
	}

	public String getDwpwSpecCn() {
		return dwpwSpecCn;
	}

	public void setDwpwSpecCn(String dwpwSpecCn) {
		this.dwpwSpecCn = dwpwSpecCn;
	}

	public String getDwpwSpecEn() {
		return dwpwSpecEn;
	}

	public void setDwpwSpecEn(String dwpwSpecEn) {
		this.dwpwSpecEn = dwpwSpecEn;
	}

	public String getNwpwCode() {
		return nwpwCode;
	}

	public void setNwpwCode(String nwpwCode) {
		this.nwpwCode = nwpwCode;
	}

	public String getNwpwSpecCn() {
		return nwpwSpecCn;
	}

	public void setNwpwSpecCn(String nwpwSpecCn) {
		this.nwpwSpecCn = nwpwSpecCn;
	}

	public String getNwpwSpecEn() {
		return nwpwSpecEn;
	}

	public void setNwpwSpecEn(String nwpwSpecEn) {
		this.nwpwSpecEn = nwpwSpecEn;
	}

	public String getDayTemp() {
		return dayTemp;
	}

	public void setDayTemp(String dayTemp) {
		this.dayTemp = dayTemp;
	}

	public String getNightTemp() {
		return nightTemp;
	}

	public void setNightTemp(String nightTemp) {
		this.nightTemp = nightTemp;
	}

	public String getDwdirCode() {
		return dwdirCode;
	}

	public void setDwdirCode(String dwdirCode) {
		this.dwdirCode = dwdirCode;
	}

	public String getDwdirSpecCn() {
		return dwdirSpecCn;
	}

	public void setDwdirSpecCn(String dwdirSpecCn) {
		this.dwdirSpecCn = dwdirSpecCn;
	}

	public String getDwdirSpecEn() {
		return dwdirSpecEn;
	}

	public void setDwdirSpecEn(String dwdirSpecEn) {
		this.dwdirSpecEn = dwdirSpecEn;
	}

	public String getNwdirCode() {
		return nwdirCode;
	}

	public void setNwdirCode(String nwdirCode) {
		this.nwdirCode = nwdirCode;
	}

	public String getNwdirSpecCn() {
		return nwdirSpecCn;
	}

	public void setNwdirSpecCn(String nwdirSpecCn) {
		this.nwdirSpecCn = nwdirSpecCn;
	}

	public String getNwdirSpecEn() {
		return nwdirSpecEn;
	}

	public void setNwdirSpecEn(String nwdirSpecEn) {
		this.nwdirSpecEn = nwdirSpecEn;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public List<String> getWeatherIndexes() {
		return weatherIndexes;
	}

	public void setWeatherIndexes(List<String> weatherIndexes) {
		this.weatherIndexes = weatherIndexes;
	}
}
