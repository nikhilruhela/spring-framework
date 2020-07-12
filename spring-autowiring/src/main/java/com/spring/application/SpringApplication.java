package com.spring.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.beans.Employee;

public class SpringApplication {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-beans-autowiring.xml");

		/**
		 * Use this employee creation, when using other spring bean configuration file except (spring-beans-annotation-di)
		 */
		Employee employee = applicationContext.getBean("employee", Employee.class);

		/**
		 * Use this employee creation, when using spring bean configuration file (spring-beans-annotation-di)
		 */
		//com.spring.beans.autowiredbeans.Employee employee = applicationContext.getBean("employee", com.spring.beans.autowiredbeans.Employee.class);
		System.out.println(employee);

		((ConfigurableApplicationContext)applicationContext).close();
	}
}

/**
 * spring-beans.xml
 * spring-beans-cons-di.xml
 * spring-beans-autowiring.xml
 * spring-beans-annotation-di.xml
 */
