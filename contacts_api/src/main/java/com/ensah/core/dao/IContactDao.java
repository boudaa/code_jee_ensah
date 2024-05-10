package com.ensah.core.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Contact;



/**
 * Interface du DAO
 * 
 * @author T. BOUDAA
 *
 */
public interface IContactDao extends JpaRepository<Contact, Long>  {

	List<Contact> getContactByFirstName(String pName);

	

}
