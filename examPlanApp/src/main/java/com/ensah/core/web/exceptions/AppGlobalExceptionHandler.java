package com.ensah.core.web.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ensah.core.web.models.UserAndAccountInfos;

@ControllerAdvice
//@PropertySource("classpath:application.properties")
public class AppGlobalExceptionHandler {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private HttpSession httpSession;

	public static final String DEFAULT_ERROR_VIEW = "error";

	@Value("${gsabs.app.mode}")
	private String appMode;

	@ExceptionHandler
	public ModelAndView handleException(Exception exc, HttpServletRequest req) {

		// Log the error
		LOGGER.error("Exception : " + exc);
		
		ModelAndView modelAndView = new ModelAndView();

		String errorMsg = "";
		// If in PROD mode
		if ("PROD".equals(appMode)) {
			errorMsg = "The application cannout execute the action because it has encountered an error,"
					+ " please contact the administrator of the application for more details";

		}
		else {// DEV mode
			LOGGER.warn("Dev mode is active");

			 errorMsg = "The application is configured to work in DEV mode."
					+ " An Error has occurred due to following " + " exception. For more details, please see the log file: "
					+ exc.getStackTrace();
		}

		// get connected User
		UserAndAccountInfos userInfo = (UserAndAccountInfos) httpSession.getAttribute("userInfo");
		
		String view = DEFAULT_ERROR_VIEW;
		if(userInfo!=null) {
			if ("ROLE_STUDENT".equals((userInfo).getRoleName())) {
				view = "/student/error";
			} else if ("ROLE_CADRE_ADMIN".equals((userInfo).getRoleName())) {
				view = "/admin/error";
			} else if ("ROLE_PROF".equals((userInfo).getRoleName())) {
				view = "/prof/error";
			} else if ("ROLE_ADMIN".equals((userInfo).getRoleName())) {
				view = "/admin/error";
			} 
		}

	

		modelAndView.addObject("message", errorMsg);

		modelAndView.addObject("url", req.getRequestURL());
		modelAndView.setViewName(view);

		return modelAndView;

	}

}