package com.ensah.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Personne;
import com.ensah.core.dao.IPersonneRepository;
import com.ensah.core.services.IPersonneService;
import com.ensah.core.utils.ExcelExporter;

@Service
@Transactional
public class PersonneServiceImpl implements IPersonneService {

	@Autowired
	private IPersonneRepository personDao;

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

	public ExcelExporter preparePersonneExport(List<Personne> persons) {
		String[] columnNames = new String[] { "Nom", "Prénom", "CIN", "Email", "Télé" };
		String[][] data = new String[persons.size()][5];

		int i = 0;
		for (Personne u : persons) {
			data[i][0] = u.getNom();
			data[i][1] = u.getPrenom();
			data[i][2] = u.getCin();
			data[i][3] = u.getEmail();
			data[i][4] = u.getTelephone();
			i++;
		}

		return new ExcelExporter(columnNames, data, "persons");

	}

	public Personne getPersonneByCin(String cin) {

		return personDao.getPersonneByCin(cin);

	}

}
