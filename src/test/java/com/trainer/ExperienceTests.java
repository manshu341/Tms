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

import com.trainer.beans.Experience;
import com.trainer.beans.Trainer;
import com.trainer.exception.ExperienceNotFoundException;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.repo.TrainerRepository;
import com.trainer.service.ExperienceService;

@SpringBootTest
class ExperienceTests {

	@Autowired
	private ExperienceService service;

	@MockBean
	private TrainerRepository repo;

	@BeforeEach
	public void setUp() {
		System.out.println("+++++++++-----------------------------------------------------------------+++++++++");
		Optional<Trainer> user = Optional
				.of(new Trainer(101, "Kushal", "pass", "kushal@probits.in", 931728121, null, null, null));
		List<Trainer> list = Arrays
				.asList(new Trainer(101, "Kushal", "pass", "kushal@probits.in", 931728121, null, null, null));

		// mocked object
		when(repo.findById(101)).thenReturn(user);
		when(repo.findAll()).thenReturn(list);
		when(repo.save(user.get())).thenReturn(user.get());
		when(repo.insert(user.get())).thenReturn(user.get());

	}

	@Test
	@DisplayName("Add single Experience Test")
	void addSingleExperience() throws TrainerNotFoundException {
		Experience experience = new Experience(1001, "Sapient", 30, "java");
		Trainer trainer = service.addSigleExperience(101, experience);
		List<Experience> experienceList = trainer.getExperienceList();
		assertEquals(1, experienceList.size());
	}

	@Test
	@DisplayName("Add Experience for invalid Trainer")
	void addSingleExperienceWithInvalidUser() throws TrainerNotFoundException {
		Experience experience = new Experience(1001, "Sapient", 30, "java");
		assertThrows(TrainerNotFoundException.class, () -> {
			service.addSigleExperience(109, experience);
		});
	}

	@Test
	@DisplayName("Add another single Experience Test")
	void addAnotherSingleExperience() throws TrainerNotFoundException {
		Experience exp1 = new Experience(1001, "Sapient", 30, "java");
		Experience exp2 = new Experience(1002, "Genpact", 30, "java");
		Trainer trainer = service.addSigleExperience(101, exp1);
		trainer = service.addSigleExperience(101, exp2);
		List<Experience> experienceList = trainer.getExperienceList();
		assertEquals(2, experienceList.size());
	}

	@Test
	@DisplayName("Get MultipleExperience for invalid User")
	void getMultipleExperienceWithInvalidUser() throws TrainerNotFoundException {
		assertThrows(TrainerNotFoundException.class, () -> {
			service.getExperiences(133);
		});
	}

	@Test
	@DisplayName("Get all Experiences when trainer not found ")
	void getAllExperiences() throws TrainerNotFoundException {
		List<Experience> experiencesList = service.getExperiences(101);
		assertNull(experiencesList);
	}

	@Test
	@DisplayName("Get all Experiences ")
	void getAllExperiencesAvailable() throws TrainerNotFoundException {

		List<Experience> experience = Arrays.asList(new Experience(11, "sapient", 30, "java"),
				new Experience(12, "genpact", 22, "java"));
		service.addMultipalExperiences(101, experience);
		List<Experience> experiencesList = service.getExperiences(101);
		assertEquals(2, experiencesList.size());
	}

	@Test
	@DisplayName("Add multiple Experience Test")
	void addMultipleExperiences() throws TrainerNotFoundException {
		Experience exp1 = new Experience(1001, "Sapient", 30, "java");
		Trainer trainer = service.addSigleExperience(101, exp1);
		List<Experience> experience = Arrays.asList(new Experience(11, "sapient", 30, "java"),
				new Experience(12, "genpact", 22, "java"));
		trainer = service.addMultipalExperiences(101, experience);
		List<Experience> experienceList = trainer.getExperienceList();
		assertEquals(3, experienceList.size());
	}

	@Test
	@DisplayName("Add multiple Experience with invalid trainerId Test")
	void addMultipleExperiencesWithInvalidUser() throws TrainerNotFoundException {
		List<Experience> experiences = Arrays.asList(new Experience(11, "sapient", 30, "java"),
				new Experience(12, "genpact", 22, "java"));
		assertThrows(TrainerNotFoundException.class, () -> {
			service.addMultipalExperiences(117, experiences);
		});
	}

	@Test
	@DisplayName("Add another multiple Experience Test")
	void addAnotherMultipleExperiences() throws TrainerNotFoundException {
		List<Experience> experience = Arrays.asList(new Experience(11, "sapient", 30, "java"),
				new Experience(12, "genpact", 22, "java"), new Experience(13, "db", 60, "java"));
		Trainer trainer = service.addMultipalExperiences(101, experience);
		List<Experience> experienceList = trainer.getExperienceList();
		assertEquals(3, experienceList.size());
	}

	@Test
	@DisplayName("Get Experience with invalid trainerId Test")
	void getExperienceWithInvalidTrainerId() throws ExperienceNotFoundException, TrainerNotFoundException {
		assertThrows(TrainerNotFoundException.class, () -> {
			service.getExperience(107, 11);
		});
	}

	@Test
	@DisplayName("Get Experience Test")
	void getSingleExperience() throws ExperienceNotFoundException, TrainerNotFoundException {
		List<Experience> experience = Arrays.asList(new Experience(11, "sapient", 30, "java"),
				new Experience(12, "genpact", 22, "java"), new Experience(13, "db", 60, "java"));
		service.addMultipalExperiences(101, experience);
		Experience exp1 = service.getExperience(101, 12);
		assertEquals(12, exp1.getId());
	}

	@Test
	@DisplayName("When no experience found for given expId")
	void noExperienceWithGivenExpId() throws ExperienceNotFoundException, TrainerNotFoundException {
		List<Experience> experience = Arrays.asList(new Experience(11, "sapient", 30, "java"),
				new Experience(13, "db", 60, "java"));
		service.addMultipalExperiences(101, experience);
		Experience exp1 = service.getExperience(101, 12);
		assertNull(exp1);
		;
	}

	@Test
	@DisplayName("Delete Single Experience Test")
	void deleteSingleExperience() throws ExperienceNotFoundException, TrainerNotFoundException {
		List<Experience> experience = Arrays.asList(new Experience(11, "sapient", 30, "java"),
				new Experience(12, "genpact", 22, "java"), new Experience(13, "db", 60, "java"));
		service.addMultipalExperiences(101, experience);
		service.deleteExperience(101, 11);
		List<Experience> experienceList = service.getExperiences(101);
		assertEquals(2, experienceList.size());

	}

	@Test
	@DisplayName("Delete Single Experience Test")
	void experienceIdNotFoundForDelete() throws ExperienceNotFoundException, TrainerNotFoundException {
		List<Experience> experience = Arrays.asList(new Experience(11, "sapient", 30, "java"),
				new Experience(12, "genpact", 22, "java"), new Experience(13, "db", 60, "java"));
		service.addMultipalExperiences(101, experience);
		service.deleteExperience(101, 14);
		List<Experience> experienceList = service.getExperiences(101);
		assertEquals(3, experienceList.size());

	}

	@Test
	@DisplayName("when trainer not found for experience")
	void deleteExperienceWithInvalidTId() throws TrainerNotFoundException {

		assertThrows(TrainerNotFoundException.class, () -> {
			service.deleteExperience(107, 11);
		});
	}

}