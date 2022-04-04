package com.trainer.controller;

/**
 * @author Satyaa
 * @see Trainer Controller -> TrainerEndpoints
 * @since February, 2022
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Add;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trainer.beans.Trainer;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.service.TrainerService;

@RestController
public class TrainerController {

	@Autowired
	private TrainerService service;

	/**
	 * @see Verifying weather service is live or not
	 * @return validation
	 */
	@GetMapping("/health")
	public String sayHello() {
		return "Service is up...";
	}

	/**
	 * @see Get list of all available Trainers endpoint
	 * @return
	 */
	@GetMapping("/trainer")
	public List<Trainer> getTrainers() {
		return service.getAllTrainers();
	}

	/**
	 * @see Get trainer detailes based on trainerId endpoint
	 * @param id -> trainerId
	 * @return trainer
	 * @throws TrainerNotFoundException
	 */
	@GetMapping("/trainer/{id}")
	public Trainer getTrainers(@PathVariable Integer id) throws TrainerNotFoundException {
		Trainer retTrainer = service.getTrainerByIdAsObject(id);
		if (retTrainer == null) {
			throw new TrainerNotFoundException("Sorry Trainer not found");
		}
		return retTrainer;
	}

	/**
	 * @see Add new trainer endpoint
	 * @param trainer
	 * @return trainer ResponseEntity
	 */
	@PostMapping("/trainer")
	public ResponseEntity<Trainer> saveTrainer(@RequestBody Trainer trainer) {
		Trainer retTrainer = service.saveTrainer(trainer);
		return ResponseEntity.status(HttpStatus.CREATED).body(retTrainer);

	}

	/**
	 * @see update existing trainer endpoint
	 * @param trainer
	 * @return trainer ResponseEntity
	 */
	@PutMapping("/trainer")
	public ResponseEntity<Trainer> updateTrainer(@RequestBody Trainer trainer) {
		Trainer retTrainer = service.updateTrainer(trainer);
		return ResponseEntity.status(HttpStatus.CREATED).body(retTrainer);
	}

	/**
	 * @see update trainer name based on their id endpoint
	 * @param id   -> trainerId
	 * @param name -> trainer new name
	 * @return trainer ResponseEntity
	 */
	@PutMapping("/trainer/{id}/{name}")
	public ResponseEntity<Trainer> updateNameById(@PathVariable Integer id, @PathVariable String name) {
		Trainer retTrainer = service.updateTrainerNameById(id, name);
		return ResponseEntity.status(HttpStatus.CREATED).body(retTrainer);
	}

	/**
	 * @see delete trainer data endpoint
	 * @param id
	 * @return validation whether trainer is deleted or not
	 * @throws TrainerNotFoundException
	 */
	@DeleteMapping("/delete/{id}")
	public String deleteTrainer(@PathVariable Integer id) throws TrainerNotFoundException {
		Trainer retTrainer = service.getTrainerByIdAsObject(id);
		if (retTrainer == null) {
			throw new TrainerNotFoundException("Sorry Trainer not found");
		}
		service.deleteTrainer(id);
		return "Trainer Deleted";
	}

}
