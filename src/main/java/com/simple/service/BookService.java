package com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.simple.entity.Book;
import com.simple.repository.BookRepository;

/**
 * 
 * @author Labotski_SV
 *
 */
@Service
public class BookService implements RESTService<Book, Long> {

	@Autowired
	BookRepository bookRepository;

	public Book findById(Long id) {
		return bookRepository.findOne(id);
	}

	@Override
	public List<Book> findAll() {
		return Lists.newArrayList(bookRepository.findAll());
	}

	@Override
	public void deleteById(Long id) {
		bookRepository.delete(id);
	}

	@Override
	public Book save(Book entity) {
		return bookRepository.save(entity);
	}
}
