package com.bo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Adresse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToMany(mappedBy = "adresses")
	private Set<Etudiant> etudiants = new HashSet<Etudiant>();

	private String ville;

    public void setEtudiants(Set<Etudiant> etudiants) {
        // Dissociate existing associations
        for (Etudiant e : this.etudiants) {
            e.removeAdresse(this); // Dissociate
        }
        this.etudiants.clear(); // Clear all associations

        // Add new associations
        this.etudiants.addAll(etudiants);
        for (Etudiant e : etudiants) {
            e.addAdresse(this); // Re-associate
        }
    }

    public void addEtudiant(Etudiant etudiant) {
        if (etudiant != null && !etudiants.contains(etudiant)) {
            etudiants.add(etudiant);
            etudiant.addAdresse(this);
        }
    }

    public void removeEtudiant(Etudiant etudiant) {
        if (etudiant != null && etudiants.contains(etudiant)) {
            etudiants.remove(etudiant);  // Remove from this set
            etudiant.removeAdresse(this);  // Dissociate from the other side
        }
    }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Adresse)) {
			return false;
		}
		return id != null && id.equals(((Adresse) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", ville=" + ville + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Set<Etudiant> getEtudiants() {
		return etudiants;
	}

}
