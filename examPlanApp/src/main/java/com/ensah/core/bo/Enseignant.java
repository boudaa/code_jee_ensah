package com.ensah.core.bo;


import jakarta.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "idEnseighant")
public class Enseignant extends Personne {

	private String specialite;


	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}



}