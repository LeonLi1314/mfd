package com.rtmap.traffic.mfd.domain.cond;

/**
 * 订阅人查询条件
 * 
 * @author liqingshan 2016-01-15
 *
 */
public class SubscriberCond {
	private String currentAirport = "PEK";
	private String sourceCode = "PEK-WECHAT-SHAKE";
	private int subscribeId = 1;
	private String subscriberId = "openId";
	private String subscribeModule = "FLIGHT";
	private String subscribeEvent = "DYNAMICS";

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
