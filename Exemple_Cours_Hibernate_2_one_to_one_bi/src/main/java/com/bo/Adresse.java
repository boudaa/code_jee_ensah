package com.bo;

import jakarta.persistence.*;

@Entity
public class Adresse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(mappedBy = "adresse")
	private Etudiant etudiant;

	private String ville;

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

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void removeEtudiantLink() {
		this.etudiant = null;
	}

	public void setEtudiant(Etudiant etudiant) {

		if (this.etudiant != null && this.etudiant != etudiant) {
			this.etudiant.removeAdresseLink();
		}

		this.etudiant = etudiant;

		if (etudiant != null && etudiant.getAdresse() != this) {

			etudiant.setAdresse(this);
		}

	}

}
