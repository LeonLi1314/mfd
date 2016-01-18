package com.rtmap.traffic.mfd.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * HibernateDao实现的基类
 * 
 * @author liqingshan
 *
 */
abstract class DaoHbSupport {
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取hibernate模版
	 * 
	 * @return hibernate模版
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * 获取jdbc模版
	 * 
	 * @return jdbc模版
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * 获取当前的会话
	 * 
	 * @return 获取会话
	 */
	public Session getCurrentSession() {
		// getCurrentSession创建的session会绑定到当前线程，openSession不会；在事务回滚或事务提交后自动关闭，openSession必须手动关闭（调用session的close()方法）
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	/**
	 * 创建当前的标准查询条件
	 * 
	 * @param classType
	 * @return 标准查询条件
	 */
	public Criteria createCriteria(Class<?> classType) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(classType);
	}

	/**
	 * 创建Hql查询
	 * 
	 * @param hql
	 *            hql查询语句
	 * @return Hql语句查询对象
	 */
	public Query createQuery(String hql) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
	}

	/**
	 * 创建Sql查询
	 * 
	 * @param sql
	 *            sql查询语句
	 * @return Sql语句查询对象
	 */
	public SQLQuery createSQLQuery(String sql) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
	}

	/**
	 * 新增实体对象
	 * 
	 * @param entity
	 *            待插入实体对象
	 * @return 实体对象
	 */
	public Serializable insert(Object entity) {
		return hibernateTemplate.save(entity);
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            待更新实体对象
	 */
	public void update(Object entity) {
		hibernateTemplate.update(entity);
	}

	/**
	 * 新增或更新实体对象
	 * 
	 * @param entity
	 *            待保存或更新实体对象
	 */
	public void insertOrUpdate(Object entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	/**
	 * 删除实体对象（物理删除）
	 * 
	 * @param entity
	 *            待删除实体对象
	 */
	public void delete(Object entity) {
		hibernateTemplate.delete(entity);
	}

	/**
	 * Flush all pending saves, updates and deletes to the database.
	 */
	public void flash() {
		hibernateTemplate.flush();
	}

	/**
	 * 根据实体对象主键查找实体对象
	 * 
	 * @param <T>
	 *            实体类型
	 * @param entityClass
	 *            实体对象class
	 * @param id
	 *            提示对象主键
	 * @return 实体对象
	 */
	public <E> E selectById(Class<E> entityClass, Serializable id) {
		return hibernateTemplate.get(entityClass, id);
	}

	/**
	 * 查询实体对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @return 实体对象列表
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> select(String hql) {
		return (List<E>)hibernateTemplate.find(hql);
	}

	/**
	 * 查询实体对象列表
	 * 
	 * @param preparedHql
	 *            HQL语句
	 * @param params
	 *            参数列表
	 * @return 实体对象列表
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> select(String preparedHql, List<?> params) {
		if (params == null) {
			return select(preparedHql);
		}

		return (List<E>)hibernateTemplate.find(preparedHql, params.toArray());
	}

	/**
	 * 查询实体对象列表
	 * 
	 * @param preparedHql
	 *            HQL语句
	 * @param params
	 *            参数数组
	 * @return 实体对象列表
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> select(String preparedHql, Object[] params) {
		return (List<E>)hibernateTemplate.find(preparedHql, params);
	}

	/**
	 * 查询所有
	 * 
	 * @param entityClass
	 *            实体类类型
	 * @return 实体对象列表
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> selectAll(Class<E> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		return criteria.list();
	}

	/**
	 * 使用SQL直接更新数据
	 * 
	 * @param sql
	 *            更新操作的SQL语句
	 */
	public int updateBySql(String sql) {
		return jdbcTemplate.update(sql);
	}

	/**
	 * 使用SQL进行更新操作
	 * 
	 * @param sql
	 *            更新操作的SQL语句
	 * @param params
	 *            参数列表
	 * @return 更细记录数
	 */
	public int updateBySql(String sql, List<?> params) {
		if (params == null || params.size() == 0) {
			return updateBySql(sql);
		}

		return jdbcTemplate.update(sql, params.toArray());
	}
}
