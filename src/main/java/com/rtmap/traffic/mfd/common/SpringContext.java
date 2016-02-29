package com.rtmap.traffic.mfd.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * spring应用上下文
 * 
 * @author liqingshan
 *
 */
public class SpringContext {
	private static ApplicationContext applicationContext; // Spring应用上下文环境

	/**
	 * 设置spring应用上下文
	 * 
	 * @param applicationContext
	 *            应用上下文
	 * @throws BeansException
	 *             bean异常
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContext.applicationContext = applicationContext;
	}

	/**
	 * 获取当前应用上下文
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据bean名称获取bean实例
	 * 
	 * @param name
	 *            bean名称
	 * @return bean实例
	 * @throws BeansException
	 *             bean异常
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}
}
