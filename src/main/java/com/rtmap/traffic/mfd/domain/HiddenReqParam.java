package com.rtmap.traffic.mfd.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lqs.frame.util.DatePatterns;

/**
 * 隐藏的请求参数
 * 
 * @author liiqingshan 2016-01-11
 *
 */
public class HiddenReqParam {
	/**
	 * 请求时间
	 */
	private Date reqTime;
	/**
	 * 当前请求人的公众号标识
	 */
	private String openId;

	@JSONField(format = DatePatterns.POPULAR_DATE24TIME)
	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
