package org.springframework.security.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.demo.SecurityService;

@SecurityService("accessTestService")
public class AccessTestService {

	public String hasIp(String value) {
		return "hasIpAddress : " + value;
	}

	public String hasUser(String value) {
		return "hasRole : " + SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public String hasAdmin(String value) {
		return "hasRole : " + SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public String hasRoleAdmin(String value) {
		return "hasRole : " + SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@PreAuthorize(value = "hasRole('ROLE_USER')")
	public String hasRoleUser(String value) {
		return "hasRole : " + SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
