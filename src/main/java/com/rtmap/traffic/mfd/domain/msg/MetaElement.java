package com.rtmap.traffic.mfd.domain.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 报文元数据META节点
 * 
 * @author liqingshan
 *
 */
@XmlRootElement(name = "META")
@XmlAccessorType(XmlAccessType.FIELD)
public class MetaElement {
	@XmlElement(name = "TIMS")
	private long requestTs;
	@XmlElement(name = "REST")
	private long responseTs;
	@XmlElement(name = "OPAI")
	private String opResult;
	@XmlElement(name = "COUN")
	private int count;
	@XmlElement(name = "ERRO")
	private ErrorElement error;

	public long getRequestTs() {
		return requestTs;
	}

	public void setRequestTs(long requestTs) {
		this.requestTs = requestTs;
	}

	public long getResponseTs() {
		return responseTs;
	}

	public void setResponseTs(long responseTs) {
		this.responseTs = responseTs;
	}

	public String getOpResult() {
		return opResult;
	}

	public void setOpResult(String opResult) {
		this.opResult = opResult;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ErrorElement getError() {
		return error;
	}

	public void setError(ErrorElement error) {
		this.error = error;
	}
}
