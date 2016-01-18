package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.SubscriberCond;
import com.rtmap.traffic.mfd.domain.entity.SubscribeContract;

/**
 * 订阅契约数据库访问层接口
 * 
 * @author liqingshan 2016-01-15
 *
 */
public interface ISubscribeContractDao {
	/**
	 * 新增
	 * @param entity 订阅契约实体
	 * @return 受影响的行数
	 */
	int insert(SubscribeContract entity);
	
	/**
	 * 删除
	 * @param contractId 主键
	 * @return 受影响的行数
	 */
	int delete(int contractId);
	
	/**
	 * 根据订阅人查询条件查询有效的订阅契约
	 * 
	 * @param cond
	 *            订阅人查询条件
	 * @return 有效的订阅契约列表
	 */
	List<SubscribeContract> selectEffectBySubscriberCond(SubscriberCond cond);

	/**
	 * 根据航班Id条件查询订阅信息
	 * @param cond 航班Id条件
	 * @return 航班订阅信息
	 */
	SubscribeContract selectContractByFltIdCond(FltIdCond cond);
}
