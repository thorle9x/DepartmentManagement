/**
 * 
 */
package com.department.service;

import java.util.List;

/**
 * @author bao.pham
 *
 */
public interface IService<D> {

	D save(D model) throws Exception;

	D update(D model, Long id);

	D findById(Long id) throws Exception;

	List<D> findAll();

	Long deleteById(Long id);

}