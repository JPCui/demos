package org.springframework.security.demo;

import org.springframework.stereotype.Service;

/**
 * 自定义安全业务注解
 * @author 奇点之光08老师
 * @see Service
 */
@Service
public @interface SecurityService {

	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any
	 */
	String value() default "";
	
	

}
