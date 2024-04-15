package com.ensah.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Contact;

@Repository
public class ContactDaoImpl extends DaoImpl<Contact, Long> implements IContactDao {


	@Override
	public List<Contact> getContactByName(String pName) {
		
		return getEntityByColValue("firstName", pName);
	}

}
