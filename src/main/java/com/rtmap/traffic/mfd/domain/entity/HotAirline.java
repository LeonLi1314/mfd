package com.rtmap.traffic.mfd.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 热门航空公司
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "search_hot_airline")
public class HotAirline {
	@Id
	@Column(name = "hot_airline_id", columnDefinition = "INT")
	private int hotAirlineId;
	@Column(name = "curr_airport_code", columnDefinition = "CHAR", length = 3)
	private String currAirportCode;
	@Column(name = "airline_code", columnDefinition = "CHAR", length = 2)
	private String airlineCode;
	@Column(name = "domint", nullable = false, columnDefinition = "CHAR", length = 1)
	private String domint;
	@Column(name = "priority", columnDefinition = "INT")
	private int priority;
	@Column(name = "name_cn")
	private String nameCn;
	@Column(name = "name_en")
	private String nameEn;

	public int getHotAirlineId() {
		return hotAirlineId;
	}

	public void setHotAirlineId(int hotAirlineId) {
		this.hotAirlineId = hotAirlineId;
	}

	public String getCurrAirportCode() {
		return currAirportCode;
	}

	public void setCurrAirportCode(String currAirportCode) {
		this.currAirportCode = currAirportCode;
	}

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
