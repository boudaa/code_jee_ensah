package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bo.Adresse;
import com.bo.Etudiant;
import com.dao.SessionFactoryBuilder;

public class TestProgram {
	private static final SessionFactory sf = SessionFactoryBuilder.getSessionFactory();

	/**
	 * Dans ce programme 
	 * 1- Dans une première session: on crée un étudiant on lui affecte des adresses
	 * puis on sauvegarde cette étudiant dans la base de données 
	 * 2- Dans une deuxième session : on recharge l'étudiant de la base de
	 * données et on supprime sa relation avec une des adresses 
	 * 3-Dans une troisième session : on recharge de la base de données l'étudiant et on vérifie que
	 * l'adresse a été bien enlevé de la liste des adresse de cet étudiant
	 * 4- Dans les session 4 et 5 on montre que l'affectation de la même adresse ad0 (qui était une
	 * adresse de l'étudiant etd1) à un autre étudiant enlèvre ad0 de la liste des adresse de etd1
	 * ceci est conforme avec le fait qu'une adresse est associée à un et un seul étudiant
	 */

	public static void main(String[] args) {

		Session session = null;
		Transaction tx = null;

		// Création des objets
		Etudiant etd1 = new Etudiant();
		etd1.setNom("boudaa");
		etd1.setPrenom("Mohamed");
		etd1.setCin("A11111");
		
		Etudiant etd2 = new Etudiant();
		etd2.setNom("boudaa");
		etd2.setPrenom("Mohamed");
		etd2.setCin("A11111");


		Adresse ad0 = new Adresse();
		ad0.setVille("Al Hoceima");

		Adresse ad1 = new Adresse();
		ad1.setVille("Imzouren");

		Adresse ad2 = new Adresse();
		ad2.setVille("Tanger");

		Adresse ad3 = new Adresse();
		ad3.setVille("Nador");

		// Affecter des adresses à l'étudiant
		etd1.addAdresse(ad0);
		etd1.addAdresse(ad1);
		etd1.addAdresse(ad2);
		etd1.addAdresse(ad3);

		System.out.println("########################## SESSION 1 #############################");

		// on obtient une session
		session = sf.getCurrentSession();

		// On commence une transaction
		tx = session.beginTransaction();

		// Enregistrer l'étudiants et ses adresses associées
		session.persist(etd1); // Attention si on utilise la méthode save (propre à Hibernate) à la place de
								// persist
								// CascadeType.PERSIST ne propagera pas l'opération de persistance aux entités
								// composites.

		System.out.println("----Affichage de tous les étudiants ajoutés en base de données----");

		Query<Etudiant> query = session.createQuery("from com.bo.Etudiant", Etudiant.class);
		List<Etudiant> list = query.getResultList();

		for (Etudiant it : list) {
			System.out.println(it);
		}

		// On valide la transaction. La session sera fermée
		tx.commit();

		// Nouvelle session
		System.out.println("########################## SESSION 2 #############################");

		// on obtient une nouvelle session
		session = sf.getCurrentSession();

		// On commence une transaction
		tx = session.beginTransaction();

		// On recharge l'étudiant de la base de données
		 etd1 = session.get(Etudiant.class, etd1.getId());

		// afficher les adresse de l'étudiant
		System.out.println("Liste des adresses de l'étudiant:");
		Set<Adresse> listAd = etd1.getAdresses();
		Adresse temp = null;
		for (Adresse it : listAd) {
			System.out.println(it);
			temp = it;
		}

		// Supprimer l'une de ses adresses.
		System.out.println("Suppresion d'une adresse de la liste des adresses de l'étudiant");
		etd1.removeAdresse(temp);

		tx.commit();

		// Nouvelle session
		System.out.println("########################## SESSION 3 #############################");

		// on obtient une nouvelle session
		session = sf.getCurrentSession();

		// On commence une transaction
		tx = session.beginTransaction();

		// On recharge l'étudiant de la base de données

		etd1 = session.get(Etudiant.class, etd1.getId());

		// afficher les adresse de l'étudiant
		System.out.println("Liste des adresses de l'étudiant:");
		listAd = etd1.getAdresses();
		for (Adresse it : listAd) {
			System.out.println(it);
		}


		System.out.println("----Affichage de toutes les adresses ajoutés en base de données----");
		Query<Adresse> queryAdd = session.createQuery("from com.bo.Adresse", Adresse.class);
		List<Adresse> listAdd = queryAdd.getResultList();

		for (Adresse it : listAdd) {
			System.out.println(it);
		}

		tx.commit();
		
		
		// Nouvelle session
		System.out.println("########################## SESSION 4 #############################");

		// on obtient une nouvelle session
		session = sf.getCurrentSession();

		// On commence une transaction
		tx = session.beginTransaction();
	
		
		ad0 = session.merge(ad0);//rattacher l'objet à la session
		etd2.addAdresse(ad0);//la meme adresse est affectée à un autre étudiant
		session.persist(etd2);
		
		tx.commit();
		
		
		
		// Nouvelle session
		System.out.println("########################## SESSION 5 #############################");

		// on obtient une nouvelle session
		session = sf.getCurrentSession();

		// On commence une transaction
		tx = session.beginTransaction();

		// On recharge les deux étudiants de la base de données

		etd1 = session.get(Etudiant.class, etd1.getId());
		etd2 = session.get(Etudiant.class, etd2.getId());
		
		// afficher les adresse des étudiants
		System.out.println("Liste des adresses de l'étudiant 1:");
		for (Adresse it : etd1.getAdresses()) {
			System.out.println(it);
		}

		System.out.println("Liste des adresses de l'étudiant 2:");
		for (Adresse it : etd2.getAdresses()) {
			System.out.println(it);
		}

	

		tx.commit();
		
		
	

	}
}
