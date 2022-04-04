package com.team3.employee.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.team3.employee.repo.EmployeeReposatory;

@Configurable
@EnableMongoRepositories(basePackageClasses = EmployeeReposatory.class)
public class MongoConfig {

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeReposatory userRepo) {
		return null;
	}

}
