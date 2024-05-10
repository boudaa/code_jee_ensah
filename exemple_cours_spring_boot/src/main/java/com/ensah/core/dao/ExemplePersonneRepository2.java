package com.ensah.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensah.core.bo.Personne;

public interface ExemplePersonneRepository2 extends JpaRepository<Personne, Long> {

	 @Query("SELECT p FROM Personne p WHERE p.prenom = :prenom order by age")
	 List<Personne> customSelect(@Param("prenom") String prenom);

}
