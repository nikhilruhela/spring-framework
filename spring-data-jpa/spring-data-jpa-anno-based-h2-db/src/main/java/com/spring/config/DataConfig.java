package com.spring.config;

import java.util.Properties;

import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.spring.repository")
@PropertySource("classpath:database.properties")
@ComponentScan(basePackages = "com.spring.service")
public class DataConfig {

	private final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
	private final String PROPERTY_DIALECT = "hibernate.dialect";

	@Autowired
	Environment environment;

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
		lfb.setDataSource(h2TestDataSource());
		lfb.setPersistenceProvider(persistenceProvider());
		lfb.setPackagesToScan("com.spring.model");
		lfb.setJpaProperties(hibernateProps());
		return lfb;
	}

	@Bean
	public PersistenceProvider persistenceProvider()
	{
		return new HibernatePersistenceProvider();
	}

	@Bean
	public DataSource h2TestDataSource(){
	   EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
			           .setType(EmbeddedDatabaseType.H2)
			           .addScript("scripts/schema.sql")
                       /**
                        * Add this script if you need to insert some test data in H2
                        * 
                        * .addScript("scripts/data.sql")
                        */
			           .build();

	   /**
	     This piece of code generates schema and insert data in embedded database, but as such
	     not need because if we add scripts file directly to EmbeddedDatabaseBuilder, it will
	     automatically use those script files to generate schema and insert data in embedded
	     H2 database.

	     Resource initSchema = new ClassPathResource("scripts/schema.sql");
	     Resource initData = new ClassPathResource("scripts/data.sql");
 	     ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
	     resourceDatabasePopulator.addScript(initSchema);
	     //resourceDatabasePopulator.addScript(initData);
	     DatabasePopulatorUtils.execute(resourceDatabasePopulator, dataSource);
	    */
	    
	   return dataSource;
	}

	Properties hibernateProps() {
		Properties properties = new Properties();
		properties.setProperty(PROPERTY_DIALECT, environment.getProperty(PROPERTY_DIALECT));
		properties.setProperty(PROPERTY_SHOW_SQL, environment.getProperty(PROPERTY_SHOW_SQL));
		return properties;
	}

	@Bean
	JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
