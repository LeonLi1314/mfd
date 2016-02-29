package com.rtmap.traffic.mfd.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rtmap.traffic.mfd.common.SpringContext;

/**
 * Application Lifecycle Listener implementation class DataSynchronizedListener
 *
 */
@WebListener
public class DataSynchronizedListener implements ServletContextListener {
	Thread thread;
	private boolean canStop = false;

	/**
	 * Context容器销毁，在所有的Filter和Servlet的destroy方法调用之后contextDestroyed接口被调用
	 */
	public void contextDestroyed(ServletContextEvent sce) {

		if (thread != null) {
			thread.interrupt();
		}
	}

	/**
	 * Context容器初始化时触发，在所有的Filter和Servlet的init方法调用之前contextInitialized接口先被调用
	 */
	public void contextInitialized(ServletContextEvent sce) {
		String key = "synchronizedTime";
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		SpringContext.setApplicationContext(context);
		//FlightPekSynchronize fs = context.getBean(FlightPekSynchronize.class);
	}
}
