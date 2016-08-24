# spring-security-samples-xml-contacts

Contacts is a sample in Spring-Security lib.

1. 修改applicationContext-common-business.xml中数据源的配置

2. `sample.contact.DataSourcePopulator`：

修改sql语法

2. spring-security-acl.jar中

`org.springframework.security.acls.jdbc.JdbcMutableAclService`
	
修改下面属性：

	//	private String classIdentityQuery = "call identity()";
	//	private String sidIdentityQuery = "call identity()";
		private String classIdentityQuery = "SELECT LAST_INSERT_ID()";
		private String sidIdentityQuery = "SELECT LAST_INSERT_ID()";


------


**需要注意设置MySQL的变量，设置表名大小写不敏感**