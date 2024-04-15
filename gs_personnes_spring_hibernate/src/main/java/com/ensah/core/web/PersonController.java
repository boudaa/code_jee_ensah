package com.ensah.core.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.ensah.core.bo.Person;
import com.ensah.core.services.IPersonService;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;

@Controller
public class PersonController {

	@Autowired
	private IPersonService personService; // Injection du service metier ici

	@Autowired
	private ServletContext appContext;

	private Map<String, String> countryList = new HashMap<String, String>(); // Contient les pays à afficher dans la vue

	public PersonController() {
		countryList.put("Morocco", "Morocco");
		countryList.put("France", "France");
		countryList.put("Spain", "Spain");

	}

	@RequestMapping("/showForm")
	public String showForm(Model model) {

		model.addAttribute("personModel", new Person()); // Ajouter un objet Personne vide comme attribut du modèle
		model.addAttribute("countryList", countryList); // Ajouter la liste des pays comme attribut du modèle
		model.addAttribute("personList", personService.getAllPersons());// Ajouter la liste des personnes comme attribut
																		// du modèle

		return "form"; // On retourne le nom de la vue
	}

	// on récupère la valeur du path variable idPerson dans le paramètre annotée
	// @PathVariable
	// Ici puisque nous avons utilisé le même nom pour le pathVariable et le
	// paramètre de la méthode, nous pouvons
	// remplacer @PathVariable(name = "idPerson") par @PathVariable
	@RequestMapping(value = "/updatePersonForm/{idPerson}", method = RequestMethod.GET)
	public String updatePersonForm(@PathVariable(name = "idPerson") int idPerson, Model model) {

		model.addAttribute("personModel", personService.getPersonById(Long.valueOf(idPerson)));
		model.addAttribute("countryList", countryList);

		return "updateForm";
	}

	// l'annotation @Valid est nécessaire pour faire la validation avec Hibernate
	// Validator
	// On obtient les données envoyées de la vue dans l'attribut du modèle
	// personModel
	// ces données sont copiées vers l'argument person
	// l'argument bindingResult permet de savoir si il y a des erreurs de validation
	@RequestMapping("/updatePerson")
	public String updatePerson(@Valid @ModelAttribute("personModel") Person person, BindingResult bindingResult,
			Model model) {

		// Si il y a des erreurs de validation
		if (bindingResult.hasErrors()) {
			model.addAttribute("countryList", countryList);
			return "updateForm";
		}

		// Si il y a pas des erreurs
		personService.updatePerson(person);
		model.addAttribute("personList", personService.getAllPersons());

		// rediriger vers un autre handler
		return "redirect:/managePersons";
	}

	@RequestMapping("/addPerson")
	public String process(@Valid @ModelAttribute("personModel") Person person, BindingResult bindingResult,
			ModelMap model) {
		model.addAttribute("countryList", countryList);

		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMsg", "Les données sont invalides.");
		} else {
			personService.addPerson(person);
			model.addAttribute("infoMsg", "Personne ajouté avec succès");

		}
		model.addAttribute("personList", personService.getAllPersons()); // Mettre la liste des personnes dans le modèle

		return "form";

	}

	@RequestMapping("/managePersons")
	public String managePersons(Model model) {

		model.addAttribute("personList", personService.getAllPersons()); // Mettre la liste des personnes dans le modèle

		return "listPersons";
	}

	@RequestMapping(value = "/deletePerson/{idPerson}", method = RequestMethod.GET)
	public RedirectView delete(@PathVariable int idPerson) {
		personService.deletePerson(Long.valueOf(idPerson));

		// Behind the scenes, RedirectView will trigger a
		// HttpServletResponse.sendRedirect() – which will perform the actual redirect.
		return new RedirectView(appContext.getContextPath() + "/managePersons");

		// return "redirect:/managePersons";
	}

	@PostMapping(value = "serachPerson")
	public String serachPerson(@RequestParam String nid, Model model) {

		Person per = personService.getPersonByNationalIdNumber(nid);
		List<Person> list = new ArrayList<>();
		if (per != null) {
			list.add(per);

		}
		// Initialiser le modele
		model.addAttribute("personList", list);

		return "listPersons";
	}

	public Map<String, String> getCountryList() {
		return countryList;
	}

	public void setCountryList(Map<String, String> countryList) {
		this.countryList = countryList;
	}

}
