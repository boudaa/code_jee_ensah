package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Role;
import com.ensah.core.bo.UserAccount;

public interface IUserAccountservice {

	public List<Role> getAllRoles();

	public List<UserAccount> getAllAccounts();

	public UserAccount getAccountByUserName(String login);

	public UserAccount createUser(String login, Long idRole, Long idPerson) ;

}
