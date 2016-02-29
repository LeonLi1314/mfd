package com.rtmap.traffic.mfd.dao.impl;

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

import com.alibaba.fastjson.JSON;
import com.rtmap.traffic.mfd.dao.IFltArrfPekDao;
import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.entity.ArrfPek;

import rtmap.frame.util.DateUtils;

@Repository
public class FltArrfPekDaoHb extends DaoHbSupport implements IFltArrfPekDao {
	@Override
	public ArrfPek selectByArrfId(String arrfId) {
		return (ArrfPek) getHibernateTemplate().get(ArrfPek.class, arrfId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ArrfPek> selectByArrfIds(List<String> arrfIds) {
		Criteria criteria = createCriteria(ArrfPek.class);
		Disjunction dis = Restrictions.disjunction();
		for (String arrfId : arrfIds) {
			dis.add(Restrictions.eq("arrfId", arrfId));

		}
		criteria.add(dis);

		return criteria.list();
	}

	@Override
	public int selectTotalCountByFltNoCond(FltNoCond cond) {
		Criteria criteria = createCriteria(ArrfPek.class);
		criteria.add(Restrictions.like("fltNo", cond.getFltNo(), MatchMode.ANYWHERE));
		criteria.add(Restrictions.ge("sdt", cond.getQueryDate()));
		criteria.add(Restrictions.lt("sdt", DateUtils.addDay(cond.getQueryDate(), 1)));
		Long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ArrfPek> selectByFltNoCond(int pageNo, int pageSize, FltNoCond cond) {
		Criteria criteria = createCriteria(ArrfPek.class);
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.add(Restrictions.like("fltNo", cond.getFltNo(), MatchMode.ANYWHERE));
		criteria.add(Restrictions.ge("sdt", cond.getQueryDate()));
		criteria.add(Restrictions.lt("sdt", DateUtils.addDay(cond.getQueryDate(), 1)));
		criteria.addOrder(Order.asc("sdt"));

		List<ArrfPek> rst = criteria.list();

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
	public List<ArrfPek> selectByPlaceCond(int pageNo, int pageSize, List<String> airportCodes, String airlineCode,
			Date queryDate) {
		Criteria criteria = buildCriteriaByPlaceCond(airportCodes, airlineCode, queryDate);
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.asc("sdt"));

		return criteria.list();
	}

	/**
	 * 根据起降地查询条件构建标准查询条件
	 * 
	 * @param airportCodes
	 *            航站代码集合
	 * @param airlineCode
	 *            航空公司代码
	 * @param queryDate
	 *            查询日期
	 * @return 标准查询条件
	 */
	private Criteria buildCriteriaByPlaceCond(List<String> airportCodes, String airlineCode, Date queryDate) {
		Criteria criteria = createCriteria(ArrfPek.class);
		criteria.add(Restrictions.like("fltNo", airlineCode, MatchMode.START));
		criteria.add(Restrictions.ge("sdt", queryDate));
		criteria.add(Restrictions.lt("sdt", DateUtils.addDay(queryDate, 1)));
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
		 * 当天 过滤掉国内实际到港时间已经超过1小时的或国际实际到港时间超过1.5小时的，并且截止到当天凌晨
		 */
		criteria.add(Restrictions.or(Restrictions.isNull("actTime"),
				Restrictions.ge("actTime", DateUtils.addHour(queryDate, -1)),
				Restrictions.and(Restrictions.le("actTime", DateUtils.addMinute(queryDate, -100)),
						Restrictions.eq("domint", "I"))));
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
	public int insert(ArrfPek arrfPek) {
		System.out.println(JSON.toJSONString(arrfPek));
		return (int) super.insert(arrfPek);
	}

	@Override
	public int update(String arrfId, Map<String, Object> updateParas) {
		if (updateParas == null || updateParas.size() == 0)
			return 0;

		StringBuilder sb = new StringBuilder(128);
		sb.append("update ArrfPek set updateTime = :updateTime");
		for (String key : updateParas.keySet()) {
			sb.append(String.format(",%s = :%s", key, key));
		}
		sb.append(" where arrfId = :arrfId");

		Query query = createQuery(sb.toString());
		query.setTimestamp("updateTime", new Date());
		
		for (String key : updateParas.keySet()) {
			query.setParameter(key, updateParas.get(key));
		}
		query.setString("arrfId", arrfId);

		int i = query.executeUpdate();
		return i;
	}
}
