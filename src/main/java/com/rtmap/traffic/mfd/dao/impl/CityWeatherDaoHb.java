package com.rtmap.traffic.mfd.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.ICityWeatherDao;
import com.rtmap.traffic.mfd.domain.entity.CityWeather;

@Repository
public class CityWeatherDaoHb extends DaoHbSupport implements ICityWeatherDao {

	@Override
	@SuppressWarnings("unchecked")
	public CityWeather select(String airportCode, Date statDate) {
		Criteria criteria = createCriteria(CityWeather.class);
		criteria.add(Restrictions.eq("airportCode", airportCode));
		criteria.add(Restrictions.eq("statDate", statDate));
		criteria.addOrder(Order.desc("releaseTime"));
		
		List<CityWeather> list = criteria.list();
		
		if(list != null && list.size() > 0)
			return list.get(0);
		
		return null;
	}

	@Override
	public void BatchInsert(List<CityWeather> list) {
		if (list == null || list.size() == 0)
			return;

		Session session = getHibernateTemplate().getSessionFactory().openSession();
		// Session session = super.getCurrentSession();
		// session.beginTransaction();

		for (int i = 0; i < list.size(); i++) {
			session.save(list.get(i));

			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
		}

		if (list.size() % 20 != 0) {
			session.flush();
			session.clear();
		}

		// session.getTransaction().commit();
		session.close();
	}

}
