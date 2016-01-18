package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.ISubscribeModeDao;
import com.rtmap.traffic.mfd.domain.entity.SubscribeMode;

/**
 * 订阅模式数据访问层实现类
 * 
 * @author liqingshan 2016-01-15
 *
 */
@Repository
public class SubscribeModeDaoHb extends DaoHbSupport implements ISubscribeModeDao {
	@Override
	@SuppressWarnings("unchecked")
	public List<SubscribeMode> selectAll() {
		Criteria criteria = createCriteria(SubscribeMode.class);
		return criteria.list();
	}
}
