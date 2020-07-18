package com.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.model.Person;

public interface PersonRepository<P> extends CrudRepository<Person, Long> {
	
	List<Person> findByFirstName(String firstName);
}
