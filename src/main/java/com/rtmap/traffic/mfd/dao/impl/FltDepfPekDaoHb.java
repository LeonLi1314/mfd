package com.rtmap.traffic.mfd.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IFltDepfPekDao;
import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.entity.DepfPek;

import rtmap.frame.util.DateUtils;

@Repository
public class FltDepfPekDaoHb extends DaoHbSupport implements IFltDepfPekDao {
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
	public int selectTotalCountByFltNoCond(FltNoCond cond) {
		Criteria criteria = createCriteria(DepfPek.class);
		criteria.add(Restrictions.like("fltNo", cond.getFltNo(), MatchMode.ANYWHERE));
		criteria.add(Restrictions.ge("sdt", cond.getQueryDate()));
		criteria.add(Restrictions.lt("sdt", DateUtils.addDay(cond.getQueryDate(), 1)));

		Long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
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
	public int selectTotalCountByPlaceCond(List<String> airportCodes, String airlineCode, Date queryDate) {
		Criteria criteria = buildCriteriaByPlaceCond(airportCodes, airlineCode, queryDate);

		Long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DepfPek> selectByPlaceCond(int pageNo, int pageSize, List<String> airportCodes, String airlineCode,
			Date queryDate) {
		Criteria criteria = buildCriteriaByPlaceCond(airportCodes, airlineCode, queryDate);
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.asc("sdt"));

		return criteria.list();
	}

	private Criteria buildCriteriaByPlaceCond(List<String> airportCodes, String airlineCode, Date queryDate) {
		Criteria criteria = createCriteria(DepfPek.class);
		criteria.add(Restrictions.like("fltNo", airlineCode, MatchMode.START));
		criteria.add(Restrictions.ge("sdt", queryDate));
		criteria.add(Restrictions.le("sdt", DateUtils.addDay(queryDate, 1)));
		List<String> taskNatures = new ArrayList<>();
		taskNatures.add("W");
		taskNatures.add("L");
		taskNatures.add("C");
		taskNatures.add("J");
		taskNatures.add("Q");
		criteria.add(Restrictions.in("taskNature", taskNatures));

		/*
		 * 昨天 查询22点到24点的
		 */
		criteria.add(Restrictions.ge("sdt", DateUtils.addHour(queryDate, 22)));
		/*
		 * 当天 过滤掉实际起飞时间超过10分钟的 或者
		 * 实际起飞时间为空且计划起飞时间3小时内的(需剔除取消时间不为空时，取消时间晚于计划时间，按取消时间代替)
		 */
		criteria.add(Restrictions.or(
				Restrictions.and(Restrictions.isNotNull("actTime"),
						Restrictions.ge("actTime", DateUtils.addMinute(queryDate, -10))),
				Restrictions.and(Restrictions.isNull("actTime"),
						Restrictions.le("sdt", DateUtils.addHour(queryDate, 3)))));
		/*
		 * 明天 全部符合条件的
		 */

		Disjunction dis = Restrictions.disjunction();
		for (String airportCode : airportCodes) {
			dis.add(Restrictions.like("route", airportCode, MatchMode.ANYWHERE));
		}
		criteria.add(dis);

		return criteria;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DepfPek> selectByGateNos(List<String> gateNos, int limit) {
		Date currDate = new Date();
		Criteria criteria = createCriteria(DepfPek.class);
		criteria.add(Restrictions.in("gatDisp", gateNos));
		criteria.add(Restrictions.isNull("actTime"));
		criteria.add(Restrictions.le("sdt", DateUtils.addHour(currDate, 3)));

		// Disjunction dis = Restrictions.disjunction();
		// for (String gatNo : gateNos) {
		// // 登机口只对应一个
		// dis.add(Restrictions.eq("gatDisp", gatNo).ignoreCase());
		// }
		// criteria.add(dis);
		criteria.addOrder(Order.asc("sdt"));
		criteria.addOrder(Order.asc("fltType"));
		criteria.addOrder(Order.asc("fltNo"));
		if (limit > 0) {
			criteria.setMaxResults(limit);
		}

		return criteria.list();
	}

	@Override
	public String insert(DepfPek depfPek) {
		 Serializable s = super.insert(depfPek);
		 System.out.println(s);
		 return s.toString();
	}

	@Override
	public int update(String depfId, Map<String, Object> updateParas) {
		if (updateParas == null || updateParas.size() == 0)
			return 0;

		StringBuilder sb = new StringBuilder(128);
		sb.append("update DepfPek set updateTime = :updateTime");
		for (String key : updateParas.keySet()) {
			sb.append(String.format(",%s = :%s", key, key));
		}
		sb.append(" where depfId = :depfId");

		Query query = createQuery(sb.toString());
		query.setTimestamp("updateTime", new Date());

		for (String key : updateParas.keySet()) {
			Object o = updateParas.get(key);
			if (String.class.equals(o.getClass())) {
				query.setString(key, String.valueOf(o));
			} else if (Date.class.equals(o.getClass())) {
				query.setTimestamp(key, (Date) o);
			} else if (Integer.class.equals(o.getClass())) {
				query.setInteger(key, (Integer) o);
			} else {
				query.setParameter(key, o);
			}
		}
		query.setString("depfId", depfId);

		int i = query.executeUpdate();
		return i;
	}
}
