package com.trainer.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trainer.beans.Trainer;

@Repository
public interface TrainerRepository extends MongoRepository<Trainer, Integer> {

	public List<Trainer> findByName(String name);

}
