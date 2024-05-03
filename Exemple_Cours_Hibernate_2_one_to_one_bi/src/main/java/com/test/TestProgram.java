package com.test;

import java.util.List;

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
	 * Dans ce programme dans : 
	 * 1- une première session: on crée un étudiant on lui affecte une adresse a0
	 * puis on sauvegarde l'étudiant dans la base de données.
	 * 2- Dans une deuxième session : on recharge l'étudiant de la base de données et
	 * on supprime sa relation avec l'adresse a0. 
	 * 3- Dans une troisième session : on recharge de la base de données l'étudiant
	 *  et on vérifie que l'étudiant n'a plus d'adresse
	 * 4- Dans une 4ème session nous avons affecté une nouvelle adresse à l'étudiant
	 * 5- Dans une  5ème session on a vérifié que les modifications ont bien pris effet
	 * en base de données.
	 * 
	 * NB: Pour simplicité j'ai supprimé le code complet de gestion des transactions
	 */

	public static void main(String[] args) {

		Session session = null;
		Transaction tx = null;

		// Création d'un étudiant
		Etudiant etd1 = new Etudiant();
		etd1.setNom("boudaa");
		etd1.setPrenom("Mohamed");
		etd1.setCin("A11111");

		Adresse ad0 = new Adresse();
		ad0.setVille("Al Hoceima");

		Adresse ad1 = new Adresse();
		ad1.setVille("Nador");

		// Affecter une adresse à l'étudiant
		etd1.setAdresse(ad0);

		System.out.println("########################## SESSION 1 #############################");

		// on obtient une session
		session = sf.getCurrentSession();

		// On commence une transaction
		tx = session.beginTransaction();

		// Enregistrer l'étudiants et son adresse associée
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
		System.out.println("Nombre d'adresses de l'étudiant avnat :" + (etd.getAdresse() == null ? 0 : 1));

		// Dissocier l'adresse de l'étudiant
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

		System.out.println("Nombre d'adresses de l'étudiant après  : " + (etd.getAdresse() == null ? 0 : 1));

		System.out.println("----Affichage de toutes les adresses ajoutées en base de données----");
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

				// On recharge l'étudiant de la base de données
				etd = session.get(Etudiant.class, etd1.getId());

				System.out.println("Affectation d'une nouvelle adresse");
				// Affecter une nouvelle adresse à l'étudiant
				etd.setAdresse(ad1);

				tx.commit();

				// Nouvelle session
				System.out.println("########################## SESSION 5 #############################");

				// on obtient une nouvelle session
				session = sf.getCurrentSession();

				// On commence une transaction
				tx = session.beginTransaction();

				// On recharge l'étudiant de la base de données
				etd = session.get(Etudiant.class, etd1.getId());

				Adresse a = etd.getAdresse();
				System.out.println("L'adresse de l'étudiant:");
				System.out.println(a);	
				System.out.println("Cette adresse est bien liée à l'étudiant aussi:");
				System.out.println(a.getEtudiant());

				tx.commit();

	}
}
