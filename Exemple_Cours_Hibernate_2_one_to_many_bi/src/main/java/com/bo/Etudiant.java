package com.bo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "ETUDIANT_TAB")
public class Etudiant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String cin;
	private String prenom;

	@OneToMany(mappedBy = "etudiant", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Adresse> adresses = new HashSet<Adresse>();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Etudiant)) {
			return false;
		}
		return id != null && id.equals(((Etudiant) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", cin=" + cin + ", adresses=" + adresses + ", prenom=" + prenom
				+ "]";
	}

	public Set<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(Set<Adresse> adresses) {
		// Dissociez toutes les anciennes adresses
		if (this.adresses != null) {
			for (Adresse adresse : this.adresses) {
				adresse.setEtudiant(null);
			}
		}

		this.adresses = adresses;

		// Reconnectez toutes les nouvelles adresses
		if (adresses != null) {
			for (Adresse adresse : adresses) {
				if (adresse.getEtudiant() != this) {
					adresse.setEtudiant(this);
				}
			}
		}
	}

	public void addAdresse(Adresse ad) {
		if (ad != null && !adresses.contains(ad)) {			
			ad.setEtudiant(this);
		}
	}

	public void removeAdresse(Adresse ad) {
		if (ad != null && adresses.contains(ad)) {
			adresses.remove(ad);
			ad.setEtudiant(null); 
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
