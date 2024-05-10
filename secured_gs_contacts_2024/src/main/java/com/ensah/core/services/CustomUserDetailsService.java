package com.ensah.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ensah.core.bo.UserAccount;
import com.ensah.core.bo.UserPrincipal;
import com.ensah.core.dao.IUserAccountDao;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserAccountDao userAccountDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserAccount user = userAccountDao.getUserAccountByLogin(username);
		System.out.println(user);

		if (user == null) {
			throw new UsernameNotFoundException("Utilisateur introuvable par " + username);
		}

		if (user.getRole() == null) {
			throw new UsernameNotFoundException("Utilisateur sans droits d'accès");

		}

		return new UserPrincipal(user);
	}

}
