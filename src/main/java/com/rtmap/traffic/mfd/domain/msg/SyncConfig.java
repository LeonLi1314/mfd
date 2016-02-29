package com.rtmap.traffic.mfd.domain.msg;

import java.util.Map;

/**
 * 同步配置
 * 
 * @author liqingshan
 *
 */
public class SyncConfig {
	private String scheme;
	private String host;
	private String port;
	private String actionPath;
	private Map<String, String> paras;

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getActionPath() {
		return actionPath;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	public Map<String, String> getParas() {
		return paras;
	}

	public void setParas(Map<String, String> paras) {
		this.paras = paras;
	}
}
