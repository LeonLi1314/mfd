package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IFltDepfCounterPekDao;
import com.rtmap.traffic.mfd.domain.entity.DepfCounterPek;

@Repository
public class FltDepfCounterPekDaoHb extends DaoHbSupport implements IFltDepfCounterPekDao {

	@Override
	public void batchInsert(List<DepfCounterPek> list) {
		if(list == null || list.size() == 0)
			return;
		
		Session session = super.getCurrentSession();
		for (DepfCounterPek counter : list) {
			session.save(counter);
		}
		// 记录数不会超过批次数量限制
        //flush a batch of inserts and release memory:
        session.flush();
        session.clear();
	}

	@Override
	public void batchInsertAfterDelete(List<DepfCounterPek> list) {
		if(list == null || list.size() == 0)
			return;
		
		String depfId = list.get(0).getDepfId();
		String hql = "delete from DepfCounterPek where depfId = :depfId";
		Query query = createQuery(hql);
		query.setString("depfId", depfId);
		 query.executeUpdate();
		 
		 batchInsert(list);
	}
}
