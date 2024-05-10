package com.ensah.core.bo;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonne;

	private String nom;

	private String prenom;

	@Column(unique = true)
	private String cin;

	private String email;

	@OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL, targetEntity = UserAccount.class)
	private Set<UserAccount> comptes;

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPerson) {
		this.idPersonne = idPerson;
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

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserAccount> getComptes() {
		return comptes;
	}

	public void setComptes(Set<UserAccount> comptes) {
		this.comptes = comptes;
	}

}