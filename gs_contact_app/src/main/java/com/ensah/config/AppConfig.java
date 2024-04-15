package com.ensah.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ensah.core.bo.*;

//Configuration d'une application Spring MVC (@EnableWebMvc)
//Avec support des transactions (@EnableTransactionManagement)

@EnableWebMvc // Configuration d'une Application Spring MVC
@Configuration // Classe de configuration qui va contenir des beans à créer automatiquement par
				// Spring
@ComponentScan(basePackages = { "com.ensah" }) // Packages à scanner pour chercher les beans spring de type component
													// (càd @controller, @repository, @service)
@EnableTransactionManagement // Support des transactions
public class AppConfig implements WebMvcConfigurer {

	/**
	 * A utiliser si vous voulez faire la journalisation. Voir le fichier log4j.xml
	 * ou .properties
	 */
	private Logger LOGGER = Logger.getLogger(getClass().getName());

	public AppConfig() {

		// On enregistre une trace dans le journal
		LOGGER.debug(" configuration init...");
	}

	// Configuration du ViewResolver

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);

		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		return bean;
	}

	
	
	// SessionFactory
	// Gestionnaire de transaction 
	
	
	
	// Configuration de la Session Factory de Hibernate
	@Bean // Nécessaire pour que Spring créra automatiquemnt la sessionFactory
	public LocalSessionFactoryBean sessionFactory() {

		// Code copié de la documentation

		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		// Les informations de votre base de données
		BasicDataSource dataSource = new BasicDataSource();

		// A configurer en fonction de votre base de données
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver"); // Driver
		dataSource.setUrl("jdbc:mariadb://localhost:3306/gs_contact_database"); // L'url d'accès à la base de données
		dataSource.setUsername("root"); // login
		dataSource.setPassword(""); // mot de passe

		sessionFactory.setDataSource(dataSource);

		// Propriété Hibernate
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDB103Dialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");

		sessionFactory.setHibernateProperties(hibernateProperties);

		// Indiquer vos classes annotées par @Entity ici
		sessionFactory.setAnnotatedClasses(Contact.class);

		// Tracer dans le journal juste pour des raisons de débougage
		// que la session Factory a été bien crée
		if (sessionFactory != null) {
			LOGGER.debug(" sessionFactory created ...");
		}

		return sessionFactory;
	}



	// Configuration du Gestionnaire des Transactions
	// Spring définit une API pour la gestion des Transactions
	// (PlatformTransactionManager) qui cache l'implémentation réelle du
	// gestionnaire de transactions pour que l'application ne sera pas dépendante
	// avec
	// les frameworks sous-jacents utilisés dans la couche dao.
	// Ici nous avons configué un gestionnaire de transactions pour Hibernate
	// (HibernateTransactionManager) mais que nous retournons sous forme de
	// PlatformTransactionManager (car HibernateTransactionManager est une
	// implémentation de HibernateTransactionManager)

	@Bean // Permet à Spring de créer automatiquement ce bean de gestion des transaction
			// et de l'injecter dans son bon endroit pour l'utilsier dans la couche service
			// dans les services annotées par @transactional et @service
	@Autowired // Permet d'injecter sessionFactory nécessaire pour le gestionnaire de
				// transaction
	public PlatformTransactionManager transactionManager(final SessionFactory sessionFactory) {

		// Création du gestionnaire de transaction de Hibernate
		final HibernateTransactionManager txManager = new HibernateTransactionManager();

		// On lui injecte la session Factory crée dans un autre bean un peu plus haut
		// dans cette classe
		txManager.setSessionFactory(sessionFactory);

		// Tracer si gestionnnaire de transaction est bien crée
		if (txManager != null) {
			LOGGER.debug(" Hibernate Transaction Manager created ...");

		}

		return txManager;

	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
	    return new PersistenceExceptionTranslationPostProcessor();
	}
	
	
	// Permet d'éviter les exception Lazy
		public void addInterceptors(InterceptorRegistry registry) {
			OpenSessionInViewInterceptor openSessionInViewInterceptor = new OpenSessionInViewInterceptor();
			openSessionInViewInterceptor.setSessionFactory(sessionFactory().getObject());
			
			registry.addWebRequestInterceptor(openSessionInViewInterceptor).addPathPatterns("/**").order(Ordered.HIGHEST_PRECEDENCE);
		}

		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		}

}