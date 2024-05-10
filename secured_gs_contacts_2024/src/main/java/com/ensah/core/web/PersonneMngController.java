package com.ensah.core.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensah.core.bo.*;
import com.ensah.core.services.IPersonneService;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Ce controleur gère les personnes de type Etudiant, Enseignant et Cadre Admin
 * 
 * @author Boudaa
 *
 */

@Controller
@RequestMapping("/admin")
public class PersonneMngController {

	@Autowired
	private IPersonneService personService;

	@Autowired
	private HttpSession httpSession;

	/** Utilisé pour la journalisation */
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	public PersonneMngController() {

	}

	@GetMapping(value = "showForm")
	public String showForm(Model model) {

		model.addAttribute("personModel", new Personne());

		// Nous avons choisit d'utiliser une classe modèle personnalisée
		// définie par PersonModel pour une meilleur flexibilité

		List<Personne> persons = personService.getAllPersonnes();

		// Mettre la liste des personnes dans le modèle de Spring MVC
		model.addAttribute("personList", persons);

		return "admin/form";
	}

	@RequestMapping(value = "addPerson", method = RequestMethod.POST)
	public String process(@Valid @ModelAttribute("personModel") Personne person, BindingResult bindingResult,
			Model model, HttpServletRequest rq) {

		// En cas d'erreur de validation
		if (bindingResult.hasErrors()) {
			// rq.setAttribute("typePerson", +person.getTypePerson());
			return "admin/form";
		}

		personService.addPersonne(person);

		// rediriger vers l'action shwoForm avec le meme type de personne
		return "redirect:/admin/showForm";
	}

	@RequestMapping(value = "updatePersonForm/{idPerson}", method = RequestMethod.GET)
	public String updatePersonForm(@PathVariable("idPerson") int idPerson, Model model) {

		// On reoit comme paramètre l'id de la personne à mettre à jour
		Personne pm = personService.getPersonneById(Long.valueOf(idPerson));

		// Initialiser le modele avec la personne
		model.addAttribute("personModel", pm);

		return "admin/updateForm";
	}

	@RequestMapping(value = "serachPerson", method = RequestMethod.GET)
	public String serachPerson(@RequestParam String cin, Model model) {

		List<Personne> modelPersons = new ArrayList<Personne>();

		// On reoit comme paramètre l'id de la personne à mettre à jour
		Personne utl = personService.getPersonneByCin(cin);
		if (utl == null) {
			modelPersons.add(utl);
		}
		model.addAttribute("personList", modelPersons);

		return "admin/listPersons";
	}

	@RequestMapping("updatePerson")
	public String updatePerson(@Valid @ModelAttribute("personModel") Personne person, BindingResult bindingResult,
			Model model) {

		// En cas d'erreur
		if (bindingResult.hasErrors()) {

			return "admin/updateForm";
		}

		personService.updatePersonne(person);

		// Mettre le message de succès dans le modèle
		model.addAttribute("msg", "Opération effectuée avec succès");

		return "admin/updateForm";
	}

	@RequestMapping("managePersons")
	public String managePersons(Model model) {

		model.addAttribute("personList", personService.getAllPersonnes());

		return "admin/listPersons";
	}

	@RequestMapping(value = "deletePerson/{idPerson}", method = RequestMethod.GET)
	public String delete(@PathVariable int idPerson) {

		personService.deletePersonne(Long.valueOf(idPerson));

		return "redirect:/admin/managePersons";
	}

}
