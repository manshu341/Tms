package com.trainer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainer.beans.Qualification;
import com.trainer.beans.Trainer;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.repo.TrainerRepository;

/**
 * @author Ashish
 * @see Qualification Services
 * @since February, 2022
 */
@Component
public class QualificationsService {

	@Autowired
	private TrainerRepository trainerRepository;

	/**
	 * @see save trainer qualification data in db 
	 * @param id -> tarinerId
	 * @param qualification
	 * @return trainer
	 */
	public Trainer addSigleQualification(Integer id, Qualification qualification) {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();

			List<Qualification> qualist = trainer.getQualificationList();

			if (qualist == null) {
				qualist = Arrays.asList(qualification);
				qualist = new ArrayList<>(qualist);
			} else {
				qualist.add(qualification);
			}
			trainer.setQualificationList(qualist);
			trainerRepository.save(trainer);
			return trainer;
		}
		return null;
	}

	/**
	 * @see get all tariner qualifications data by trainerId
	 * @param id -> tarinerId
	 * @return qualificationList
	 * @throws TrainerNotFoundException
	 */
	public List<Qualification> getAllQualification(Integer id) throws TrainerNotFoundException {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();

			List<Qualification> qualist = trainer.getQualificationList();
			if (qualist != null) {
				return qualist;
			}
			return null;
		}
		throw new TrainerNotFoundException("Trainer not found id " + id);
	}

	/**
	 * @see get qualification by trainerId and qualificationId
	 * @param tid -> trainerId
	 * @param qid -> qualificationId
	 * @return qualification
	 * @throws TrainerNotFoundException
	 */
	public Qualification getQualification(Integer tid, Integer qid) throws TrainerNotFoundException {

		Optional<Trainer> byId = trainerRepository.findById(tid);

		if (byId.isPresent()) {
			Trainer trainer = byId.get();
			List<Qualification> testQualifications = trainer.getQualificationList();
			if (testQualifications != null) {
				for (int i = 0; i < testQualifications.size(); i++) {
					if (Integer.toString(qid).equals(Integer.toString(testQualifications.get(i).getId()))) {
						return testQualifications.get(i);
					}

				}
			}

		} else {
			throw new TrainerNotFoundException("Trainer not found id " + tid);
		}
		return null;
	}

	/**
	 * @see Add multiple qualification by trainerId
	 * @param id -> trainerId
	 * @param qualifications
	 * @return trainer
	 */
	public Trainer addMultipalQualification(Integer id, List<Qualification> qualifications) {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();

			List<Qualification> testQualifications = trainer.getQualificationList();
			if (testQualifications == null) {
				testQualifications = new ArrayList<>(qualifications);
			} else {
				testQualifications.addAll(qualifications);
			}
			trainer.setQualificationList(testQualifications);
			trainerRepository.save(trainer);
			return trainer;
		}
		return null;
	}

	/**
	 * @see Delete qualification by trainerId and qualificationId
	 * @param id -> trainerId
	 * @param qid -> qualificationId
	 */
	public void deleteQualification(Integer id, Integer qid) {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();
			List<Qualification> testQualifications = trainer.getQualificationList();
			if (testQualifications != null) {
				for (int i = 0; i < testQualifications.size(); i++) {
					if (Integer.toString(qid).equals(Integer.toString(testQualifications.get(i).getId()))) {
						testQualifications.remove(i);
						break;
					}
				}
			}
			trainer.setQualificationList(testQualifications);
			trainerRepository.save(trainer);

		}

	}
}
