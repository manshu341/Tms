package com.trainer.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.trainer.repo.TrainerRepository;

@Configurable
@EnableMongoRepositories(basePackageClasses = TrainerRepository.class)
public class MongoConfig {

	@Bean
	public CommandLineRunner commandLineRunner(TrainerRepository userRepo) {
		return null;
	}

}
