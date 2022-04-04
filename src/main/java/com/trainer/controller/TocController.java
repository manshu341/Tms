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

import com.trainer.beans.Toc;
import com.trainer.beans.Trainer;
import com.trainer.exception.TocNotFoundException;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.service.TocService;

/**
 * 
 * @author Yuvraj
 * @since February, 2022
 *
 */

@RestController
public class TocController {

	@Autowired
	private TocService service;

	/**
	 * @see Verifying weather service is live or not
	 * @return validation
	 */
	@GetMapping("/toc")
	public String sayToc() {
		return "tocc is working";
	}

	/**
	 * @see Add Single toc to existing trainer
	 * @param id -> trainerId
	 * @param toc -> trainer Toc
	 * @return trainer
	 */
	@PutMapping("/toc/{id}")
	public ResponseEntity<Trainer> addSingleToc(@PathVariable Integer id, @RequestBody Toc toc) {

		Trainer retTrainer = service.addSigleToc(id, toc);
		return ResponseEntity.status(HttpStatus.CREATED).body(retTrainer);

	}

	/**
	 * @see Add multuiple Toc to existing trainer 
	 * @param id -> trainerId
	 * @param toc -> trainer Toc
	 * @return trainer
	 * @throws TrainerNotFoundException
	 */
	@PutMapping("/toces/{id}")
	public ResponseEntity<Trainer> addMultipalToc(@PathVariable Integer id, @RequestBody List<Toc> toc) throws TrainerNotFoundException {

		Trainer retTrainer = service.addMultipalToc(id, toc);
		return ResponseEntity.status(HttpStatus.CREATED).body(retTrainer);

	}
	
	/**
	 * @see get all the toc list by trainerId
	 * @param id -> trainerId
	 * @return toc
	 * @throws TocNotFoundException
	 * @throws TrainerNotFoundException
	 */
	@GetMapping("/toc/{id}")
	public List<Toc> getAllTocWithId(@PathVariable Integer id) throws TocNotFoundException, TrainerNotFoundException {

		List<Toc> retToc = service.getToc(id);
		if (retToc == null) {
			throw new TocNotFoundException("SOrry there is no Toc available for this Trainer " + id);
		}
		return retToc;

	}

	/**
	 * @see get single toc by trainerId and tocId
	 * @param id -> trainerId
	 * @param tid -> tocId
	 * @return toc
	 * @throws TocNotFoundException
	 * @throws TrainerNotFoundException
	 */
	@GetMapping("/toc/{id}/{tid}")
	public Toc singleToc(@PathVariable Integer id, @PathVariable Integer tid)
			throws TocNotFoundException, TrainerNotFoundException {
		if ((service.getToc(id)) != null) {
			return service.singleToc(id, tid);
		} else {
			throw new TocNotFoundException("Sorry there is no Toc available for this T " + tid);
		}
	}

	/**
	 * @see delete toc by trainerId and tocId
	 * @param id -> tarinerId
	 * @param tid -> toId
	 * @return toc Successfully deleted
	 * @throws TocNotFoundException
	 * @throws TrainerNotFoundException
	 */
	@DeleteMapping("/toc/{id}/{tid}")
	public String deleteToc(@PathVariable Integer id, @PathVariable Integer tid)
			throws TocNotFoundException, TrainerNotFoundException {
		if (service.getToc(id) != null) {
			service.deleteToc(id, tid);
		} else {
			throw new TocNotFoundException("Sorry there is no Toc available for this T " + tid);
		}
		return "Toc Deleted";
	}
}
