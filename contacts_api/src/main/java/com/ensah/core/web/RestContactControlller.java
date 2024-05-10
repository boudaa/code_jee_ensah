package com.ensah.core.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensah.core.bo.Contact;
import com.ensah.core.services.IContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/contacts")
public class RestContactControlller {

	@Autowired
	private IContactService service;

	@GetMapping("/{id}")
	public ResponseEntity<Contact> getByFirstName(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.getContactById(id));
	}

	@GetMapping("/names/{name}")
	public ResponseEntity<?>  getByFirstName(@PathVariable("name") String name) {
		

		List<Contact> contacts = service.searchContact(name);
		
		if (contacts.isEmpty()) {
			return new ResponseEntity<>("Aucun contact avec le nom "+name, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Contact>> getAllContacts() {

		List<Contact> contacts = service.getAllContacts();
		
		if (contacts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}

	@PostMapping
	public Contact addContact(@Valid @RequestBody Contact c) {

		c.setIdContact(null);
		service.addContact(c);
		return c;

	}

	@PutMapping
	public Contact updateContact(@RequestBody Contact c) {

		service.updateContact(c);
		return c;
	}

	@DeleteMapping("/{id}")
	public void deleteContact(@PathVariable("id") Long id) {
		service.deleteContact(id);
	}

	//validation handler
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handValidEx(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();
		List<ObjectError> validationErros = ex.getBindingResult().getAllErrors();
		for (ObjectError error : validationErros) {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		}

		return ResponseEntity.badRequest().body(errors);
	}

}
