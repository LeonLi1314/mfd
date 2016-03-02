package com.rtmap.traffic.mfd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.entity.ArrfPek;

/**
 * 首都机场到港航班动态
 * 
 * @author liqingshan 2016-01-12
 *
 */
public interface IFltArrfPekDao {
	String insert(ArrfPek arrfPek);

	int update(String arrfId, Map<String, Object> updateParas);

	/**
	 * 按航班Id主键查找
	 * 
	 * @param arrfId
	 *            航班Id
	 * @return 航班信息
	 */
	ArrfPek selectByArrfId(String arrfId);

	/**
	 * 按航班Id主键列表查找
	 * 
	 * @param arrfIds
	 *            航班Id列表
	 * @return 航班信息
	 */
	List<ArrfPek> selectByArrfIds(List<String> arrfIds);

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
	List<ArrfPek> selectByFltNoCond(int pageNo, int pageSize, FltNoCond cond);

	/**
	 * 根据航班号条件查询符合条件的航班记录数
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
	 * @param airportCodes
	 *            机场航站编码集合
	 * @param airlineCode
	 *            航空公司编码
	 * @param queryDate
	 *            查询日期
	 * @return 航班信息列表
	 */
	List<ArrfPek> selectByPlaceCond(int pageNo, int pageSize, List<String> airportCodes, String airlineCode,
			Date queryDate);

	/**
	 * 根据起降地条件查询符合条件的航班信息列表总记录数
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
