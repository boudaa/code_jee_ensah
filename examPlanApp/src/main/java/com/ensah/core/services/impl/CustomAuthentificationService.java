package com.ensah.core.services.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ensah.core.bo.Compte;
import com.ensah.core.bo.UserPrincipal;
import com.ensah.core.dao.ICompteRepository;

/**
 * 
 * Cette classe contient le gestionnaire d'authentification personnalisé
 *
 */

@Service
public class CustomAuthentificationService implements UserDetailsService {

	@Autowired
	private ICompteRepository userRepository; // le DAO qui gère les utilisateurs

	/** Utilisé pour la journalisation */
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	public CustomAuthentificationService() {
	}

	// Implémentation de la méthode de vérification de l'existence d'un compte
	// utilisateur

	@Override
	public UserDetails loadUserByUsername(String username) {

		Compte user = null;

		// On récupère le compte avec son username
		user = userRepository.getCompteByLogin(username);

		// Si il n'existe pas
		if (user == null) {

			// On écrit dans le journal un message de warning
			LOGGER.warn("Utilisateur " + username + " introuvable ");

			// Cette exception iforme Spring Security que l'utilisateur n'existe pas
			// et donc il n a pas le droit de se connecter
			throw new UsernameNotFoundException(username);
		}

		// Embaler l'objet de type UserAccount dans un objet de type UserPrincipal qui
		// lui même
		// implémente UserDetails
		return new UserPrincipal(user);
	}

}