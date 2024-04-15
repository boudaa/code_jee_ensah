package com.ensah.core.dao;

import java.util.List;
import java.util.Map;

/**
 * Définit le contrat d'un DAO d'une manière générique
 * 
 * @author <a href="mailto:tarik.boudaa@gmail.com">T.BOUDAA, Ecole Nationale des
 *         Sciences Appliquées Al Hoceima</a>
 *
 * @param <T>
 *            Le type de l'objet metier (l'entité) manipulé par le DAO
 * @param <PK>
 *            Le type utilisé pour les identifiants des entités (Long,
 *            Integer,...)
 */
public interface IDao<T, PK> {

	/**
	 * permet de persister un objet
	 * 
	 * @param o
	 *            l'objet à persister
	 * @return l'identifiant autogénérée après l'enregistrement de l'objet
	 */
	T create(T o);

	/**
	 * Permet d'rattacher un objet détaché pour synchorniser son état avec la base
	 * de données
	 * 
	 * @param o
	 *            l'objet détaché contenant éventuellement des modifications
	 *            effectuées en dehors d'une session
	 */
	void update(T o);

	/**
	 * Permet d'effectuer une recherche sur les entité de type T
	 * 
	 * @return Retourne toutes les entités de type T de la base de données
	 * 
	 */
	List<T> getAll();

	/**
	 * Variante de la méthode getAll qui retourne une liste de résultats en ignorant
	 * les doublants
	 * 
	 * @return toute les différentes entités de type T
	 */
	List<T> getAllDistinct();

	/**
	 * Recherche une entité par son identifiant
	 * 
	 * @param pId
	 *            identifiant de l'entité
	 * @return l'entité de type T ayant l'identifiant pId
	
	 */
	T findById(PK pId)  ;

	/**
	 * Permet de rechercher des entités en base de données avec la valeur d'une
	 * propriété de l'objet
	 * 
	 * @param pColName
	 *            le nom de la propriété (équivalent d'une colonne en base de
	 *            données)
	 * @param pColVal
	 *            valeur de la propriété
	 * @return la liste des entités respectant la condition de recherche
	 */
	
	public List<T> getEntityByColValue(String pColumnName, String pValue) ;
	
	public List<T> getEntityByColValue(Map<String, String> colValues)  ;
	
	

	/**
	 * Permet de supprimer une entité dans la base de données
	 * 
	 * @param pId
	 *            l'identifiant de l'entité à supprimer
	
	 */
	void delete(PK pId);



}