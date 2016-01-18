package com.rtmap.traffic.mfd.service;

import java.util.List;

import com.rtmap.traffic.mfd.domain.OpRst;
import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.SubscriberCond;
import com.rtmap.traffic.mfd.domain.dto.FollowFltDto;
import com.rtmap.traffic.mfd.domain.entity.SubscribeContract;

/**
 * 订阅关注模块控制器
 * @author liqingshan 2016-01-14
 *
 */
public interface ISubscribeService {
	/**
	 * 关注航班
	 * @param dto 关注信息
	 * @return 操作结果
	 */
	OpRst followFlight(FollowFltDto dto);

	/**
	 * 取消关注航班
	 * @param dto 关注信息
	 * @return 操作结果
	 */
	OpRst unfollowFlight(FollowFltDto dto);

	/**
	 * 根据订阅者查询条件获取有效的契约
	 * @param cond 订阅者查询条件
	 * @return 有效的契约
	 */
	List<SubscribeContract> getEffectContractsBySubscriberCond(SubscriberCond cond);

	/**
	 * 根据航班Id条件查询订阅信息
	 * @param cond 航班Id查询条件
	 * @return 航班订阅信息
	 */
	SubscribeContract getContractByFltIdCond(FltIdCond cond);
}
