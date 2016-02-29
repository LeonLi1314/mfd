package com.rtmap.traffic.mfd.domain.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 航班动态报文ERRO节点
 * 
 * @author liqingshan
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorElement {
	@XmlElement(name = "CODE")
	private String code;// 错误代码
	@XmlElement(name = "MESG")
	private String message;// 错误信息

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
