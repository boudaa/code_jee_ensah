package com.ensah.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Person;
import com.genericdao.SpringNativeHibernateGenericDAOImpl;

@Repository
public class PersonDaoImpl extends SpringNativeHibernateGenericDAOImpl<Person, Long> implements IPersonDao {

	@Override
	public Person getPersonByNationalIdNumber(String nid) {

		List<Person> list = getEntityByColValue("nationalIdNumber", nid);
		return list!=null && !list.isEmpty() ? list.get(0)  : null;
	}



	



}
