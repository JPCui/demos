
使用Spring.JtaTransactionManager + hibernate + atomikos实现分布式事务

Hibernate：当前只能用openSession来获取session，至于getCurrentSession，可能是事务不再被spring管理，所以不能使用，之后尝试解决...
可参考（http://hanqunfeng.iteye.com/blog/2121427）

使用之前，先修改一下数据库配置（端口、用户名、密码），在src/test/java里可以参考两个junit测试


