package com.rtmap.traffic.mfd.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IMsgWechatCenterDao;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.entity.MsgWechatCenter;

@Repository
public class MsgWechatCenterDaoHb extends DaoHbSupport implements IMsgWechatCenterDao {
	@Override
	public void batchInsert(List<MsgWechatCenter> list) {
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

	@Override
	public int selectUnreadCount(String openId) {
		Criteria criteria = createCriteria(MsgWechatCenter.class);
		criteria.add(Restrictions.eq("openId", openId));
		criteria.add(Restrictions.eq("deleteFlag", "N"));
		criteria.add(Restrictions.eq("readFlag", "N"));

		Long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}

	@Override
	public int selectMessagesTotalCount(String openId, boolean isAll) {
		Criteria criteria = createCriteria(MsgWechatCenter.class);
		criteria.add(Restrictions.eq("openId", openId));
		criteria.add(Restrictions.eq("deleteFlag", "N"));
		if (!isAll) {
			criteria.add(Restrictions.eq("readFlag", "N"));
		}

		Long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MsgWechatCenter> selectPageMessages(PageCond<String> cond, boolean isAll) {
		Criteria criteria = createCriteria(MsgWechatCenter.class);
		criteria.setFirstResult((cond.getPageNo() - 1) * cond.getPageSize());
		criteria.setMaxResults(cond.getPageSize());
		criteria.add(Restrictions.eq("openId", cond.getCond()));
		criteria.add(Restrictions.eq("deleteFlag", "N"));
		criteria.addOrder(Order.asc("createTime"));
		if (!isAll) {
			criteria.add(Restrictions.eq("readFlag", "N"));
		}

		List<MsgWechatCenter> rst = criteria.list();

		return rst;
	}

	@Override
	public void updateReadFlag(int msgId) {
		Query query = createQuery(
				"update MsgWechatCenter set readFlag = 'Y',updateTime = :updateTime where msgId = :msgId");
		query.setTimestamp("updateTime", new Date());
		query.setInteger("msgId", msgId);

		query.executeUpdate();
	}

	@Override
	public void updateDeleteFlag(int msgId) {
		Query query = createQuery(
				"update MsgWechatCenter set deleteFlag = 'Y',updateTime = :updateTime where msgId = :msgId");
		query.setTimestamp("updateTime", new Date());
		query.setInteger("msgId", msgId);

		query.executeUpdate();
	}
}
