package com.it_sucre.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.it_sucre.JacksonUtil;
import com.it_sucre.dao.UserDao;
import com.it_sucre.entity.User;

@Service
public class UserService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	UserDao userDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void test() {
		User user = new User();
		user.setName("aaaa");
		userDao.add(user);
		if (logger.isInfoEnabled()) {
			logger.error(JacksonUtil.toJson(user));
		}

		userDao.find(1);
	}

}
