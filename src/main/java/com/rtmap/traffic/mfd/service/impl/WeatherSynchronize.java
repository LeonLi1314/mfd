package com.rtmap.traffic.mfd.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rtmap.traffic.mfd.service.ICityWeatherService;

import rtmap.frame.util.HttpClientUtils;

/**
 * 天气同步
 * 
 * @author liqingshan
 *
 */
public class WeatherSynchronize {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	ICityWeatherService weatherService;
	private HttpClientUtils httpClient;

	public void setHttpClient(HttpClientUtils httpClient) {
		this.httpClient = httpClient;
	}
	
	private Thread thread;

	/*
	 * 处理间隔时间
	 */
	private int interval = 3600;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void destroy() throws Exception {
		if (thread == null || thread.isInterrupted())
			return;

		thread.interrupt();
		System.out.println("天气同步线程结束");
	}

	public void init() throws Exception {
		thread = new Thread() {
			public void run() {
				System.out.println("天气同步线程启动");
				
				try {
					while (true) {
						// 访问远程接口，返回数据
						// String msg = httpClient.get(null);
						String msg = "";
						Element weathersEle = getWeathersElement(msg);
						
						weatherService.execute(weathersEle);
					}
				} catch (Exception e) {
					logger.error(e.toString());
					e.printStackTrace();
				} finally {
					try {
						Thread.sleep(interval * 1000);
					} catch (InterruptedException e) {
					}
				}
			}
		};

		// 启动处理变更线程
		thread.start();
	}
	
	private Element getWeathersElement(String msg) {
		String xml = "E:\\download\\msg-weather.txt";
		Document document = null;
		
		try {
			SAXReader reader = new SAXReader();
			// InputStream is = new ByteArrayInputStream(msg.getBytes());
			// document = reader.read(is);
			document = reader.read(new File(xml));
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element root = document.getRootElement();
		Element weathers = root.element("WTRS");

		return weathers;
	}
}
