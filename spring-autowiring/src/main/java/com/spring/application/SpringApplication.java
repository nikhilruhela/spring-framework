package com.spring.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.beans.Employee;

public class SpringApplication {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-beans-autowiring.xml");

		Employee employee = applicationContext.getBean("employee", Employee.class);
		System.out.println(employee);

		((ConfigurableApplicationContext)applicationContext).close();
	}
}

/**
 * spring-beans.xml
 * spring-beans-cons-di.xml
 * spring-beans-autowiring.xml
 */
