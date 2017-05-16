package com.bk;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;//new Class<?>[] { AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;// new Class<?>[] { WebMvcConfig.class, RepositoryRestMvcConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/ssj/rest/*" };
	}

}
