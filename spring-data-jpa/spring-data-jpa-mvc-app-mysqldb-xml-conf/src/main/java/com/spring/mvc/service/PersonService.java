package com.spring.mvc.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.model.Person;
import com.spring.mvc.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository<Person> personRepository;

	@Transactional
	public List<Person> getAllPersons() {
		return (List<Person>) personRepository.findAll();
	}
	
	@Transactional
	public List<Person> findByName(String name) {
		return personRepository.findByFirstName(name);
	}

	@Transactional
	public Optional<Person> getById(Long id) {
		return personRepository.findById(id);
	}

	@Transactional
	public void deletePerson(Long personId) {
		personRepository.deleteById(personId);
	}

	@Transactional
	public boolean addPerson(Person person) {
		return Objects.nonNull(personRepository.save(person));
	}

	@Transactional
	public boolean updatePerson(Person person) {
		return Objects.nonNull(personRepository.save(person));
	}
}
