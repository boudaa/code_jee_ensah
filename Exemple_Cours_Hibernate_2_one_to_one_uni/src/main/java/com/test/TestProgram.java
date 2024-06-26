package com.test;

import java.util.List;

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
	 * Dans ce programme dans une première session: on crée un étudiant on lui
	 * affecte une adresse puis on sauvegarde l'étudiant dans la base de données.
	 * Dans une deuxième session : on recharge le même étudiant de la base de
	 * données et on supprime son association avec l'adresse. Dans une troisième
	 * session : on recharge de la base de données le même étudiant et on vérifie
	 * que l'étudiant n'a plus d'adresse
	 * 
	 * NB: Pour simplicité j'ai supprimé le code complet de gestion des transactions
	 * 
	 */
	public static void main(String[] args) {

		Session session = null;
		Transaction tx = null;

		// Création des objets
		Etudiant etd1 = new Etudiant();
		etd1.setNom("boudaa");
		etd1.setPrenom("Mohamed");
		etd1.setCin("A11111");

		Adresse ad0 = new Adresse();
		ad0.setVille("Al Hoceima");

		// Affecter des adresses
		etd1.setAdresse(ad0);

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
		Etudiant etd = session.get(Etudiant.class, etd1.getId());
		System.out.println("Nombre d'adresses de l'étudiant avant  = " + (etd.getAdresse() == null ? 0 : 1));

		// Supprimer l'une de ses adresses.
		System.out.println("Dissocier l'adresse de l'étudiant");

		etd.setAdresse(null);

		tx.commit();

		// Nouvelle session
		System.out.println("########################## SESSION 3 #############################");

		// on obtient une nouvelle session
		session = sf.getCurrentSession();

		// On commence une transaction
		tx = session.beginTransaction();

		// On recharge l'étudiant de la base de données
		etd = session.get(Etudiant.class, etd1.getId());

		System.out.println("Nombre d'adresses de l'étudiant après  = " + (etd.getAdresse() == null ? 0 : 1));

		System.out.println("----Affichage de toutes les adresses ajoutés en base de données----");
		Query<Adresse> queryAdd = session.createQuery("from com.bo.Adresse", Adresse.class);
		List<Adresse> listAdd = queryAdd.getResultList();

		for (Adresse it : listAdd) {
			System.out.println(it);
		}

		tx.commit();

	}
}
