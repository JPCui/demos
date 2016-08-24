package org.springframework.security.demo.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.demo.service.AccessTestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {

	private static Logger logger = Logger.getLogger(DemoController.class);

	@Autowired
	AccessTestService accessTestService;

	@RequestMapping("/log")
	public ModelAndView log() {
		ModelAndView model = new ModelAndView("/index");
		model.addObject("value", "LOG Page");
		return model;
	}

	@RequestMapping("/hasRole")
	public ModelAndView hasRole(String value) {
		ModelAndView model = new ModelAndView("/index");
		if ("role_user".equalsIgnoreCase(value)) {
			model.addObject("value", accessTestService.hasRoleUser(value));
		} else if ("role_admin".equalsIgnoreCase(value)) {
			model.addObject("value", accessTestService.hasRoleAdmin(value));
		} else if ("user".equalsIgnoreCase(value)) {
			model.addObject("value", accessTestService.hasUser(value));
		} else if ("admin".equalsIgnoreCase(value)) {
			model.addObject("value", accessTestService.hasAdmin(value));
		}
		return model;
	}

	@RequestMapping("/hasIp")
	public ModelAndView hasIp(String value) {
		ModelAndView model = new ModelAndView("/index");
		model.addObject("value", accessTestService.hasIp(value));
		return model;
	}

	@RequestMapping("/forward")
	public ModelAndView forward(String value) {
		ModelAndView model = new ModelAndView("/" + value);
		model.addObject("value", value);
		return model;
	}

	@RequestMapping("/login")
	public ModelAndView login(String value) {
		logger.info(value);
		ModelAndView model = new ModelAndView("/login");
		model.addObject("value", value);
		return model;
	}

	@RequestMapping("/login-processing-url")
	public ModelAndView login_processing_url(String value) {
		logger.info(value);
		ModelAndView model = new ModelAndView("/login");
		model.addObject("value", value);
		return model;
	}

	@RequestMapping("/index")
	public ModelAndView index(String value) {
		ModelAndView model = new ModelAndView("/index");
		model.addObject("value", value);
		return model;
	}

	@RequestMapping("/web1")
	@ResponseBody
	public String web1() {
		return "web 1";
	}

	@RequestMapping("/web2")
	@ResponseBody
	public String web2() {
		return "web 2";
	}

}
