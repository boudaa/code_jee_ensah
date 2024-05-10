package com.ensah.core.dao;

import java.util.List;

import com.ensah.core.bo.Personne;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ExemplePersonneRepository3CustomImpl implements ExemplePersonneRepository3Custom {

	@PersistenceContext
	private EntityManager em;

	public List<Personne> getMineures() {

		System.out.println("Method  getMineures !");
		return em.createQuery("SELECT p FROM Personne p WHERE age < 18 ").getResultList();
	}

}
