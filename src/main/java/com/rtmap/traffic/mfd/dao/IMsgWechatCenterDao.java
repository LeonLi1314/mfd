package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.entity.MsgWechatCenter;

/**
 * 微信公众号消息中心数据访问层接口
 * 
 * @author liqingshan
 *
 */
public interface IMsgWechatCenterDao {
	/**
	 * 批量插入
	 * @param list 消息集合
	 */
	void batchInsert(List<MsgWechatCenter> list);
	
	/**
	 * 查询未读信息的数量
	 * @param openId 公众号个人Id
	 * @return 数量
	 */
	int selectUnreadCount(String openId);

	/**
	 * 查询信息总数量
	 * @param openId 公众号个人Id
	 * @param isAll 是否全部信息
	 * @return 总数量
	 */
	int selectMessagesTotalCount(String openId, boolean isAll);
	
	/**
	 * 分页查询个人的消息
	 * @param cond 分页查询条件
	 * @param isAll 是否全部信息
	 * @return 一页消息集合
	 */
	List<MsgWechatCenter> selectPageMessages(PageCond<String> cond, boolean isAll);
	
	/**
	 * 更新已读标记
	 * @param msgId 信息Id
	 */
	void updateReadFlag(int msgId);
	
	/**
	 * 删除信息
	 * @param msgId 信息Id
	 */
	void updateDeleteFlag(int msgId);
}
