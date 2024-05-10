package com.ensah.core.web.models;

public class UserAndAccountInfos {

	private Long idPersonne;
	
	private Long idCompte;
	
	private String login;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String roleName;
	
	
	public UserAndAccountInfos() {
	}
	

	public UserAndAccountInfos(Long idPersonne, Long idCompte, String login, String nom, String prenom, String email,String roleName) {
		this.idPersonne = idPersonne;
		this.idCompte = idCompte;
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.roleName = roleName;
	}

	
	
	
	@Override
	public String toString() {
		return "UserAndAccountInfos [idPersonne=" + idPersonne + ", idCompte=" + idCompte + ", login=" + login
				+ ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}


	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public Long getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(Long idCompte) {
		this.idCompte = idCompte;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
