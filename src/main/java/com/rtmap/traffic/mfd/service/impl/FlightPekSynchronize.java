package com.rtmap.traffic.mfd.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtmap.traffic.mfd.common.AirportCodes;
import com.rtmap.traffic.mfd.domain.msg.MetaElement;
import com.rtmap.traffic.mfd.domain.msg.SyncConfig;
import com.rtmap.traffic.mfd.service.IFlightFactory;

import rtmap.frame.util.DatePatterns;
import rtmap.frame.util.DateUtils;
import rtmap.frame.util.FileUtils;
import rtmap.frame.util.HttpClientUtils;
import rtmap.frame.util.JaxbUtils;

/**
 * 航班动态同步
 * 
 * @author liqingshan 2016-01-26
 *
 */
public class FlightPekSynchronize {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private IFlightFactory factory;
	// @Resource(name="pekService")
	// IFlightService pekService;
	private HttpClientUtils httpClient;

	public void setHttpClient(HttpClientUtils httpClient) {
		this.httpClient = httpClient;
	}

	private Thread thread;

	public void afterPropertiesSet() throws Exception {
		thread = new Thread() {
			public void run() {
				URL url = Configuration.class.getClassLoader().getResource("synchroize.txt");
				if (url == null)
					logger.error("synchroize.txt配置文件不存在！");

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				JsonParser jsonParser;
				SyncConfig sc = null;
				Date reqTime = null;

				try {
					jsonParser = objectMapper.getFactory().createParser(url);
					System.out.println(url.getPath());
					sc = jsonParser.readValueAs(SyncConfig.class);
					reqTime = DateUtils.parseDate(sc.getParas().get("syncTime"), DatePatterns.POPULAR_DATE24TIME);
				} catch (JsonParseException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				System.out.println("-----------初始化------------");

				while (true) {
					try {
						Map<String, String> map = new HashMap<>();
						map.put("syncTime", Long.toString(reqTime.getTime()));
						// 调用webservice，返回报文数据
						reqTime = new Date();
						String msg = httpClient.get(map);
						Element outmsg = getMsgElement(msg);
						MetaElement meta = getMeta(outmsg);

						if (meta.getOpResult().toUpperCase().equals("C")) {
							System.out.println("-----------服务端正常------------");
							if (meta.getCount() > 0) {
								factory.getService(AirportCodes.PEK).execute(outmsg.element("DATA"));
							}
							
							// 处理完成，回写配置文件
							sc.getParas().put("syncTime",
									DateUtils.formatDate(reqTime, DatePatterns.POPULAR_DATE24TIME));
							String content = objectMapper.writeValueAsString(sc);
							FileUtils.overrideFileMethodA(url.getPath(), content);
						} else {// 获取报文失败
							System.out.println("-----------服务端错误------------");
							// 记录失败日志
							String error = "报文获取失败：" + meta.getError().getCode() + "-" + meta.getError().getMessage();
							logger.info(error);
						}

						System.out.println("-----------结束循环------------");
					} catch (Exception e) {
						logger.error(e.toString());
						e.printStackTrace();
					} finally {
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							logger.error(e.toString());
						}
					}
				}
			}
		};

		thread.start();
		// thread.run();
	}

	private MetaElement getMeta(Element outmsg) {
		Element element = outmsg.element("META");
		JaxbUtils inboJaxb = new JaxbUtils(MetaElement.class);
		return inboJaxb.<MetaElement> fromXml(element.asXML());
	}

	private Element getMsgElement(String msg) {
		String xml = "E:\\download\\msg-1.txt";
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
		Element body = root.element("Body");
		Element outmsg = body.element("outmsg");

		return outmsg;
	}
}
