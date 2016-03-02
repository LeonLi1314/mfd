package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IFltArrfBeltPekDao;
import com.rtmap.traffic.mfd.domain.entity.ArrfBeltPek;

@Repository
public class FltArrfBeltPekDaoHb extends DaoHbSupport implements IFltArrfBeltPekDao{

	@Override
	public void batchInsert(List<ArrfBeltPek> list) {
		if(list == null || list.size() == 0)
			return;
		
		Session session = super.getCurrentSession();
		for (ArrfBeltPek arrfBeltPek : list) {
			session.save(arrfBeltPek);
		}
		// 记录数不会超过批次数量限制
        //flush a batch of inserts and release memory:
        session.flush();
        session.clear();
	}

	@Override
	public void batchInsertAfterDelete(List<ArrfBeltPek> list) {
		if(list == null || list.size() == 0)
			return;
		
		String arrfId = list.get(0).getArrfId();
		String hql = "delete from ArrfBeltPek where arrfId = :arrfId";
		Query query = createQuery(hql);
		query.setString("arrfId", arrfId);
		 query.executeUpdate();
		 
		 batchInsert(list);
	}
}
