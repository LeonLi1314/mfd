package com.rtmap.traffic.mfd.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.ISubscribeContractDao;
import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.SubscriberCond;
import com.rtmap.traffic.mfd.domain.entity.SubscribeContract;

/**
 * 订阅契约数据访问层实现类
 * 
 * @author liqingshan 2016-01-15
 *
 */
@Repository
public class SubscribeContractDaoHb extends DaoHbSupport implements ISubscribeContractDao {
	@Override
	public int insert(SubscribeContract entity) {
		Serializable id = getCurrentSession().save(entity);
		return (int)id;
	}

	@Override
	public int delete(int contractId) {
		Query query = createQuery("update SubscribeContract set deleteFlag = 'Y',updateTime = :updateTime where contractId = :contractId");
		query.setTimestamp("updateTime", new Date());
		query.setInteger("contractId", contractId);
		int i = query.executeUpdate();
		return i;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubscribeContract> selectEffectBySubscriberCond(SubscriberCond cond) {
		Criteria criteria = createCriteria(SubscribeContract.class);
		criteria.add(Restrictions.eq("currentAirport", cond.getCurrentAirport()));
		criteria.add(Restrictions.eq("sourceCode", cond.getSourceCode()));
		criteria.add(Restrictions.eq("subscribeId", cond.getSubscribeId()));
		criteria.add(Restrictions.eq("subscriberId", cond.getSubscriberId()));
		criteria.add(Restrictions.eq("subscribeModule", cond.getSubscribeModule()));
		criteria.add(Restrictions.eq("subscribeEvent", cond.getSubscribeEvent()));
		criteria.add(Restrictions.eq("deleteFlag", "N"));
		criteria.add(Restrictions.ge("invalidTime", new Date()));
		criteria.addOrder(Order.desc("createTime"));
		
		return criteria.list();
	}

	@Override
	public SubscribeContract selectContractByFltIdCond(FltIdCond cond) {
		Criteria criteria = createCriteria(SubscribeContract.class);
		criteria.add(Restrictions.eq("currentAirport", cond.getCurrentAirport()));
		criteria.add(Restrictions.eq("sourceCode", cond.getSourceCode()));
		criteria.add(Restrictions.eq("subscribeId", cond.getSubscribeId()));
		criteria.add(Restrictions.eq("subscriberId", cond.getSubscriberId()));
		criteria.add(Restrictions.eq("subscribeModule", cond.getSubscribeModule()));
		criteria.add(Restrictions.eq("subscribeEvent", cond.getSubscribeEvent()));
		criteria.add(Restrictions.eq("subscribeKeywords", cond.getFltId() + "|" + cond.getArrdep().toString()));
		criteria.add(Restrictions.eq("deleteFlag", "N"));
		criteria.add(Restrictions.ge("invalidTime", new Date()));

		return (SubscribeContract)criteria.uniqueResult();
	}
}
