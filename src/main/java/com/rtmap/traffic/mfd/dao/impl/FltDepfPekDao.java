package com.rtmap.traffic.mfd.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IFltDepfPekDao;
import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.entity.DepfPek;

import lqs.frame.util.DateUtils;

@Repository
public class FltDepfPekDao extends DaoHbSupport implements IFltDepfPekDao {
	@Override
	public DepfPek selectByDepfId(String depfId) {
		return (DepfPek) getHibernateTemplate().get(DepfPek.class, depfId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DepfPek> selectByDepfIds(List<String> depfIds) {
		Criteria criteria = createCriteria(DepfPek.class);
		Disjunction dis = Restrictions.disjunction();
		for (String depfId : depfIds) {
			dis.add(Restrictions.eq("depfId", depfId));

		}
		criteria.add(dis);

		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DepfPek> selectByFltNoCond(int pageNo, int pageSize, FltNoCond cond) {
		Criteria criteria = createCriteria(DepfPek.class);
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.add(Restrictions.like("fltNo", cond.getFltNo(), MatchMode.ANYWHERE));
		criteria.add(Restrictions.ge("sdt", cond.getQueryDate()));
		criteria.add(Restrictions.lt("sdt", DateUtils.addDay(cond.getQueryDate(), 1)));
		criteria.addOrder(Order.asc("sdt"));

		List<DepfPek> rst = criteria.list();

		return rst;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DepfPek> selectByPlaceCond(int pageNo, int pageSize, String airportCode, String airlineCode,
			Date queryDate) {
		Criteria criteria = createCriteria(DepfPek.class);
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.add(Restrictions.like("fltNo", airlineCode, MatchMode.START));
		criteria.add(Restrictions.ge("sdt", queryDate));
		criteria.add(Restrictions.lt("sdt", DateUtils.addDay(queryDate, 1)));
		criteria.add(Restrictions.like("route", airportCode, MatchMode.ANYWHERE));
		criteria.addOrder(Order.asc("sdt"));

		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DepfPek> selectByPlaceCond(int pageNo, int pageSize, List<String> airportCodes, String airlineCode,
			Date queryDate) {
		Criteria criteria = createCriteria(DepfPek.class);
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.add(Restrictions.like("fltNo", airlineCode, MatchMode.START));
		criteria.add(Restrictions.ge("sdt", queryDate));
		criteria.add(Restrictions.lt("sdt", DateUtils.addDay(queryDate, 1)));

		Disjunction dis = Restrictions.disjunction();
		for (String airportCode : airportCodes) {
			dis.add(Restrictions.like("route", airportCode, MatchMode.ANYWHERE));
		}
		criteria.add(dis);

		criteria.addOrder(Order.asc("sdt"));
		return criteria.list();
	}

	@Override
	public int selectTotalCountByPlaceCond(List<String> airportCodes, String airlineCode, Date queryDate) {
		Criteria criteria = createCriteria(DepfPek.class);
		criteria.add(Restrictions.like("fltNo", airlineCode, MatchMode.START));
		criteria.add(Restrictions.ge("sdt", queryDate));
		criteria.add(Restrictions.lt("sdt", DateUtils.addDay(queryDate, 1)));

		Disjunction dis = Restrictions.disjunction();
		for (String airportCode : airportCodes) {
			dis.add(Restrictions.like("route", airportCode, MatchMode.ANYWHERE));
		}
		criteria.add(dis);
		
		Long count = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}

	@Override
	public int selectTotalCountByFltNoCond(FltNoCond cond) {
		Criteria criteria = createCriteria(DepfPek.class);
		criteria.add(Restrictions.like("fltNo", cond.getFltNo(), MatchMode.ANYWHERE));
		criteria.add(Restrictions.ge("sdt", cond.getQueryDate()));
		criteria.add(Restrictions.lt("sdt", DateUtils.addDay(cond.getQueryDate(), 1)));
		
		Long count = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}
}
