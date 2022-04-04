package com.trainer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.trainer.beans.Trainer;
import com.trainer.repo.TrainerRepository;

/**
 * @author Satyaa
 * @see Trainer Services
 * @since February, 2022
 */
@Service
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;

	/**
	 * @see Save trainer data in db
	 * @param trainer -> Trainer Data
	 * @return trainer
	 */
	public Trainer saveTrainer(Trainer trainer) {
		trainerRepository.insert(trainer);
		return trainer;
	}

	/**
	 * @see Update trainer data for existing trainer in db
	 * @param trainer
	 * @return trainer
	 */
	public Trainer updateTrainer(Trainer trainer) {
		trainerRepository.save(trainer);
		return trainer;
	}

	/**
	 * @see Update trainer name by id in db
	 * @param id   -> trainerId
	 * @param name -> new trainer name
	 * @return trainer data
	 */
	public Trainer updateTrainerNameById(Integer id, String name) {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();
			trainer.setName(name);

			trainerRepository.save(trainer);
			return trainer;
		}
		return null;
	}

	/**
	 * @see get trainer data by there id
	 * @param id -> trainerId
	 * @return trainer data
	 */
	public Trainer getTrainerByIdAsObject(Integer id) {
		Optional<Trainer> byId = trainerRepository.findById(id);
		return byId.isPresent() ? byId.get() : null;
	}

	/**
	 * @see Get all trainers data available in db
	 * @return list of all trainers
	 */
	public List<Trainer> getAllTrainers() {
		return trainerRepository.findAll();
	}

	/**
	 * @see get list of trainers based on there name
	 * @param name -> trainerName
	 * @return list of trainers
	 */
	public List<Trainer> getTrainerByName(String name) {
		return trainerRepository.findByName(name);
	}

	/**
	 * @see Delete trainer based on there id
	 * @param id -> trainerId
	 */
	public void deleteTrainer(Integer id) {
		trainerRepository.deleteById(id);
	}

}
