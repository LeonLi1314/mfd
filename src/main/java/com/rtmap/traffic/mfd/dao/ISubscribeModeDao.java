package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.SubscribeMode;

/**
 * 订阅方式数据库访问层接口
 * @author liqingshan 2016-01-15
 *
 */
public interface ISubscribeModeDao {
	/**
	 * 查询所有订阅方式
	 * @return 订阅方式集合
	 */
	List<SubscribeMode> selectAll();
}
