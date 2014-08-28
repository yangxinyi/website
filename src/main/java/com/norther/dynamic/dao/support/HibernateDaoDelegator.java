package com.norther.dynamic.dao.support;

import java.io.Serializable;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.norther.dynamic.dao.HibernateDao;


/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ÉÏÎç10:12:18
 */
@SuppressWarnings("unchecked")
public class HibernateDaoDelegator extends HibernateDaoSupport implements HibernateDao {

	public Query getNamedQuery(String name) {
		return getSession().getNamedQuery(name);
	}

	public Query createQuery(String hql) {
		return getSession().createQuery(hql);
	}

	public Query createSqlQuery(String sql) {
		return getSession().createSQLQuery(sql);
	}

	public void delete(Object entity, LockMode lockMode) {
		getHibernateTemplate().delete(entity, lockMode);

	}

	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);

	}

	public Object get(Class entityClass, Serializable id, LockMode lockMode) {
		return getHibernateTemplate().get(entityClass, id, lockMode);
	}

	public Object get(Class entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	public Object get(String entityName, Serializable id, LockMode lockMode) {
		return getHibernateTemplate().get(entityName, id, lockMode);
	}

	public Object get(String entityName, Serializable id) {
		return getHibernateTemplate().get(entityName, id);
	}

	public Object load(Class entityClass, Serializable id, LockMode lockMode) {
		return getHibernateTemplate().load(entityClass, id, lockMode);
	}

	public Object load(Class entityClass, Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}

	public Object load(String entityName, Serializable id, LockMode lockMode) {
		return getHibernateTemplate().load(entityName, id, lockMode);
	}

	public Object load(String entityName, Serializable id) {
		return getHibernateTemplate().load(entityName, id);
	}

	public Serializable save(Object entity) {
		return getHibernateTemplate().save(entity);
	}

	public Serializable save(String entityName, Object entity) {
		return getHibernateTemplate().save(entityName, entity);
	}

	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);

	}

	public void saveOrUpdate(String entityName, Object entity) {
		getHibernateTemplate().saveOrUpdate(entityName, entity);
	}

	public void update(Object entity) {
		getHibernateTemplate().update(entity);

	}

	public void update(String entityName, Object entity) {
		getHibernateTemplate().update(entityName, entity);

	}

	public void lock(Object entity, LockMode lockMode) {
		getHibernateTemplate().lock(entity, lockMode);

	}

	public void lock(String entityName, Object entity, LockMode lockMode) {
		getHibernateTemplate().lock(entityName, entity, lockMode);
	}

}
