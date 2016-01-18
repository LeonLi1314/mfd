package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IBscCountryDao;
import com.rtmap.traffic.mfd.domain.entity.Country;

/**
 * 国家基础表数据访问层实现
 * 
 * @author liqingshan 2016-01-06
 *
 */
@Repository
public class BscCountryDaoHb extends DaoHbSupport implements IBscCountryDao {
	// @Resource(name = "sessionFactory")
	// private SessionFactory sessionFactory;
	@Override
	@SuppressWarnings("unchecked")
	public List<Country> selectAll() {
		// 1.template查询
		String hsql = "from Country";

		return (List<Country>) getHibernateTemplate().find(hsql);

		// 2.使用Hql查询
		// String hsql = "from Country";
		// Session session = sessionFactory.getCurrentSession();
		// Query query = session.createQuery(hsql);
		//
		// return query.list();

		// String hsql = "from Country where countryCode like ? ";
		// Session session = sessionFactory.getCurrentSession();
		// Query query = session.createQuery(hsql);
		// query.setString("subname","%C%");
		// return query.list();

		// QBC查询
		// Criteria cr = session.createCriteria(Country.class);
		// cr.add(Expression.like("subname","%C%"));

	}
}
