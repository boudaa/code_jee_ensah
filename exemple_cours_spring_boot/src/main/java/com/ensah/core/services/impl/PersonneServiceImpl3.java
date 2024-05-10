package com.ensah.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Personne;
import com.ensah.core.dao.ExemplePersonneRepository2;
import com.ensah.core.dao.ExemplePersonneRepository3;
import com.ensah.core.services.ExemplePersonneService2;
import com.ensah.core.services.ExemplePersonneService3;

@Service
@Transactional
public class PersonneServiceImpl3 implements ExemplePersonneService3 {

	@Autowired
	private ExemplePersonneRepository3 personDao;



	public List<Personne> getMineures() {

		return personDao.getMineures();

	}

}
