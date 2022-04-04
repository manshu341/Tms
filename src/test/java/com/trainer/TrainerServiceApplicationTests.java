package com.trainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.trainer.beans.Trainer;
import com.trainer.repo.TrainerRepository;
import com.trainer.service.TrainerService;

@SpringBootTest
class TrainerServiceApplicationTests {

	@Autowired
	private TrainerService service;

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
	@DisplayName("When Trainer id is given return valid object")
	void trainerValidReturn() {
		assertEquals(101, service.getTrainerByIdAsObject(101).getId());
	}

	@Test
	@DisplayName("get all the Trainers and list should give you an array of 1 user")
	void getAllTrainers() {
		assertEquals(1, service.getAllTrainers().size());
	}

	@Test
	@DisplayName("get Trainers by there name")
	void getTrainerByName() {
		List<Trainer> trainerByName = service.getTrainerByName("Satya");
		assertNotNull(trainerByName);
	}

	@Test
	@DisplayName("save the valid Trainer and acknowledge the same")
	void saveTrainerToTheServer() {
		Trainer trainer = new Trainer(102, "Trainer 2", "pass", "Trainer@gmail.in", 73239712, null, null, null);

		assertNotNull(service.saveTrainer(trainer));
	}

	@Test
	@DisplayName("update Trainer data")
	void updateTrainerToTheServer() {
		Trainer trainer = new Trainer(101, "Updated name", "pass", "Trainer@gmail.in", 73239712, null, null, null);
		Trainer updateTrainer = service.updateTrainer(trainer);
		assertNotNull(updateTrainer);
	}

	@Test
	@DisplayName("Update trainer name by ID")
	void updateTrainerNameById() {
		String name = "New Name";
		Trainer retTrainer = service.updateTrainerNameById(101, name);
		assertEquals(name, retTrainer.getName());
	}

	@Test
	@DisplayName("Update trainer by name but invalid trainer ")
	void updateTrainerButTrainerNotFound() {
		assertNull(service.updateTrainerNameById(91, "New Name"));
	}

}
