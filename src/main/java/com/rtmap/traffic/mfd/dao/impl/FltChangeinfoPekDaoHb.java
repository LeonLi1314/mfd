package com.rtmap.traffic.mfd.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IFltChangeinfoPekDao;
import com.rtmap.traffic.mfd.domain.entity.FltChangeinfoPek;

/**
 * 首都机场航班变更信息数据层实现
 * 
 * @author liqingshan
 *
 */
@Repository
public class FltChangeinfoPekDaoHb extends DaoHbSupport implements IFltChangeinfoPekDao {
	@Override
	public int insert(FltChangeinfoPek record) {
		return (int) super.insert(record);
	}

	@Override
	public int updateExecuted(Integer recId) {
		Query query = createQuery(
				"update FltChangeinfoPek set executeFlag = 'Y',updateTime = :updateTime where contractId = :contractId");
		query.setTimestamp("updateTime", new Date());
		query.setInteger("recId", recId);

		return query.executeUpdate();
	}

	@Override
	public FltChangeinfoPek selectByRecId(Integer recId) {
		return (FltChangeinfoPek) getHibernateTemplate().get(FltChangeinfoPek.class, recId);
	}

	@Override
	@SuppressWarnings("unchecked")
	// @Transactional(propagation = Propagation.NEVER)
	public List<FltChangeinfoPek> selectNotExecuted(int limit) {
		Criteria criteria = createCriteria(FltChangeinfoPek.class);
		criteria.add(Restrictions.eq("executeFlag", "N"));

		if (limit > 0) {
			criteria.setMaxResults(limit);
			criteria.addOrder(Order.asc("create_time"));
		}

		return criteria.list();
	}
}
