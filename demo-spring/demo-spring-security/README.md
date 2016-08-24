# v2016.05.17

spring-security 集成的登录sample.

# v2016.05.17.02

自定义UserDetailsService，可以通过注入service跟业务结合.

how to use:

1. run with tomcat...

2. when visit /web1.htm, the browser will be redirected to /login.htm

3. try to login by username and password. Both them are the same, for example: 

		username: user
		password: user
	
	login success and towards /index.htm?value=config.

4. then you can get access to /web1.htm.



