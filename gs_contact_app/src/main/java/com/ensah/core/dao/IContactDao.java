package com.ensah.core.dao;



import java.util.List;

import com.ensah.core.bo.Contact;



/**
 * Interface du DAO
 * 
 * @author T. BOUDAA
 *
 */
public interface IContactDao extends IDao<Contact, Long>  {

	List<Contact> getContactByName(String pName);

	

}
