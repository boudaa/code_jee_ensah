package com.ensah.core.bo;


import java.util.*;
import jakarta.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "idEtudiant")
public class Etudiant extends Personne {

	private String cne;

	private Date dateNaissance;



	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(java.util.Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}




}