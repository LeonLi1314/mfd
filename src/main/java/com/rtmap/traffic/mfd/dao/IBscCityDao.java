package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.City;

/**
 * 城市基础数据访问接口
 * @author liqingshan 2016-01-11
 *
 */
public interface IBscCityDao {
	/**
	 * 查询所有城市
	 * @return 城市集合
	 */
	List<City> selectAll();

	/**
	 * 查询国内城市
	 * @return 城市集合
	 */
	List<City> selectDomestic();

	/**
	 * 查询国际城市
	 * @return 城市集合
	 */
	List<City> selectInternational();
}
