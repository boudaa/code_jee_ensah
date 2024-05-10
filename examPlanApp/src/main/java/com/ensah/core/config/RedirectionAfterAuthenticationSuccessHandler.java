package com.ensah.core.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Cette classe permet de personnaliser la redirection après l'authntification
 * 
 * @author Hp
 *
 */
public class RedirectionAfterAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	//Logger
	protected final Log LOGGER = LogFactory.getLog(getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public RedirectionAfterAuthenticationSuccessHandler() {
		super();
	}



	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}


	protected void handle(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		final String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			LOGGER.debug("Impossible de rediriger car la réponse a été déjà envoyé " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	
	protected String determineTargetUrl(final Authentication authentication) {

		Map<String, String> roleTargetUrlMap = new HashMap<>();

		//redirection selon le role vers la page home
		roleTargetUrlMap.put("ROLE_STUDENT", "/student/showHome"); 
		roleTargetUrlMap.put("ROLE_CADRE_ADMIN", "/cadreadmin/showHome"); 
		roleTargetUrlMap.put("ROLE_PROF", "/prof/showHome"); 
		roleTargetUrlMap.put("ROLE_ADMIN", "/admin/showAdminHome"); 
		roleTargetUrlMap.put("ROLE_BIBLIO", "/biblio/showHome"); 

		//Vous pouvez laisser ce code sans changement
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {

			String authorityName = grantedAuthority.getAuthority();
			if (roleTargetUrlMap.containsKey(authorityName)) {
				return roleTargetUrlMap.get(authorityName);
			}
		}

		throw new IllegalStateException();
	}

	/**
	 * Removes temporary authentication-related data which may have been stored in
	 * the session during the authentication process.
	 */
	protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
		final HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}