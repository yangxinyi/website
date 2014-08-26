package com.map.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author yangxinyi
 * 娴嬭瘯鐢�
 */
@Controller
@RequestMapping(value="")
public class TestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView("/status");
		modelAndView.addObject("status", "succ full");
		LOG.info("run success");
		return modelAndView;
	}
	
}