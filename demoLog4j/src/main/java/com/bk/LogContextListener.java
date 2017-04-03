package com.bk;

import javax.servlet.ServletContextEvent;

import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.web.Log4jServletContextListener;

public class LogContextListener extends Log4jServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent servletContextevent) {
		String env =  System.getenv("PENV")==null?System.getProperty("PENV"):System.getenv("PENV");
		if(null == env) {
			throw new RuntimeException("Cound not find Environment varriable PENV which indicate the deployment environment. Please configure environemnt varriable.");
		}else {
			String pathToConfigFile = "classpath:log4j2-"+env+".xml";
		    Configurator.initialize(null, pathToConfigFile);
			super.contextInitialized(servletContextevent);
			
		}
		
	}
}
