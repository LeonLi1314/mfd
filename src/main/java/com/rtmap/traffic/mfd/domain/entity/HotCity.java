package com.rtmap.traffic.mfd.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 热门城市
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "search_hot_city")
public class HotCity {
	@Id
	@Column(name = "hot_city_id", columnDefinition = "INT")
	private int hotCityId;
	@Column(name = "curr_airport_code", columnDefinition = "CHAR", length = 3)
	private String currAirportCode;
	@Column(name = "city_code", columnDefinition = "CHAR", length = 3)
	private String cityCode;
	@Column(name = "domint", nullable = false, columnDefinition = "CHAR", length = 1)
	private String domint;
	@Column(name = "priority", columnDefinition = "INT")
	private int priority;
	@Column(name = "name_cn")
	private String nameCn;
	@Column(name = "name_en")
	private String nameEn;

	public int getHotCityId() {
		return hotCityId;
	}

	public void setHotCityId(int hotCityId) {
		this.hotCityId = hotCityId;
	}

	public String getCurrAirportCode() {
		return currAirportCode;
	}

	public void setCurrAirportCode(String currAirportCode) {
		this.currAirportCode = currAirportCode;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
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
}
