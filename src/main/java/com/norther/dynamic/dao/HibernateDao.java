package com.norther.dynamic.dao;

import java.io.Serializable;

import org.hibernate.LockMode;
import org.hibernate.Query;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-11 ÏÂÎç08:47:38
 */
@SuppressWarnings("unchecked")
public interface HibernateDao {

	Query getNamedQuery(String name);

	Query createQuery(String hql);

	Query createSqlQuery(String sql);

	void delete(Object entity);

	void delete(Object entity, LockMode lockMode);

	Object get(Class entityClass, Serializable id);

	Object get(Class entityClass, Serializable id, LockMode lockMode);

	Object get(String entityName, Serializable id);

	Object get(String entityName, Serializable id, LockMode lockMode);

	Object load(Class entityClass, Serializable id);

	Object load(String entityName, Serializable id);

	Object load(Class entityClass, Serializable id, LockMode lockMode);

	Object load(String entityName, Serializable id, LockMode lockMode);

	Serializable save(Object entity);

	Serializable save(String entityName, Object entity);

	void saveOrUpdate(Object entity);

	void saveOrUpdate(String entityName, Object entity);

	void update(Object entity);

	void update(String entityName, Object entity);

	void lock(Object entity, LockMode lockMode);

	void lock(String entityName, Object entity, LockMode lockMode);

}
