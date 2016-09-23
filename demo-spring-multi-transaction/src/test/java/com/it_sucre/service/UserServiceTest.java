package com.it_sucre.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.jta.JtaTransactionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserServiceTest {

	@Autowired
	JtaTransactionManager txManager;

	@Autowired
	UserService userService;

	@Test
	public void test() {
		userService.test();
	}

}
