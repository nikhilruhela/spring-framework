package com.spring.mvc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.mvc.model.Person;

public interface PersonRepository<P> extends CrudRepository<Person, Long> {
	
	List<Person> findByFirstName(String firstName);
}
