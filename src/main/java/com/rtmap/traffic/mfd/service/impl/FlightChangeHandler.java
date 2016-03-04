package com.rtmap.traffic.mfd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.rtmap.traffic.mfd.dao.IFltChangeinfoPekDao;
import com.rtmap.traffic.mfd.dao.IMsgWechatCenterDao;
import com.rtmap.traffic.mfd.dao.ISubscribeContractDao;
import com.rtmap.traffic.mfd.domain.ArrdepFlag;
import com.rtmap.traffic.mfd.domain.DomintFlag;
import com.rtmap.traffic.mfd.domain.FltChangeinfoFiledsConst;
import com.rtmap.traffic.mfd.domain.entity.FltChangeinfoPek;
import com.rtmap.traffic.mfd.domain.entity.MsgWechatCenter;
import com.rtmap.traffic.mfd.domain.entity.SubscribeContract;

import rtmap.frame.util.DateUtils;
import rtmap.frame.util.StringUtils;

/**
 * 航班动态变更处理器
 * 
 * @author liqingshan
 *
 */
public class FlightChangeHandler implements InitializingBean, DisposableBean {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Thread thread;
	@Resource
	private IFltChangeinfoPekDao changetinfoPekDao;
	@Resource
	private ISubscribeContractDao subscribeContractDao;
	@Resource
	private IMsgWechatCenterDao msgWechatCenterDao;
	/*
	 * 处理间隔时间
	 */
	private int interval = 10;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public void destroy() throws Exception {
		if (thread == null || thread.isInterrupted())
			return;

		thread.interrupt();
		System.out.println("航班动态变更处理线程结束");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		thread = new Thread() {
			public void run() {
				System.out.println("航班动态变更处理线程启动");
				/*
				 * 非线程安全，当前环境使用单线程处理 单线程生产，多线程消费，目前只启动一个线程生产并消费
				 * 后期与航班动态变更打通之后，产生变更信息入库后直接写入缓存队列
				 */
				Queue<FltChangeinfoPek> queue = new LinkedList<>();

				while (true) {
					execute(queue);
				}
			}
		};

		// 启动处理变更线程
		thread.start();
	}

	private void execute(Queue<FltChangeinfoPek> queue) {
		try {

			FltChangeinfoPek changeInfo = queue.poll();

			if (changeInfo == null) {
				// 当队列深度小于0时，获取未处理的航班变更信息队列
				List<FltChangeinfoPek> list = changetinfoPekDao.selectNotExecuted(50);

				if (list == null || list.size() == 0)
					return;

				queue.addAll(list);
				changeInfo = queue.poll();
			}

			System.out.println(String.format("当前队列深度：%s", queue.size()));
			// 匹配订阅契约，生成推送信息，批量保存
			List<MsgWechatCenter> msgs = getMsgWechats(changeInfo);
			msgWechatCenterDao.batchInsert(msgs);
			// 删除变更信息
			changetinfoPekDao.updateExecuted(changeInfo.getRecId());
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

	/**
	 * 根据航班动态变更信息和订阅契约获取微信通知消息
	 * 
	 * @param changeInfo
	 *            航班动态变更信息
	 * @return 微信通知消息
	 */
	private List<MsgWechatCenter> getMsgWechats(FltChangeinfoPek changeInfo) {
		if (changeInfo == null)
			return null;

		String msgContent = getMsgContent(changeInfo);
		if (StringUtils.isNullOrEmpty(msgContent))
			return null;

		List<SubscribeContract> contracts = subscribeContractDao
				.selectEffectByKeywords(String.format("%s|%s", changeInfo.getFltId(), changeInfo.getArrdep()));

		List<MsgWechatCenter> rst = new ArrayList<>();

		for (SubscribeContract contract : contracts) {
			MsgWechatCenter msg = new MsgWechatCenter();
			msg.setCreateTime(new Date());
			msg.setCurrentAirport(contract.getCurrentAirport());
			msg.setDeleteFlag("N");
			msg.setMsgContent(msgContent);
			msg.setMsgEvent(contract.getSubscribeEvent());
			msg.setMsgModule(contract.getSubscribeModule());
			msg.setMsgTime(changeInfo.getCreateTime());
			String msgTitle = "航班动态变更";
			msg.setMsgTitle(msgTitle);
			msg.setOpenId(contract.getSubscriberId());
			msg.setReadFlag("N");
			msg.setSourceCode(contract.getSourceCode());

			rst.add(msg);
		}

		return rst;
	}

	private String getMsgContent(FltChangeinfoPek changeInfo) {
		String[] changes = changeInfo.getChangeInfo().split("\\|");

		if (changes == null || changes.length == 0)
			return "";

		Map<String, String> map = new HashMap<>();
		for (String keyValuePair : changes) {
			String[] ary = keyValuePair.split(":");
			map.put(ary[0], ary[1]);
		}

		StringBuilder sb = new StringBuilder(changes.length * 32);

		if (changeInfo.getArrdep().equals(ArrdepFlag.A.toString())) {
			sb.append("您关注的");
			sb.append(DomintFlag.valueOf(changeInfo.getDomint()).toLocaleString());
			sb.append("进港航班[");
			sb.append(changeInfo.getFltNo());
			sb.append("/");
			sb.append(DateUtils.formatDate(changeInfo.getSdt()));
			sb.append("]");

			if (map.containsKey(FltChangeinfoFiledsConst.fltStateCnAbbr)) {
				sb.append(String.format("，状态变更为[%s]", map.get(FltChangeinfoFiledsConst.fltStateCnAbbr)));
			}
			if (map.containsKey(FltChangeinfoFiledsConst.estTime)) {
				sb.append(String.format("，预计进港时间为[%s]", map.get(FltChangeinfoFiledsConst.estTime)));
			}
			if (map.containsKey(FltChangeinfoFiledsConst.actTime)) {
				sb.append(String.format("，实际进港时间为[%s]", map.get(FltChangeinfoFiledsConst.actTime)));
			}
			if (map.containsKey(FltChangeinfoFiledsConst.bltDisp)) {
				sb.append(String.format("，请于[%s]行李转盘提取您的行李", map.get(FltChangeinfoFiledsConst.bltDisp)));
			}
			if (map.containsKey(FltChangeinfoFiledsConst.termOrExitsNo)) {
				sb.append(String.format("，进港到达口[%s]", map.get(FltChangeinfoFiledsConst.termOrExitsNo)));
			}

			sb.append("。");
		} else {
			sb.append("您关注的");
			sb.append(DomintFlag.valueOf(changeInfo.getDomint()).toLocaleString());
			sb.append("出港航班[");
			sb.append(changeInfo.getFltNo());
			sb.append("/");
			sb.append(DateUtils.formatDate(changeInfo.getSdt()));
			sb.append("]");

			if (map.containsKey(FltChangeinfoFiledsConst.firstCntOt)) {
				sb.append(String.format("，目前已开放值机柜台", map.get(FltChangeinfoFiledsConst.firstCntOt)));
			}
			if (map.containsKey(FltChangeinfoFiledsConst.fltStateCnAbbr)) {
				sb.append(String.format("，状态变更为[%s]", map.get(FltChangeinfoFiledsConst.fltStateCnAbbr)));
			}
			if (map.containsKey(FltChangeinfoFiledsConst.estTime)) {
				sb.append(String.format("，预计出港时间为[%s]", map.get(FltChangeinfoFiledsConst.estTime)));
			}
			if (map.containsKey(FltChangeinfoFiledsConst.actTime)) {
				sb.append(String.format("，实际出港时间为[%s]", map.get(FltChangeinfoFiledsConst.actTime)));
			}
			if (map.containsKey(FltChangeinfoFiledsConst.gatDisp)) {
				sb.append(String.format("，当前登机口调整为[%s]", map.get(FltChangeinfoFiledsConst.gatDisp)));
			}

			sb.append("。");
		}

		return sb.toString();
	}
}
