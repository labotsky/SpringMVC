package com.simple.repository;

import org.springframework.data.repository.CrudRepository;

import com.simple.entity.Book;

/**
 * 
 * @author Labotski_SV
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {

}
