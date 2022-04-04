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

import com.trainer.beans.Qualification;
import com.trainer.beans.Trainer;
import com.trainer.exception.QualificationNotFoundException;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.service.QualificationsService;
/**
 * 
 * @author Ashish
 * @since February, 2022
 */
@RestController
public class QualificationController {

	@Autowired
	private QualificationsService service;

	/**
	 * @see Verifying weather service is live or not
	 * @return validation
	 */
	@GetMapping("/qualification")
	public String sayQualifications() {
		return "qualifications is working";
	}

	/**
	 * @see Add new qualification to trainer by trainerId
	 * @param id -> trainerId
	 * @param qualification
	 * @return trainer ResponseEntity
	 */
	@PutMapping("/qualification/{id}")
	public ResponseEntity<Trainer> addSingleQualification(@PathVariable Integer id,
			@RequestBody Qualification qualification) {

		Trainer retTrainer = service.addSigleQualification(id, qualification);
		return ResponseEntity.status(HttpStatus.CREATED).body(retTrainer);

	}

	/**
	 * @see Get all qualifications
	 * @param id -> trainerId
	 * @return qualificationList
	 * @throws QualificationNotFoundException
	 * @throws TrainerNotFoundException
	 */
	@GetMapping("/qualification/{id}")
	public List<Qualification> getAllQualification(@PathVariable Integer id)
			throws QualificationNotFoundException, TrainerNotFoundException {

		List<Qualification> retQua = service.getAllQualification(id);
		if (retQua == null) {
			throw new QualificationNotFoundException(
					"Sorry there is no Qualification available for this Trainer " + id);
		}
		return retQua;

	}

	/**
	 * @see Add multiple qualifications to trainer by trainerId and qualification
	 * @param id -> trainerId
	 * @param qualifications
	 * @return trainer ResponseEntity
	 */
	@PutMapping("/qualifications/{id}")
	public ResponseEntity<Trainer> addMultipalQualification(@PathVariable Integer id,
			@RequestBody List<Qualification> qualifications) {
		Trainer retTrainer = service.addMultipalQualification(id, qualifications);
		return ResponseEntity.status(HttpStatus.CREATED).body(retTrainer);

	}

	/**
	 * @see get qualification by trainerId and qualificationId
	 * @param id -> trainerId
	 * @param qid -> qualificationId
	 * @return qualification
	 * @throws QualificationNotFoundException
	 * @throws TrainerNotFoundException
	 */
	@GetMapping("/qualification/{id}/{qid}")
	public Qualification getQualification(@PathVariable("id") Integer id, @PathVariable("qid") Integer qid)
			throws QualificationNotFoundException, TrainerNotFoundException {

		Qualification retQualification = service.getQualification(id, qid);
		if (retQualification == null) {
			throw new QualificationNotFoundException("Sorry there is no Qualification available for this Trainer " + id);
		}
		return retQualification;

	}

	/**
	 * @see delete qualification by tarinerId and qualificationId
	 * @param tid -> trainerId 
	 * @param qid -> qualificationId
	 * @return qualification deleted
	 * @throws QualificationNotFoundException
	 * @throws TrainerNotFoundException
	 */
	@DeleteMapping("/qualification/{tid}/{qid}")
	public String deleteQualification(@PathVariable Integer tid, @PathVariable Integer qid)
			throws QualificationNotFoundException, TrainerNotFoundException {
		if ((service.getQualification(tid, qid)) != null) {
			service.deleteQualification(tid, qid);
		} else {
			throw new QualificationNotFoundException("Sorry there is no Qualiication available for this T " + qid);
		}
		return "Qualification Deleted";
	}

}
