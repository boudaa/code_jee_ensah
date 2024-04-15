package com.bo;

import jakarta.persistence.Entity;

@Entity
public class Prof extends Personne {
	private String cin;

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

}
