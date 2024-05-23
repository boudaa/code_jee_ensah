package com.ensah.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensah.core.bo.UserAccount;
import com.ensah.core.services.IPersonneService;
import com.ensah.core.services.IUserAccountservice;
import com.ensah.core.services.impl.UsernameExistsException;
import com.ensah.core.web.models.AccountModel;

@Controller
@RequestMapping("/admin") // Très important car, dans notre conf Spring security les URL qui commencent
							// par ADMIN
							// sont dédiées
							// à l'admin. Toutes les URL dédinies dans ce controleur définissent des
							// fonctionnalités
							// accessibles uniquement à l'administrateur
public class AccountController {

	@Autowired
	private IUserAccountservice userService; // On obtient par injection automatique le service

	@Autowired
	private IPersonneService personService; // On obtient par injection automatique le service

	// Cette méthode initialise le formulaire de création de compte
	@RequestMapping(value = "createAccountForm/{idPerson}", method = RequestMethod.GET)
	public String createAccountForm(@PathVariable Long idPerson, Model model) {

		// On crée le model
		AccountModel accountModel = new AccountModel(idPerson);
		// On enregistre le modèle pour le passer à la vue
		model.addAttribute("accountModel", accountModel);

		// On ajoute la liste des roles dans le modele
		model.addAttribute("roleList", userService.getAllRoles());

		// On ajoute également la liste des comptes dans le modèle
		model.addAttribute("accountList", userService.getAllAccounts());

		// On affiche la vue
		return "admin/formAccount";
	}

	@GetMapping("manageAccounts")
	public String manageAccounts(@ModelAttribute("accountModel") AccountModel accountModel, Model model) {

		model.addAttribute("accountList", userService.getAllAccounts());

		return "admin/accountList";
	}

	@GetMapping("createAccounts")
	public String createAccounts(@ModelAttribute("accountModel") AccountModel accountModel, Model model) {

		model.addAttribute("personList", personService.getAllPersonnes());

		return "admin/accountCreation";
	}

	// Cette méthode permet de créer un comote
	@PostMapping("addAccount")
	public String addAccount(@ModelAttribute("accountModel") AccountModel accountModel, Model model) {

		try {

			// La logique de la création du compte est implémenter au niveau service
			// Il suffit de passer le login et l'id du role et l'id de personne
			// à la couche service
			UserAccount createdUser = userService.createUser(accountModel.getUsername(), accountModel.getRoleId(),
					accountModel.getPersonId());

			model.addAttribute("msg", "l' utilisateur " + accountModel.getUsername() + " crée avec le mot de passe: "
					+ createdUser.getRawPassword());

		} catch (Exception e) {
			if(e instanceof DataIntegrityViolationException) {
				model.addAttribute("msg", "Erreur : Login " + accountModel.getUsername() + " exists !");

			}
			else {
				model.addAttribute("msg", "Erreur : compte non ajouté à cause d'une erreur");

			}
		}

		// On affiche la liste des comptes dans la vue
		model.addAttribute("accountList", userService.getAllAccounts());

		// On affiche la vue
		return "admin/accountList";

	}

}
