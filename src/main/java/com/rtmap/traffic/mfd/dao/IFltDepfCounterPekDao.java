package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.DepfCounterPek;

/**
 * 离港航班值机柜台数据接口访问层
 * @author liqingshan
 *
 */
public interface IFltDepfCounterPekDao {
	/**
	 * 批量插入
	 * 
	 * @param list
	 *            集合
	 */
	void batchInsert(List<DepfCounterPek> list);

	/**
	 * 先删后批量插入
	 * 
	 * @param list
	 *            集合
	 */
	void batchInsertAfterDelete(List<DepfCounterPek> list);

}
