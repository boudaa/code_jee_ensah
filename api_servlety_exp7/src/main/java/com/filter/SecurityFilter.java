package com.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

//voir la suite de la configuration dans WEB-INF\web.xml
@WebFilter(filterName = "security")
public class SecurityFilter implements Filter {

	private static Logger LOGGER;


	public SecurityFilter() {

		LOGGER = Logger.getLogger(getClass());

		LOGGER.info("Filter security est crée");
	}

	

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOGGER.info("security filter est appelé..");

        //Votre code de securité ici

		chain.doFilter(request, response);

		LOGGER.info("fin d'execution de security filter ..");

	}

}
