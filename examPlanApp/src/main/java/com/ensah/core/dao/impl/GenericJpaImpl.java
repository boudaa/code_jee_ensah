package com.ensah.core.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GenericJpaImpl<T, ID>  {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	private EntityManager entityManager;

	public List<T> getEntitiesByColValue(Class boClass, String colName, String colVal) {
		
		String rq = "SELECT u FROM " + boClass.getSimpleName() + "  u WHERE u." + colName + "= :" + colName;

		LOGGER.debug("execute request : " + rq + " in getEntityByColValue");

		Query query = entityManager.createQuery(rq, boClass);
		query.setParameter(colName, colVal);

		return (List<T>) query.getResultList();
	}

	public T getEntityByColValue(Class boClass, String colName, String colVal) {

		String rq = "SELECT u FROM " + boClass.getSimpleName() + "  u WHERE u." + colName + "= :" + colName;

		LOGGER.debug("execute request : " + rq + " in getEntityByColValue");

		Query query = entityManager.createQuery(rq, boClass);

		query.setParameter(colName, colVal);
		if (query.getResultList().isEmpty()) {
			return null;
		}
		return (T) query.getResultList().get(0);
	}

}
