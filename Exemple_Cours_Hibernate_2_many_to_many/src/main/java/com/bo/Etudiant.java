package com.bo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "ETUDIANT_TAB")
public class Etudiant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String nom;
	private String cin;
	private String prenom;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "adresse_etudiant", joinColumns = @JoinColumn(name = "id_etudiant"), inverseJoinColumns = @JoinColumn(name = "id_adresse"))
	private Set<Adresse> adresses = new HashSet<Adresse>();

	public void setAdresses(Set<Adresse> adresses) {
		// Dissociate existing associations
		for (Adresse a : this.adresses) {
			a.removeEtudiant(this); // Dissociate
		}
		this.adresses.clear(); // Clear all associations

		// Add new associations
		this.adresses.addAll(adresses);
		for (Adresse a : adresses) {
			a.addEtudiant(this); // Re-associate
		}
	}

	public void addAdresse(Adresse adresse) {
		if (adresse != null && !adresses.contains(adresse)) {
			adresses.add(adresse);
			adresse.addEtudiant(this);
		}
	}

	public void removeAdresse(Adresse adresse) {
		if (adresse != null && adresses.contains(adresse)) {
			adresses.remove(adresse); // Remove from this set
			adresse.removeEtudiant(this); // Dissociate from the other side
		}
	}

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
		return "Etudiant [id=" + id + ", nom=" + nom + ", cin=" + cin + ", adresse=" + adresses + ", prenom=" + prenom
				+ "]";
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

	public Set<Adresse> getAdresses() {
		return adresses;
	}

}
