package com.ensah;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ensah.core.bo.Personne;
import com.ensah.core.services.ExemplePersonneService1;
import com.ensah.core.services.ExemplePersonneService2;
import com.ensah.core.services.ExemplePersonneService3;

@SpringBootApplication
public class ExempleCoursSpringBootApplication implements CommandLineRunner {

	@Autowired
	private ExemplePersonneService1 personeService1;
	@Autowired
	private ExemplePersonneService2 personeService2;
	@Autowired
	private ExemplePersonneService3 personeService3;

	public static void main(String[] args) {
		SpringApplication.run(ExempleCoursSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Personne p1 = new Personne();
		p1.setCin("R125");
		p1.setEmail("test@test.com");
		p1.setNom("boudaa");
		p1.setPrenom("med");
		p1.setTelephone("060000");
		p1.setCin("R125");
		p1.setAge(21);
		personeService1.addPersonne(p1);

		Personne p2 = new Personne();
		p2.setCin("RX125");
		p2.setEmail("xtest@test.com");
		p2.setNom("xboudaa");
		p2.setPrenom("med");
		p2.setTelephone("060000");
		p2.setAge(30);

		Personne p3 = new Personne();
		p3.setCin("RX125");
		p3.setEmail("xtest@test.com");
		p3.setNom("xboudaa");
		p3.setPrenom("med");
		p3.setTelephone("060000");
		p3.setAge(40);
		
		Personne p4 = new Personne();
		p4.setCin("RXA125");
		p4.setEmail("xtest@test.com");
		p4.setNom("aaa");
		p4.setPrenom("aaa");
		p4.setTelephone("060000");
		p4.setAge(17);

		personeService1.addPersonne(p4);

		List<Personne> personneList = personeService2.customSelect("med");

		for (Personne it : personneList) {
			System.out.println(it);
		}
		System.out.println("----------------------");

		personneList = personeService1.getPersonnesByNomPrenom("boudaa","med");

		for (Personne it : personneList) {
			System.out.println(it);
		}

		
		System.out.println("----------------------");

		personneList = personeService3.getMineures();

		for (Personne it : personneList) {
			System.out.println(it);
		}
	}
}
