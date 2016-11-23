package com.simple.service;

import java.util.List;

/**
 * @author Labotski_SV
 *
 * @param <T>
 * @param <ID>
 */
public interface RESTService<T, ID> {

	/**
	 * find entity by id
	 * 
	 * @param id
	 * @return
	 */
	public T findById(ID id);

	/**
	 * find all entities
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * delete entity by id
	 * 
	 * @param id
	 */
	public void deleteById(ID id);

	/**
	 * save entity
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity);

}
