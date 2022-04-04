package com.trainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.trainer.beans.Qualification;
import com.trainer.beans.Trainer;
import com.trainer.exception.QualificationNotFoundException;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.repo.TrainerRepository;
import com.trainer.service.QualificationsService;

@SpringBootTest
class QualificationTests {

	@Autowired
	private QualificationsService service;

	@MockBean
	private TrainerRepository repo;

	@BeforeEach
	public void setUp() {
		System.out.println("+++++++++-----------------------------------------------------------------+++++++++");
		Optional<Trainer> user = Optional
				.of(new Trainer(101, "Satya", "pass", "naveen@probits.in", 931728121, null, null, null));
		List<Trainer> list = Arrays
				.asList(new Trainer(101, "Satya", "pass", "naveen@probits.in", 931728121, null, null, null));

		// mocked object
		when(repo.findById(101)).thenReturn(user);
		when(repo.findAll()).thenReturn(list);
		when(repo.save(user.get())).thenReturn(user.get());
		when(repo.insert(user.get())).thenReturn(user.get());

	}

	@Test
	@DisplayName("Add single Qualification Test")
	void addSingleQualification() {
		Qualification qualification = new Qualification(1001, "PG", 2005, "CS", 7.9, "BHU");
		Trainer trainer = service.addSigleQualification(101, qualification);
		List<Qualification> qualificationList = trainer.getQualificationList();
		assertEquals(1, qualificationList.size());
	}

	@Test
	@DisplayName("Add Qualification for invalid User")
	void addSingleQualificationWithInvalidUser() {
		Qualification qualification = new Qualification(1001, "PG", 2005, "CS", 7.9, "BHU");
		Trainer trainer = service.addSigleQualification(109, qualification);
		assertNull(trainer);
	}

	@Test
	@DisplayName("Get all Qualifications ")
	void getAllQualification() throws TrainerNotFoundException {
		List<Qualification> QualificationList = service.getAllQualification(101);
		assertNull(QualificationList);

	}

	@Test
	@DisplayName("Get single Qualifications ")
	void getSingleQualification() throws TrainerNotFoundException, QualificationNotFoundException {
		Qualification qualification = service.getQualification(101, 205);
		assertNull(qualification);
	}

	@Test
	@DisplayName("Add multiple Qualification ")
	void addMultipleQualification() throws TrainerNotFoundException {
		List<Qualification> qualification = Arrays.asList(new Qualification(1001, "PG", 2005, "CS", 7.9, "BHU"),
				new Qualification(1002, "Diploma", 2002, "CS", 9.1, "NIT"));

		Trainer trainer = service.addMultipalQualification(101, qualification);
		List<Qualification> qualificationList = trainer.getQualificationList();
		assertEquals(2, qualificationList.size());
	}

	@Test
	@DisplayName("Add multiple Qualification for invalid User")
	void addMultipleQualificationWithInvalidUser() throws TrainerNotFoundException {
		List<Qualification> qualification = Arrays.asList(new Qualification(1001, "PG", 2005, "CS", 7.9, "BHU"),
				new Qualification(1002, "Diploma", 2002, "CS", 9.1, "NIT"));

		Trainer trainer = service.addMultipalQualification(108, qualification);
		assertNull(trainer);

	}

	@Test
	@DisplayName("Get Qualifiaction Test for invalid user")
	void getQualification() throws QualificationNotFoundException, TrainerNotFoundException {

		assertThrows(TrainerNotFoundException.class, () -> {
			service.getQualification(103, 201);
		});
	}

	@Test
	@DisplayName("Get Allqualification Test")
	void getAllQualificationexp() throws QualificationNotFoundException, TrainerNotFoundException {
		assertThrows(TrainerNotFoundException.class, () -> {
			service.getAllQualification(103);
		});
	}

	@Test
	@DisplayName("When no Qualification found for given expId")
	void noQualificationWithGivenExpId() throws QualificationNotFoundException, TrainerNotFoundException {
		List<Qualification> qualification = Arrays.asList(new Qualification(1001, "PG", 2005, "CS", 7.9, "BHU"),
				new Qualification(1002, "Diploma", 2002, "CS", 9.1, "NIT"));

		service.addMultipalQualification(101, qualification);
		Qualification quaf = service.getQualification(101, 12);
		assertNull(quaf);
	}

}