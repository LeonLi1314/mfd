package com.rtmap.traffic.mfd.domain.dto;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * 出发或目的地数据传输对象
 * 
 * @author liqingshan 2016-01-11
 *
 */
@JSONType(orders = "", ignores = "")
public class StartingOrDestinationDto {
	private String code;
	private String nameCn;
	private String nameEn;
	private String fullPinyin;
	private String firstLetter;
	private int type;

	/**
	 * 获取编码（对应城市或机场编码）
	 * 
	 * @return 编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置编码
	 * 
	 * @param code
	 *            城市或机场编码
	 */
	public void setCode(String code) {
		this.code = code;
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

	/**
	 * 获取类型（0:城市；1:机场）
	 * 
	 * @return 对象类型
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            对象类型（0:城市；1:机场）
	 */
	public void setType(int type) {
		this.type = type;
	}
}
