package org.springframework.security.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

public class UserService implements UserDetailsService {

	private static Logger logger = Logger.getLogger(UserService.class);

	/**
	 * 在这里查询用户的真实信息，之后会与客户端输入的username，password进行判断
	 * <p>
	 * 这里设置用户名、密码相同，developer可以自行修改，从数据库获取
	 * <p>
	 * 至于如何设置传递多个参数，可以参考：{@link http://salever.iteye.com/blog/1686425}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername - " + username);
		if (StringUtils.isEmpty(username)) {
			return null;
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		String password = username;
		return new User(username, password, authorities);
	}

}
