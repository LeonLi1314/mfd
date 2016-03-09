package com.rtmap.traffic.mfd.service;

import com.rtmap.traffic.mfd.domain.PageRst;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.dto.WechatMsgDto;

/**
 * 公众号信息服务层接口
 * 
 * @author liqingshan
 *
 */
public interface IWechatMsgService {
	/**
	 * 获取未读消息数量
	 * @param openId 公众号唯一标识
	 * @return	维度消息数量
	 */
	public int getUnreadWechatMsgsCount(String openId);

	/**
	 * 获取一页信息
	 * @param cond 分页查询条件
	 * @return 一页信息集合
	 */
	public PageRst<WechatMsgDto> getWechatMsgs(PageCond<String> cond);

	/**
	 * 获取一页未读信息
	 * @param cond 分页查询条件
	 * @return 一页未读信息集合
	 */
	public PageRst<WechatMsgDto> getUnreadWechatMsgs(PageCond<String> cond);

	/**
	 * 修改是否已读的标记
	 * @param msgId 信息唯一标识
	 */
	public void modifyWechatMsgReadFlag(int msgId);

	/**
	 * 根据Id删除信息
	 * @param msgId 信息唯一标识
	 */
	public void deleteWechatMsgById(int msgId);
}
