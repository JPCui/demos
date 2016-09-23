package com.it_sucre.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
public class BaseDaoTest {

	@Resource(name = "sessionFactory1")
	SessionFactory sessionFactory1;

	@Resource(name = "sessionFactory2")
	SessionFactory sessionFactory2;

	@BeforeClass
	public static void before() {
		System.out.println("before");
	}

	@AfterClass
	public static void after() {
		System.out.println("after");
	}

	@Test
	@Transactional
	public void test() {
		System.out.println(sessionFactory1);
		Session session = sessionFactory1.openSession();
		Query query = session.createQuery("from User");
		session.flush();
		Object list = query.list();
		session.close();

		System.out.println(list);

	}

}
