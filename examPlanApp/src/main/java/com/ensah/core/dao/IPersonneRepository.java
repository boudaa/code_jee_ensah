package com.ensah.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Personne;

public interface IPersonneRepository extends JpaRepository<Personne, Long> {

	Personne getPersonneByCin(String cin);

}
