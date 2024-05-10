package com.ensah.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	// Logger
	Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

	
	@Autowired	
	private UserDetailsService userService;
	
	public AppSecurityConfig() {

		LOGGER.debug("AppSecurityConfig Initialisé");
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new RedirectionAfterAuthenticationSuccessHandler();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	// Configuration de l'algorithme de hashage des mots de passe
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// TODO : Configurer la securité de votre application

	
		
		
		    http
				.csrf(csrf -> csrf.disable()) 						
				
				.authorizeHttpRequests(authz -> authz.requestMatchers("/user/**").hasRole("USER")	
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()		
				

				)
				
				.formLogin(form -> form.loginPage("/showMyLoginPage")
						.loginProcessingUrl("/authenticateTheUser")
				
						.failureHandler(authenticationFailureHandler())
						.successHandler(authenticationSuccessHandler())
						.permitAll()

				)
				
				
				
				.logout(logout -> logout.logoutUrl("/logout") 
				.deleteCookies("JSESSIONID") 
				.permitAll())
				
				
				.exceptionHandling(exception -> exception.accessDeniedPage("/access-denied"));
		    
		    
		    
		return http.build();

	}
}
