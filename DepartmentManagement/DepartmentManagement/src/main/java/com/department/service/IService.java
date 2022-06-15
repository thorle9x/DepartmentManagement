package com.department.service;

import java.util.List;

public interface IService<D> {

	D save(D model);

	D update(D model, Long id);

	D findById(Long id);

	List<D> findAll();

	Long deleteById(Long id);

}