package com.rtmap.traffic.mfd.domain.dto;

import com.rtmap.traffic.mfd.domain.ArrdepFlag;

/**
 * 航班动态关注数据传输对象
 * 
 * @author liqingshan 2016-01-14
 *
 */
public class FollowFltDto {
	private boolean isFollow;
	private int contractId;
	private String fltId;
	private ArrdepFlag arrdep;
	private int subscribeId = 1;
	private String subscriberId = "openId";
	private String currentAirport = "PEK";
	private String sourceCode = "PEK-WECHAT-SHAKE";
	private String subscribeModule = "FLIGHT";
	private String subscribeEvent = "DYNAMICS";

	public boolean isFollow() {
		return isFollow;
	}

	public void setFollow(boolean isFollow) {
		this.isFollow = isFollow;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getFltId() {
		return fltId;
	}

	public void setFltId(String fltId) {
		this.fltId = fltId;
	}

	public ArrdepFlag getArrdep() {
		return arrdep;
	}

	public void setArrdep(ArrdepFlag arrdep) {
		this.arrdep = arrdep;
	}

	public int getSubscribeId() {
		return subscribeId;
	}

	public void setSubscribeId(int subscribeId) {
		this.subscribeId = subscribeId;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getCurrentAirport() {
		return currentAirport;
	}

	public void setCurrentAirport(String currentAirport) {
		this.currentAirport = currentAirport;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSubscribeModule() {
		return subscribeModule;
	}

	public void setSubscribeModule(String subscribeModule) {
		this.subscribeModule = subscribeModule;
	}

	public String getSubscribeEvent() {
		return subscribeEvent;
	}

	public void setSubscribeEvent(String subscribeEvent) {
		this.subscribeEvent = subscribeEvent;
	}
}
