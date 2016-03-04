package com.rtmap.traffic.mfd.domain;

/**
 * 到港离港标识
 * 
 * @author liqingshan 2016-01-12
 *
 */
public enum ArrdepFlag {
	/**
	 * 到港
	 */
	A{
		public String toLocaleString() {
			return "进港";
		}
	},
	/**
	 * 离港
	 */
	D{
		public String toLocaleString() {
			return "出港";
		}
	};
	
	public abstract String toLocaleString();
}
