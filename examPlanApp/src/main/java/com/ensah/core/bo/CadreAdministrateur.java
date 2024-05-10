package com.ensah.core.bo;


import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="idCardreAdmin")
public class CadreAdministrateur extends Personne {

   private String grade;

public String getGrade() {
	return grade;
}

public void setGrade(String grade) {
	this.grade = grade;
}

   
   
}