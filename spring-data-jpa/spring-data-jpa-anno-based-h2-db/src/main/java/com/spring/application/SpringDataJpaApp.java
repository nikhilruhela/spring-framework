package com.spring.application;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.config.DataConfig;
import com.spring.model.Person;
import com.spring.service.PersonService;

public class SpringDataJpaApp {
	private static PersonService personService;
	
	public static void main(String[] args)
	{	
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataConfig.class);

		personService = applicationContext.getBean(PersonService.class);
		
		Person person1 = createPerson("Gavin", "King", 28);
		Person person2 = createPerson("Narendra", "Modi Ji", 26);
		Person person3 = createPerson("Mark", "Zukerberg", 20);
		Person person4 = createPerson("Sundar", "Pichai", 48);

		/**
		 * Persist all persons
		 */
		persistPersonObjects(Arrays.asList(person1, person2, person3, person4));
		
		/**
		 * Read All Persons
		 */
		List<Person> persons = personService.getAllPersons();
		if (Objects.nonNull(persons) && !persons.isEmpty())
		{
			persons.forEach(System.out::println);
		}
		
		((ConfigurableApplicationContext)applicationContext).close();
	}
	
	private static void persistPersonObjects(List<Person> persons)
	{
		if (Objects.nonNull(persons) && !persons.isEmpty())
		{
			persons.forEach(person -> {
				persistPerson(person);
			});
			
			System.out.println("Data Persisted Successfully!");
		}
	}

	private static void persistPerson(Person person)
	{
		personService.addPerson(person);
	}

	private static Person createPerson(String firstName, String lastName, Integer age)
	{
		return new Person(firstName, lastName, age);
	}
}
