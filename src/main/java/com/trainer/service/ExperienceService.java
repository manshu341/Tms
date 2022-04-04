package com.trainer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainer.beans.Experience;
import com.trainer.beans.Trainer;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.repo.TrainerRepository;

/** 
 * @author Kushal
 * @since February, 2022
 * @see Experience Services
 */
@Component
public class ExperienceService {

	@Autowired
	private TrainerRepository trainerRepository;

	private String trainerNotFound = "Trainer not found id ";

	/**
	 * @see save trainer experience data in db 
	 * @param id -> trainerId
	 * @param experience
	 * @return trainer
	 * @throws TrainerNotFoundException
	 */
	public Trainer addSigleExperience(Integer id, Experience experience) throws TrainerNotFoundException {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();

			List<Experience> testExperience = trainer.getExperienceList();

			if (testExperience == null) {
				testExperience = Arrays.asList(experience);
				testExperience = new ArrayList<>(testExperience);
			} else {
				testExperience.add(experience);
			}
			trainer.setExperienceList(testExperience);
			trainerRepository.save(trainer);
			return trainer;
		}

		throw new TrainerNotFoundException(trainerNotFound + id);
	}

	/**
	 * @see get multiple experiences data by trainerId
	 * @param id -> tarinerId
	 * @param experiences
	 * @return trainer
	 * @throws TrainerNotFoundException
	 */
	public Trainer addMultipalExperiences(Integer id, List<Experience> experiences) throws TrainerNotFoundException {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();

			List<Experience> testtoc = trainer.getExperienceList();

			if (testtoc == null) {
				testtoc = new ArrayList<>(experiences);
			} else {
				testtoc.addAll(experiences);
			}
			trainer.setExperienceList(testtoc);
			trainerRepository.save(trainer);
			return trainer;
		}

		throw new TrainerNotFoundException(trainerNotFound + id); 
	}

	/**
	 * @see get all tariner experiences data by trainerId
	 * @param id -> trainerId
	 * @return experienceList
	 * @throws TrainerNotFoundException
	 */
	public List<Experience> getExperiences(Integer id) throws TrainerNotFoundException {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();

			List<Experience> testExperience = trainer.getExperienceList();
			if (testExperience != null) {
				return testExperience;
			}
			return null;
		} else
			throw new TrainerNotFoundException(trainerNotFound + id);
	}

	/**
	 * @see get experience by trainerId and experienceId
	 * @param tid -> trainerId
	 * @param eid -> experienceId
	 * @return experience
	 * @throws TrainerNotFoundException
	 */
	public Experience getExperience(Integer tid, Integer eid) throws TrainerNotFoundException {

		Optional<Trainer> byId = trainerRepository.findById(tid);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();
			List<Experience> testExperience = trainer.getExperienceList();
			if (testExperience != null) {
				for (int i = 0; i < testExperience.size(); i++) {
					if (Integer.toString(eid).equals(Integer.toString(testExperience.get(i).getId()))) {
						return testExperience.get(i);
					}

				}
				return null;
			}

		}

		throw new TrainerNotFoundException(trainerNotFound + tid);

	}

	/**
	 * @see delete experience from db by trainerId and experienceId
	 * @param id -> trainerId
	 * @param eid -> experienceId
	 * @throws TrainerNotFoundException
	 */
	public void deleteExperience(Integer id, Integer eid) throws TrainerNotFoundException {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();
			List<Experience> testExperience = trainer.getExperienceList();
			if (testExperience != null) {
				for (int i = 0; i < testExperience.size(); i++) {
					if (testExperience.get(i).getId() == eid) {

						testExperience.remove(i);
						break;
					}
				}
			}
			trainer.setExperienceList(testExperience);
			trainerRepository.save(trainer);

		} else {
			throw new TrainerNotFoundException(trainerNotFound + id);
		}

	}

}
