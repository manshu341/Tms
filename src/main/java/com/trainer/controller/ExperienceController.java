package com.trainer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trainer.beans.Experience;
import com.trainer.beans.Trainer;
import com.trainer.exception.ExperienceNotFoundException;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.service.ExperienceService;
/**
 * @author Kushal
 * @since February,2022
 * 
 */
@RestController
public class ExperienceController {

	@Autowired
	private ExperienceService service;

	/**
	 * @see Verifying weather service is live or not
	 * @return validation
	 */
	@GetMapping("/experience")
	public String sayExperience() {
		return "Experience......";
	}


	/**
	 * @see Add new experience to trainer by trainerId
	 * @param id -> trainerId
	 * @param experience
	 * @return trainer ResponseEntity
	 */
	@PutMapping("/experience/{id}")
	public ResponseEntity<Trainer> addSingleExperience(@PathVariable Integer id, @RequestBody Experience experience)
			throws TrainerNotFoundException {

		Trainer retTrainer = service.addSigleExperience(id, experience);
		return ResponseEntity.status(HttpStatus.CREATED).body(retTrainer);

	}

	/**
	 * @see Add new experience to trainer by trainerId
	 * @param id -> trainerId
	 * @param experience
	 * @return trainer ResponseEntity
	 */
	@PutMapping("/experiences/{id}")
	public ResponseEntity<Trainer> addMultipalToc(@PathVariable Integer id, @RequestBody List<Experience> experiences)
			throws TrainerNotFoundException {
		Trainer retTrainer = service.addMultipalExperiences(id, experiences);
		return ResponseEntity.status(HttpStatus.CREATED).body(retTrainer);

	}

	/**
	 * @see Get all experience
	 * @param id -> trainerId
	 * @return experienceList
	 * @throws ExperienceNotFoundException
	 * @throws TrainerNotFoundException
	 */
	@GetMapping("/experience/{id}")
	public List<Experience> getAllExperienceOfUserWithId(@PathVariable Integer id)
			throws ExperienceNotFoundException, TrainerNotFoundException {

		List<Experience> retExperience = service.getExperiences(id);
		if (retExperience == null) {
			throw new ExperienceNotFoundException("SOrry there is no Experience available for this Trainer " + id);
		}
		return retExperience;

	}

	/**
	 * @see Get experience by trainerId and experienceId
	 * @param id -> trainerId
	 * @param eid -> experienceId
	 * @return experience
	 * @throws ExperienceNotFoundException
	 * @throws TrainerNotFoundException
	 */
	@GetMapping("/experience/{id}/{eid}")
	public Experience getExperience(@PathVariable("id") Integer id, @PathVariable("eid") Integer eid)
			throws ExperienceNotFoundException, TrainerNotFoundException {

		Experience retExperience = service.getExperience(id, eid);
		if (retExperience == null) {
			throw new ExperienceNotFoundException("Sorry there is no Experience available for this Trainer " + id);
		}
		return retExperience;

	}

	/**
	 * @see delete experience by trainerId and experienceId
	 * @param tid -> trainerId
	 * @param eid -> experienceId
	 * @return experience deleted
	 * @throws ExperienceNotFoundException
	 * @throws TrainerNotFoundException
	 */
	@DeleteMapping("/experience/{tid}/{eid}")
	public String deleteExperience(@PathVariable Integer tid, @PathVariable Integer eid)
			throws ExperienceNotFoundException, TrainerNotFoundException {
		if ((service.getExperience(tid, eid)) != null) {
			service.deleteExperience(tid, eid);
		} else {
			throw new ExperienceNotFoundException("Sorry there is no Experience available for this T " + eid);
		}
		return "Experience Deleted";
	}

}
