package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.rtmap.traffic.mfd.dao.IFltArrfPekDao;
import com.rtmap.traffic.mfd.domain.entity.Airline;

public class FltArrfPekDaoHq implements IFltArrfPekDao {
	@Resource
	HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<Airline> selectAll() {
		String hsql = "from Airline as airline order by airline.fullPinyin";

		return (List<Airline>) hibernateTemplate.find(hsql);
	}
}
