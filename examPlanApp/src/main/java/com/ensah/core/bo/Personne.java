package com.ensah.core.bo;

import java.util.*;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonne;

	private String nom;

	private String prenom;

	@Column(unique = true)
	private String cin;

	private String email;

	private String telephone;

	private String nomArabe;

	private String prenomArabe;

	private String photo;

	@OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL, targetEntity = Compte.class)
	private Set<Compte> comptes;

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNomArabe() {
		return nomArabe;
	}

	public void setNomArabe(String nomArabe) {
		this.nomArabe = nomArabe;
	}

	public String getPrenomArabe() {
		return prenomArabe;
	}

	public void setPrenomArabe(String prenomArabe) {
		this.prenomArabe = prenomArabe;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

	@Override
	public String toString() {
		return "Utilisateur [idPerson=" + idPersonne + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", email="
				+ email + ", telephone=" + telephone + ", nomArabe=" + nomArabe + ", prenomArabe=" + prenomArabe
				+ ", photo=" + photo + "]";
	}

}