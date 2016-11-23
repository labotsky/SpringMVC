package com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.simple.entity.Category;
import com.simple.repository.CategoryRepository;

/**
 * @author Labotski_SV
 *
 */
@Service
public class CategoryService implements RESTService<Category, Long> {

	@Autowired
	CategoryRepository categoryRepository;

	public Category findById(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public List<Category> findAll() {
		return Lists.newArrayList(categoryRepository.findAll());
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.delete(id);
	}

	@Override
	public Category save(Category entity) {
		return categoryRepository.save(entity);
	}

}
