package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IFltDepfGatePekDao;
import com.rtmap.traffic.mfd.domain.entity.DepfGatePek;

@Repository
public class FltDepfGatePekDaoHb  extends DaoHbSupport implements IFltDepfGatePekDao {

	@Override
	public void batchInsert(List<DepfGatePek> list) {
		if(list == null || list.size() == 0)
			return;
		
		Session session = super.getCurrentSession();
		for (DepfGatePek gate : list) {
			session.save(gate);
		}
		// 记录数不会超过批次数量限制
        //flush a batch of inserts and release memory:
        session.flush();
        session.clear();
	}

	@Override
	public void batchInsertAfterDelete(List<DepfGatePek> list) {
		if(list == null || list.size() == 0)
			return;
		
		String depfId = list.get(0).getDepfId();
		String hql = "delete from DepfGatePek where depfId = :depfId";
		Query query = createQuery(hql);
		query.setString("depfId", depfId);
		 query.executeUpdate();
		 
		 batchInsert(list);
	}
}
