package com.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * 
 * 
 * @author T.BOUDAA
 *
 */
public class EntityManagerFactoryBuilder {

	private static EntityManagerFactory emf;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("com.ensah.gs_etudiants");

		}
		return emf;
	}

}
