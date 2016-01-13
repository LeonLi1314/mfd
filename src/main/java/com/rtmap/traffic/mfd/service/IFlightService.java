package com.rtmap.traffic.mfd.service;

import com.rtmap.traffic.mfd.domain.PageRst;
import com.rtmap.traffic.mfd.domain.cond.ArrdepPlaceCond;
import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.dto.FltDetailDto;
import com.rtmap.traffic.mfd.domain.dto.FltInfoDto;

/**
 * 航班动态服务层接口
 * 
 * @author liqingshan 2016-01-12
 *
 */
public interface IFlightService {

	/**
	 * 根据航班号模糊匹配航班列表
	 * 
	 * @param cond
	 *            查询条件
	 * @return 航班列表
	 */
	PageRst<FltInfoDto> getFlightsByFltNoCond(PageCond<FltNoCond> cond);

	/**
	 * 根据起降地条件获取航班列表
	 * 
	 * @param cond
	 *            查询条件
	 * @return 航班列表
	 */
	PageRst<FltInfoDto> getFlightsByPlaceCond(PageCond<ArrdepPlaceCond> cond);

	/**
	 * 根据航班Id查询条件获取航班详情
	 * 
	 * @param cond
	 *            查询条件
	 * @return 航班详情
	 */
	FltDetailDto getFlightDetailByFltIdCond(FltIdCond cond);

	/**
	 * 获取当前用户关注的航班列表
	 * 
	 * @param openId
	 *            用户公众号唯一标识
	 * @return 航班列表
	 */
	PageRst<FltInfoDto> getFollowedFlights(String openId);
}
