package com.map.service;
/*
 * @(#)BaseTests.java
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * description
 * 
 * @author yangxinyi
 * @version 1.0,2014-08-27
 */
@ContextConfiguration({ "classpath:applicationContext.xml" })
public abstract class BaseTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	protected Services services;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	protected void flush() throws BeansException {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.flush();
    }
	
}
