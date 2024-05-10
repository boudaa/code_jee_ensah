package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Contact;


/**
 * Interface du Service
 * 
 * @author T. BOUDAA
 *
 */
public interface IContactService {

	public void addContact(Contact pPerson);

	public void updateContact(Contact pPerson);

	public List<Contact> getAllContacts();

	public void deleteContact(Long id);

	public Contact getContactById(Long id);

	public List<Contact> searchContact(String pName);

}
