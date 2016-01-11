package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.Country;

/**
 * 国家基础数据访问接口
 * 
 * @author liqingshan 2016-01-06
 *
 */
public interface IBscCountryDao {
	/**
	 * 查询所有国家
	 * 
	 * @return 国家信息集合
	 */
	List<Country> selectAll();
}
