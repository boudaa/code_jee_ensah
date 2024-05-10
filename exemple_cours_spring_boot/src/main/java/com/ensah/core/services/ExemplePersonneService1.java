package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Personne;

public interface ExemplePersonneService1 {

	public void addPersonne(Personne pPerson);
	
	public List<Personne> getPersonnesByNomPrenom(String nom, String prenom);

	public void updatePersonne(Personne pPerson);

	public List<Personne> getAllPersonnes();

	public void deletePersonne(Long id);

	public Personne getPersonneById(Long id);

	public Personne getPersonneByCin(String cin);

}
