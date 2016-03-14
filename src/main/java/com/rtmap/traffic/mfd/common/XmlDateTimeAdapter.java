package com.rtmap.traffic.mfd.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * jaxb日期转化适配器
 * 
 * @author liqingshan 2016-02-15
 *
 */
public class XmlDateTimeAdapter extends XmlAdapter<String, Date> {
	private String pattern = "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat sdf = new SimpleDateFormat(pattern);

	@Override
	public String marshal(Date v) throws Exception {
		return sdf.format(v);
	}

	@Override
	public Date unmarshal(String v) throws Exception {
		return sdf.parse(v);
	}
}
