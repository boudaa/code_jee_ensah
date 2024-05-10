package com.ensah.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Personne;
import com.ensah.core.dao.ExemplePersonneRepository1;
import com.ensah.core.services.ExemplePersonneService1;

@Service
@Transactional
public class PersonneServiceImpl1 implements ExemplePersonneService1 {

	@Autowired
	private ExemplePersonneRepository1 personDao;

	public List<Personne> getAllPersonnes() {

		return personDao.findAll();
	}

	public void deletePersonne(Long id) {
		personDao.deleteById(id);

	}

	public Personne getPersonneById(Long id) {
		return personDao.findById(id).get();

	}

	public void addPersonne(Personne pPerson) {
		personDao.save(pPerson);

	}

	public void updatePersonne(Personne pPerson) {
		personDao.save(pPerson);

	}

	public Personne getPersonneByCin(String cin) {

		return personDao.getPersonneByCin(cin);

	}

	@Override
	public List<Personne> getPersonnesByNomPrenom(String nom, String prenom) {
		
		return personDao.getPersonneByNomAndPrenom(nom, prenom);
	}

}
