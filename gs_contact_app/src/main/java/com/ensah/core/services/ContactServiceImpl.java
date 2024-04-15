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
		ContactDao.create(pContact);

	}

	public List<Contact> getAllContacts() {
		return ContactDao.getAll();
	}

	public void deleteContact(Long id) {
		ContactDao.delete(id);

	}

	public Contact getContactById(Long id) {
		return ContactDao.findById(id);

	}

	public void updateContact(Contact pContact) {
		ContactDao.update(pContact);

	}

	@Override
	public List<Contact> searchContact(String pName) {
		return ContactDao.getContactByName(pName);
	}

}
