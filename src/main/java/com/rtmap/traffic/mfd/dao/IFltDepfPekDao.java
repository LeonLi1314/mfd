package com.rtmap.traffic.mfd.dao;

import java.util.Date;
import java.util.List;

import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.entity.DepfPek;

/**
 * 首都机场离港航班动态
 * 
 * @author liqingshan 2016-01-12
 *
 */
public interface IFltDepfPekDao {
	/**
	 * 按航班Id主键查找
	 * 
	 * @param depfId
	 *            航班Id
	 * @return 航班信息
	 */
	DepfPek selectByDepfId(String depfId);

	/**
	 * 按航班Id主键查找
	 * 
	 * @param depfIds
	 *            航班Id
	 * @return 航班信息
	 */
	List<DepfPek> selectByDepfIds(List<String> depfIds);

	/**
	 * 根据航班号条件查询航班信息列表
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @param cond
	 *            航班号查询条件
	 * @return 航班信息列表
	 */
	List<DepfPek> selectByFltNoCond(int pageNo, int pageSize, FltNoCond cond);

	/**
	 * 根据航班号条件查询符合条件的记录数
	 * 
	 * @param cond
	 *            航班号查询条件
	 * @return 总记录数
	 */
	int selectTotalCountByFltNoCond(FltNoCond cond);

	/**
	 * 根据起降地条件查询航班信息列表
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @param airportCode
	 *            机场航站编码
	 * @param airlineCode
	 *            航空公司编码
	 * @param queryDate
	 *            查询日期
	 * @return 航班信息列表
	 */
	List<DepfPek> selectByPlaceCond(int pageNo, int pageSize, String airportCode, String airlineCode, Date queryDate);

	/**
	 * 根据起降地条件查询航班信息列表
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @param airportCodes
	 *            机场航站编码集合
	 * @param airlineCode
	 *            航空公司编码
	 * @param queryDate
	 *            查询日期
	 * @return 航班信息列表
	 */
	List<DepfPek> selectByPlaceCond(int pageNo, int pageSize, List<String> airportCodes, String airlineCode,
			Date queryDate);

	/**
	 * 根据起降地条件查询符合条件的航班信息总记录数
	 * 
	 * @param airportCodes
	 *            机场航站编码集合
	 * @param airlineCode
	 *            航空公司编码
	 * @param queryDate
	 *            查询日期
	 * @return 总记录数
	 */
	int selectTotalCountByPlaceCond(List<String> airportCodes, String airlineCode, Date queryDate);
}
