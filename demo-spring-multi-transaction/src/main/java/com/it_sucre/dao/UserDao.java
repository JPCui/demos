package com.it_sucre.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.it_sucre.entity.User;

@Repository
public class UserDao {

	@Resource(name = "sessionFactory1")
	SessionFactory sessionFactory1;

	@Resource(name = "sessionFactory2")
	SessionFactory sessionFactory2;

	public void add(User user) {
		Session session = sessionFactory1.openSession();
		// Session session = sessionFactory1.getCurrentSession();
		session.persist(user);
		session.flush();
	}

	public User find(int id) {
		Session session = sessionFactory2.openSession();
		// Session session = sessionFactory2.getCurrentSession();
		User user = (User) session.load(User.class, id);
		// int i = 1/0; // for transaction test
		session.flush();
		return user;
	}

}
