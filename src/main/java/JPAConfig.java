/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unip.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 *
 * @author dirceu
 */
@Configuration
@EnableJpaRepositories("br.unip.repositorios")
@EnableTransactionManagement
@ComponentScan(basePackages = {"br.unip.modelos"})
@PropertySource("classpath:jpa.properties")
public class JPAConfig {


	@Autowired
	private Environment env;

	@Bean(destroyMethod = "close")
	public DataSource dataSource(Environment env) {

		HikariConfig dataSourceConfig = new HikariConfig();

		dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));

		dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));

		dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));

		dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));

		return new HikariDataSource(dataSourceConfig);

	}


	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("br.unip.repositorios","br.unip.modelos");

		Properties jpaProperties = new Properties();

		//Configures the used database dialect. This allows Hibernate to create SQL
		//that is optimized for the used database.
		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

		jpaProperties.put("hibernate.hbm2ddl.auto",
				env.getRequiredProperty("hibernate.hbm2ddl.auto")
				);

		jpaProperties.put("hibernate.ejb.naming_strategy",
				env.getRequiredProperty("hibernate.ejb.naming_strategy")
				);

		jpaProperties.put("hibernate.show_sql",
				env.getRequiredProperty("hibernate.show_sql")
				);

		jpaProperties.put("hibernate.format_sql",
				env.getRequiredProperty("hibernate.format_sql")
				);

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}