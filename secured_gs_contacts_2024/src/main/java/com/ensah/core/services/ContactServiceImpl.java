package com.ensah.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Contact;
import com.ensah.core.dao.IContactDao;

@Service
@Transactional
public class ContactServiceImpl implements IContactService {

	@Autowired
	private IContactDao ContactDao;

	public void addContact(Contact pContact) {
		ContactDao.save(pContact);

	}

	public List<Contact> getAllContacts() {
		return ContactDao.findAll();
	}

	public void deleteContact(Long id) {
		ContactDao.delete(getContactById(id));

	}

	public Contact getContactById(Long id) {
		return ContactDao.findById(id).get();

	}

	public void updateContact(Contact pContact) {
		ContactDao.save(pContact);

	}

	@Override
	public List<Contact> searchContact(String pName) {
		return ContactDao.getContactByLastName(pName);
	}

}
