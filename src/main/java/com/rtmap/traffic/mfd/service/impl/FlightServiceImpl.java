package com.rtmap.traffic.mfd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.mfd.dao.IFltArrfPekDao;
import com.rtmap.traffic.mfd.dao.IFltDepfPekDao;
import com.rtmap.traffic.mfd.domain.ArrdepFlag;
import com.rtmap.traffic.mfd.domain.PageRst;
import com.rtmap.traffic.mfd.domain.cond.ArrdepPlaceCond;
import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.dto.FltDetailDto;
import com.rtmap.traffic.mfd.domain.dto.FltInfoDto;
import com.rtmap.traffic.mfd.service.IFlightService;

/**
 * 航班动态服务层实现
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Service
public class FlightServiceImpl implements IFlightService {
	@Resource
	IFltArrfPekDao fltArrPekDao;
	@Resource
	IFltDepfPekDao fltDepfPekDao;

	@Override
	public PageRst<FltInfoDto> getFlightsByFltNoCond(PageCond<FltNoCond> cond) {
		List<FltInfoDto> list = null;

		if (ArrdepFlag.A == cond.getCond().getArrdep()) {
			list = fltArrPekDao.selectByFltNoCond(cond.getIndex(), cond.getSize(), cond.getCond());
			
			
			
		} else {
			list = fltDepfPekDao.selectByFltNoCond(cond.getIndex(), cond.getSize(), cond.getCond());
			
			
			
		}
	}

	@Override
	public PageRst<FltInfoDto> getFlightsByPlaceCond(PageCond<ArrdepPlaceCond> cond) {
		List<FltInfoDto> list = null;

		if (ArrdepFlag.A == cond.getCond().getArrdep()) {
			list = fltArrPekDao.selectByPlaceCond(cond.getIndex(), cond.getSize(), cond.getCond());
			
			
			
		} else {
			list = fltDepfPekDao.selectByPlaceCond(cond.getIndex(), cond.getSize(), cond.getCond());
			
			
			
		}
		return null;
	}

	@Override
	public FltDetailDto getFlightDetailByFltIdCond(FltIdCond cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageRst<FltInfoDto> getFollowedFlights(String openId) {
		// TODO Auto-generated method stub
		return null;
	}
}
