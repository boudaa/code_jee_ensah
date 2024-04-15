package com.ensah.core.dao;

import com.ensah.core.bo.Person;
import com.genericdao.GenericDao;

/**
 * Interface du DAO
 * 
 * @author T. BOUDAA
 *
 */
public interface IPersonDao extends GenericDao<Person, Long> {

	Person getPersonByNationalIdNumber(String nid);

}
