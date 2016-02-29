package com.rtmap.traffic.mfd.domain.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rtmap.traffic.mfd.domain.entity.ArrfPek;
import com.rtmap.traffic.mfd.domain.entity.DepfPek;

/**
 * 航班动态报文Data节点
 * 
 * @author liqingshan
 *
 */
@XmlRootElement(name = "DATA")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataElement {
	@XmlElement(name = "INBO")
	private List<ArrfPek> arrfs;
	@XmlElement(name = "FLST")
	private List<DepfPek> depfs;

	public List<ArrfPek> getArrfs() {
		return arrfs;
	}

	public void setArrfs(List<ArrfPek> arrfs) {
		this.arrfs = arrfs;
	}

	public List<DepfPek> getDepfs() {
		return depfs;
	}

	public void setDepfs(List<DepfPek> depfs) {
		this.depfs = depfs;
	}
}
