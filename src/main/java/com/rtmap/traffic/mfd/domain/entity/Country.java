package com.rtmap.traffic.mfd.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 国家航站
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Entity
@Table(name = "bas_country")
public class Country {
	@Id
	@Column(name = "country_code", columnDefinition = "CHAR", length = 2)
	private String countryCode;
	@Column(name = "name_abbr_cn")
	private String nameAbbrCn;
	@Column(name = "name_cn")
	private String nameCn;
	@Column(name = "name_en")
	private String nameEn;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
}
