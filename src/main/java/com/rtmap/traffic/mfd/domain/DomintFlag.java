package com.rtmap.traffic.mfd.domain;

/**
 * 国际国内标识
 * 
 * @author liqingshan 2016-01-11
 *
 */
public enum DomintFlag {
	/**
	 * 国内
	 */
	D {
		public String toLocaleString() {
			return "国内";
		}
	},
	/**
	 * 国际
	 */
	I {
		public String toLocaleString() {
			return "国际";
		}
	},
	/**
	 * 国际国内混合
	 */
	M {
		public String toLocaleString() {
			return "国际国内混合";
		}
	};

	public abstract String toLocaleString();
}
