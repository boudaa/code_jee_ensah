package com.ensah;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListenerExample implements ServletContextListener {

	public ContextListenerExample() {
		System.out.println("Ecouteur crée");
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("code à executer à l'initialisation du context");
	}


}
