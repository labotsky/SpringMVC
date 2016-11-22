package com.simple.repository;

import org.springframework.data.repository.CrudRepository;

import com.simple.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
