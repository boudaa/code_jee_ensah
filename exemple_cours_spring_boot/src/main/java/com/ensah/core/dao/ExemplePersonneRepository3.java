package com.ensah.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Personne;

public interface ExemplePersonneRepository3 extends JpaRepository<Personne, Long>, ExemplePersonneRepository3Custom {

	Personne getPersonneByCin(String cin);
	
	List<Personne> getPersonneByNomAndPrenom(String nom, String prenom);

}
