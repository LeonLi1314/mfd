package com.rtmap.traffic.mfd.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.mfd.dao.ISubscribeContractDao;
import com.rtmap.traffic.mfd.dao.ISubscribeModeDao;
import com.rtmap.traffic.mfd.domain.OpRst;
import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.SubscriberCond;
import com.rtmap.traffic.mfd.domain.dto.FollowFltDto;
import com.rtmap.traffic.mfd.domain.entity.SubscribeContract;
import com.rtmap.traffic.mfd.service.ISubscribeService;

import rtmap.frame.util.DateUtils;

/**
 * 订阅关注模块服务层
 * 
 * @author liqingshan 2016-01-15
 *
 */
@Service
public class SubscribeServiceImpl implements ISubscribeService {
	@Resource
	ISubscribeModeDao subscribeModeDao;
	@Resource
	ISubscribeContractDao subscribeContractDao;

	@Override
	public OpRst followFlight(FollowFltDto dto) {
		SubscribeContract entity = new SubscribeContract();
		Date now = new Date();
		entity.setCreateTime(now);
		entity.setCurrentAirport(dto.getCurrentAirport());
		entity.setDeleteFlag("N");
		entity.setInvalidTime(DateUtils.addDay(now, 3));
		entity.setSourceCode(dto.getSourceCode());
		entity.setSubscribeEvent(dto.getSubscribeEvent());
		entity.setSubscribeId(dto.getSubscribeId());
		entity.setSubscribeKeywords(dto.getFltId() + "|" + dto.getArrdep());
		entity.setSubscribeModule(dto.getSubscribeModule());
		entity.setSubscriberId(dto.getSubscriberId());
		entity.getSubscribeEvent();

		FltIdCond cond = new FltIdCond();
		cond.setArrdep(dto.getArrdep());
		cond.setCurrentAirport(dto.getCurrentAirport());
		cond.setFltId(dto.getFltId());
		cond.setSourceCode(dto.getSourceCode());
		cond.setSubscribeEvent(dto.getSubscribeEvent());
		cond.setSubscribeId(dto.getSubscribeId());
		cond.setSubscribeModule(dto.getSubscribeModule());
		cond.setSubscriberId(dto.getSubscriberId());
		SubscribeContract contract = subscribeContractDao.selectContractByFltIdCond(cond);

		OpRst rst = new OpRst();
		if(contract != null){
			rst.setSuccess(false);
			rst.setMsg(contract.getContractId().toString());
			return rst;
		}
		
		int id = subscribeContractDao.insert(entity);
		if (id > 1) {
			rst.setSuccess(true);
			rst.setMsg(Integer.toString(id));
		}

		return rst;
	}

	@Override
	public OpRst unfollowFlight(FollowFltDto dto) {
		int i = subscribeContractDao.delete(dto.getContractId());

		OpRst rst = new OpRst();

		if (i == 1) {
			rst.setSuccess(true);
		}

		return rst;
	}
	
	@Override
	public List<SubscribeContract> getEffectContractsBySubscriberCond(SubscriberCond cond){
		return subscribeContractDao.selectEffectBySubscriberCond(cond);
	}

	@Override
	public SubscribeContract getContractByFltIdCond(FltIdCond cond) {
		return subscribeContractDao.selectContractByFltIdCond(cond);
	}
}
