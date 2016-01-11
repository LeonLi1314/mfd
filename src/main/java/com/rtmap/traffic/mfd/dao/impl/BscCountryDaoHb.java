package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
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
public class BscCountryDaoHb implements IBscCountryDao {
	// @Resource(name = "sessionFactory")
	// private SessionFactory sessionFactory;
	@Resource
	HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<Country> selectAll() {
		String hsql = "from Country";

		return (List<Country>) hibernateTemplate.find(hsql);
		// Session session = sessionFactory.getCurrentSession();
		// Query query = session.createQuery(hsql);
		//
		// return query.list();
	}
}
