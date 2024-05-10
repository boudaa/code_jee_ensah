package com.ensah.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensah.core.bo.Contact;
import com.ensah.core.services.IContactService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class ContactController {

	@Autowired
	private IContactService contactService;

	public ContactController() {
	}

	@ModelAttribute
	public void init(Model model) {
		model.addAttribute("/showForm", false);
	}

	@RequestMapping(value = {"showForm","showUserHome"})
	public String showForm(Model model) {
		model.addAttribute("action", "addContact");
		model.addAttribute("contactModel", new Contact());
		model.addAttribute("showForm", true);
		model.addAttribute("contactList", contactService.getAllContacts());

		return "user/form";
	}

	@PostMapping("/addContact")
	public String addContact(@Valid @ModelAttribute("contactModel") Contact contact, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("showForm", true);
			model.addAttribute("errorMsg", "Les données sont invalides.");
		} else {
			contactService.addContact(contact);
			model.addAttribute("infoMsg", "Contact ajouté avec succès");
		}
		model.addAttribute("contactList", contactService.getAllContacts());

		return "user/form";
	}

	@GetMapping("/deleteContact/{idContact}")
	public String deleteContact(@PathVariable("idContact") Long idContact, Model model) {
		contactService.deleteContact(idContact);
		model.addAttribute("infoMsg", "Contact supprimé avec succès");
		model.addAttribute("contactList", contactService.getAllContacts());

		return "user/form";

	}

	@GetMapping("/updateContactForm/{idContact}")
	public String updateContactForm(@PathVariable("idContact") Long idContact, Model model) {
		Contact contact = contactService.getContactById(idContact);
		model.addAttribute("contactModel", contact);
		model.addAttribute("action", "updateContact");
		model.addAttribute("showForm", true);
		model.addAttribute("contactList", contactService.getAllContacts());

		return "user/form";

	}

	@PostMapping("/updateContact")
	public String updateContact(@Valid @ModelAttribute("contactModel") Contact contact, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("showForm", true);
			model.addAttribute("errorMsg", "Les données sont invalides.");
		} else {
			contactService.updateContact(contact);
			model.addAttribute("infoMsg", "Contact modifié avec succès");
		}
		model.addAttribute("contactList", contactService.getAllContacts());

		return "user/form";
	}

	@PostMapping("/serachContact")
	public String serachContact(@RequestParam("keyword") String keyword, Model model) {
		model.addAttribute("contactList", contactService.searchContact(keyword));
		return "form";
	}

	@GetMapping("/allContacts")
	public String allContacts(Model model) {
		model.addAttribute("contactList", contactService.getAllContacts());

		return "user/form";
	}

}
