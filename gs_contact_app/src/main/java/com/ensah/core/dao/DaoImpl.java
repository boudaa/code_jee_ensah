package com.ensah.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Classe de base pour tous les DAOs, elle implémente les méthodes CRUD
 * génériques définit par le contrat GenericDAO<T>. Cette implémentation est
 * basée sur Hibernate nativement
 * 
 * @version 1.1
 * 
 * @author <a href="mailto:tarik.boudaa@gmail.com">T.BOUDAA Ecole Nationale des
 *         Sciences Appliquées </a>
 * 
 * @param <T>  le type d'objet métier manipulé
 * @param <PK> le type utilisé pour l'indentifiant d'un objet métier
 */
public abstract class DaoImpl<T, PK extends Serializable> implements IDao<T, PK> {

	/** L'objet Class de la classe BO manipulée par le DAO */
	protected Class<T> boClass;

	/** Utilisé par tous les DAOs */
	protected final Logger LOGGER;

	/** la fabrique des session */
	@Autowired
	protected SessionFactory sf;

	public DaoImpl() {
		// On determine dynamiquement l'objet de type Class
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		boClass = (Class) pt.getActualTypeArguments()[0];
		LOGGER = Logger.getLogger(boClass);
		LOGGER.debug("Le DAO pour " + boClass.getName() + " est bien crée");

	}

	protected Session getSession() {
		return sf.getCurrentSession();
	}

	public T create(T o) {

		LOGGER.debug("appel de la méthode create");

		// On obtient la session en cours
		Session s = sf.getCurrentSession();

		s.persist(o);

		return o;
	}

	public void update(T o) {

		LOGGER.debug("appel de la méthode update");

		// On obtient la session en cours
		Session s = getSession();

		s.merge(o);

	}

	public List<T> getAll() {

		LOGGER.debug("appel de la méthode getAll");

		// On obtient la session en cours
		Session s = getSession();

		return s.createQuery("from " + boClass.getCanonicalName(), boClass).getResultList();

	}

	public T findById(PK pId) {
		LOGGER.debug("appel de la méthode findById");

		// On obtient la session en cours
		Session s = getSession();

		T obj = (T) s.get(boClass, pId);

		return obj;
	}



	public List<T> getEntityByColValue(String pColumnName, String pValue) {
		Map<String, String> colValues = new HashMap<String, String>();
		colValues.put(pColumnName, pValue);
		return getEntityByColValue(colValues);
	}

	public List<T> getEntityByColValue(Map<String, String> colValues) {

		LOGGER.debug("appel de la méthode getEntityByColValue");

		// On obtient la session en cours
		Session s = getSession();

		//Construire la requete
		StringBuffer sb = new StringBuffer();
		sb.append("from " + boClass.getCanonicalName() + " where ");
		int c =0;
		for (Map.Entry<String, String> entry : colValues.entrySet()) {
			if(c!=0) {
				sb.append(" and ");
			}
			sb.append(entry.getKey() + "= :" + entry.getKey());
			c++;
		}
		Query<T> query = s.createQuery(sb.toString(), boClass);

		for (Map.Entry<String, String> entry : colValues.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}

	public void delete(PK pId)  {

		LOGGER.debug("appel de la méthode delete");

		// On obtient la session en cours
		Session s = getSession();

		T obj = (T) findById(pId);
		s.remove(obj);

	}

	public List<T> getAllDistinct() {

		Collection<T> result = new LinkedHashSet<T>(getAll());
		return new ArrayList<T>(result);

	}

}
