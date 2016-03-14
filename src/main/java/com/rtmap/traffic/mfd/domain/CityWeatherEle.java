package com.rtmap.traffic.mfd.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 城市天气元素
 * @author liqingshan
 *
 */
@XmlRootElement(name = "WETR")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityWeatherEle {
	@XmlElement(name = "WTC1")
    private Integer areaId;

	@XmlElement(name = "WTCD")
    private String cityCode;

	@XmlElement(name = "WTC3")
    private String cityNameCn;

	@XmlElement(name = "WTC2")
    private String cityNameEn;

	@XmlElement(name = "WTAP")
    private String airportCode;

	@XmlElement(name = "WTAC")
    private String airportNameCn;

	@XmlElement(name = "WTAE")
    private String airportNameEn;

	@XmlElements(@XmlElement(name = "WTDD"))
	private List<WeatherEle> weathers;

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
		this.cityCode = cityCode;
	}

	public String getCityNameCn() {
		return cityNameCn;
	}

	public void setCityNameCn(String cityNameCn) {
		this.cityNameCn = cityNameCn;
	}

	public String getCityNameEn() {
		return cityNameEn;
	}

	public void setCityNameEn(String cityNameEn) {
		this.cityNameEn = cityNameEn;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportNameCn() {
		return airportNameCn;
	}

	public void setAirportNameCn(String airportNameCn) {
		this.airportNameCn = airportNameCn;
	}

	public String getAirportNameEn() {
		return airportNameEn;
	}

	public void setAirportNameEn(String airportNameEn) {
		this.airportNameEn = airportNameEn;
	}

	public List<WeatherEle> getWeathers() {
		return weathers;
	}

	public void setWeathers(List<WeatherEle> weathers) {
		this.weathers = weathers;
	}
}
