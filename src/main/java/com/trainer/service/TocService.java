package com.trainer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainer.beans.Toc;
import com.trainer.beans.Trainer;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.repo.TrainerRepository;

/**
 * @author Yuvraj
 * @since February, 2022
 * 
 */
@Component
public class TocService {

	@Autowired
	private TrainerRepository trainerRepository;
	private String trainerNotFound = "Trainer not found id ";

	/**
	 * @see add single toc to db
	 * @param id -> trainerId
	 * @param toc -> trainerToc
	 * @return updated trainer
	 */
	public Trainer addSigleToc(Integer id, Toc toc) {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();

			List<Toc> testtoc = trainer.getToc();
			if (testtoc == null) {
				testtoc = Arrays.asList(toc);
				testtoc = new ArrayList<>(testtoc);
			} else {
				testtoc.add(toc);
			}
			trainer.setToc(testtoc);
			trainerRepository.save(trainer);
			return trainer;
		}
		return null;
	}

	/**
	 * @see add multiple toc to db
	 * @param id -> trainerId
	 * @param toc -> tocList
	 * @return trainer
	 * @throws TrainerNotFoundException
	 */
	public Trainer addMultipalToc(Integer id, List<Toc> toc) throws TrainerNotFoundException {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();

			List<Toc> testtoc = trainer.getToc();

			if (testtoc == null) {

				testtoc = new ArrayList<>(toc);
			} else {
				testtoc.addAll(toc);
			}
			trainer.setToc(testtoc);
			trainerRepository.save(trainer);
			return trainer;
		}
		throw new TrainerNotFoundException(trainerNotFound + id);
	}

	/**
	 * @see get toc by trainerId from db
	 * @param id -> tarinerId
	 * @return tocList
	 * @throws TrainerNotFoundException
	 */
	public List<Toc> getToc(Integer id) throws TrainerNotFoundException {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();

			List<Toc> testToc = trainer.getToc();
			if (testToc != null) {
				return testToc;
			}
			return null;
		}
		throw new TrainerNotFoundException(trainerNotFound + id);
	}

	/**
	 * @see get single toc by trainerId and tocId
	 * @param id -> trainerId
	 * @param tid -> tocId
	 * @return toc
	 * @throws TrainerNotFoundException
	 */
	public Toc singleToc(Integer id, Integer tid) throws TrainerNotFoundException {
		Optional<Trainer> byId = trainerRepository.findById(id);

		if (byId.isPresent()) {
			Trainer trainer = byId.get();
			List<Toc> testToc = trainer.getToc();
			if (testToc != null) {
				for (int i = 0; i < testToc.size(); i++) {
					if (Integer.toString(tid).equals(Integer.toString(testToc.get(i).getTid()))) {
						return testToc.get(i);
					}
				}
				return null;
			}
		}

		throw new TrainerNotFoundException(trainerNotFound + tid);
	}

	/**
	 * @see delete single toc by trainerId and tocId
	 * @param id -> trainerId
	 * @param tid -> tocId
	 * @throws TrainerNotFoundException
	 */
	public void deleteToc(Integer id, Integer tid) throws TrainerNotFoundException {
		Optional<Trainer> byId = trainerRepository.findById(id);
		if (byId.isPresent()) {
			Trainer trainer = byId.get();
			List<Toc> testToc = trainer.getToc();
			if (testToc != null) {
				for (int i = 0; i < testToc.size(); i++) {
					if (testToc.get(i).getTid() == tid) {
						testToc.remove(i);
						break;
					}
				}
			}
			trainer.setToc(testToc);
			trainerRepository.save(trainer);

		}else {
			throw new TrainerNotFoundException(trainerNotFound + id);
		}

	}

}
