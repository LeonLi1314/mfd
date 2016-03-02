package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.DepfGatePek;

/**
 * 离港航班登机口数据接口访问层
 * @author liqingshan
 *
 */
public interface IFltDepfGatePekDao {
	/**
	 * 批量插入
	 * 
	 * @param list
	 *            集合
	 */
	void batchInsert(List<DepfGatePek> list);

	/**
	 * 先删后批量插入
	 * 
	 * @param list
	 *            集合
	 */
	void batchInsertAfterDelete(List<DepfGatePek> list);

}
