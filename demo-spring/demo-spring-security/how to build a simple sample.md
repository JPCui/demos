# demo-spring-security

# web.xml

		<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
		</filter>
		
		<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
		</filter-mapping>

# spring-security.xml

## A Minimal <http> Configuration

enable web security.

	<http>
	<intercept-url pattern="/**" access="hasRole('USER')" />
	<form-login />
	<logout />
	</http>

> 你也可以添加一个http method属性，来限制请求方式：
> 
>  *You can also add a method attribute to limit the match to a particular HTTP method (GET, POST, PUT etc.).*

 你可以直接配置下面的namespace来定义一些测试用户：

	<authentication-manager>
	<authentication-provider>
		<user-service>
		<user name="jimi" password="jimispassword" authorities="ROLE_USER, ROLE_ADMIN" />
		<user name="bob" password="bobspassword" authorities="ROLE_USER" />
		</user-service>
	</authentication-provider>
	</authentication-manager>

通过下面配置，绕过security filter chain：

*It is also possible to have all requests matching a particular pattern bypass the security filter chain completely, by defining a separate http element for the pattern like this:*


	<http pattern="/css/**" security="none"/>
	<http pattern="/login.jsp*" security="none"/>


[simple demo getting started](http://docs.spring.io/spring-security/site/docs/4.1.0.RELEASE/reference/htmlsingle/#ns-getting-started)



