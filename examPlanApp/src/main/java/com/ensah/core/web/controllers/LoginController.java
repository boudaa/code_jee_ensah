package com.ensah.core.web.controllers;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ensah.core.bo.Compte;
import com.ensah.core.bo.Role;
import com.ensah.core.bo.UserPrincipal;
import com.ensah.core.bo.Personne;
import com.ensah.core.web.models.UserAndAccountInfos;

@Controller
public class LoginController {

	@Autowired
	private HttpSession httpSession;

	/**
	 * Récupère les données de l'utilisateur connecté du contexte de securité et le
	 * stocke dans un objet personnalisé à enregistrer dans la session http
	 * 
	 * @return
	 */
	private UserAndAccountInfos getUserAccount() {
		// On vérifie si les infors de l'utilisateur sont déjà dans la session
		UserAndAccountInfos userInfo = (UserAndAccountInfos) httpSession.getAttribute("userInfo");

		if (userInfo == null) {

			// On obtient l'objet representant le compte connecté (Objet UserPrincipal
			// implémentant UserDetails)
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			// On cast vers notre objet UserPrincipal
			Compte userAccount = ((UserPrincipal) principal).getUser();

			Personne u = userAccount.getProprietaire();
			
			String roleName = userAccount.getRole().getNomRole();

			userInfo = new UserAndAccountInfos(u.getIdPersonne(), userAccount.getIdCompte(), userAccount.getLogin(),
					u.getNom(), u.getPrenom(), u.getEmail(), roleName);

			// On le stocke dans la session
			httpSession.setAttribute("userInfo", userInfo);
		}

		return userInfo;

	}

	@GetMapping("/showMyLoginPage")
	public String showLoginForm() {

		return "loginForm";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";

	}

	@GetMapping("/student/showHome")
	public String showStudentHomePage(Model m) {

		UserAndAccountInfos infoUser = getUserAccount();
		m.addAttribute("userInfos", infoUser);

		return "student/userHomePage";

	}

	@GetMapping("/prof/showHome")
	public String showProfHomePage(Model m) {

		UserAndAccountInfos infoUser = getUserAccount();
		m.addAttribute("userInfos", infoUser);
		return "prof/userHomePage";

	}

	@GetMapping("/cadreadmin/showHome")
	public String showCadreAdminHomePage(Model m) {

		UserAndAccountInfos infoUser = getUserAccount();
		m.addAttribute("userInfos", infoUser);
		return "cadreadmin/userHomePage";

	}

	@GetMapping("/biblio/showHome")
	public String showBiblioHomePage(Model m) {

		UserAndAccountInfos infoUser = getUserAccount();
		m.addAttribute("userInfos", infoUser);
		return "biblio/userHomePage";

	}

	@GetMapping("/admin/showAdminHome")
	public String showAdminHome(Model m) {

		UserAndAccountInfos infoUser = getUserAccount();
		m.addAttribute("userInfos", infoUser);
		return "admin/adminHome";

	}

}
