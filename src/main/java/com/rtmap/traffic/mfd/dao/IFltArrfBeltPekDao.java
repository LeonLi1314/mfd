package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.ArrfBeltPek;

/**
 * 到港航班行李转盘数据接口访问层
 * 
 * @author liqingshan
 *
 */
public interface IFltArrfBeltPekDao {
	/**
	 * 批量插入
	 * 
	 * @param list
	 *            集合
	 */
	void batchInsert(List<ArrfBeltPek> list);

	/**
	 * 先删后批量插入
	 * 
	 * @param list
	 *            集合
	 */
	void batchInsertAfterDelete(List<ArrfBeltPek> list);
}
