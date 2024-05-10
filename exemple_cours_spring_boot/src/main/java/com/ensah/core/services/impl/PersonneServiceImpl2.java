package com.ensah.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Personne;
import com.ensah.core.dao.ExemplePersonneRepository2;
import com.ensah.core.services.ExemplePersonneService2;

@Service
@Transactional
public class PersonneServiceImpl2 implements ExemplePersonneService2 {

	@Autowired
	private ExemplePersonneRepository2 personDao;



	public List<Personne> customSelect(String pPrenom) {

		return personDao.customSelect(pPrenom);

	}

}
