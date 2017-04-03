package com.bk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloController {
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(method = RequestMethod.GET)public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");
	      logger.debug("Debug message");
	        logger.info("Info message");
	        logger.warn("Warn message");
	        logger.error("Error message");
	      return "hello";
	   }

}
