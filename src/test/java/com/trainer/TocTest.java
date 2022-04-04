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

import com.trainer.beans.Toc;
import com.trainer.beans.Trainer;
import com.trainer.exception.TocNotFoundException;
import com.trainer.exception.TrainerNotFoundException;
import com.trainer.repo.TrainerRepository;
import com.trainer.service.TocService;

@SpringBootTest
class TocTest {

	@Autowired
	private TocService service;

	@MockBean
	private TrainerRepository repo;

	@BeforeEach
	public void setUp() {
		System.out.println("+++++++++-----------------------------------------------------------------+++++++++");
		Optional<Trainer> user = Optional
				.of(new Trainer(101, "yuvraj", "pass", "yuvraj@gmail.com", 931728121, null, null, null));
		List<Trainer> list = Arrays
				.asList(new Trainer(101, "yuvraj", "pass", "yuvraj@gmail.com", 931728121, null, null, null));

		// mocked object
		when(repo.findById(101)).thenReturn(user);
		when(repo.findAll()).thenReturn(list);
		when(repo.save(user.get())).thenReturn(user.get());
		when(repo.insert(user.get())).thenReturn(user.get());

	}

	@Test
	@DisplayName("Add single Toc Test")
	void addSingleToc() {
		Toc toc = new Toc(1001, "Toc1", 45, "freshers");
		Trainer trainer = service.addSigleToc(101, toc);
		List<Toc> tocList = trainer.getToc();
		assertEquals(1, tocList.size());
	}

	@Test
	@DisplayName("Add Toc for invalid User")
	void addSingleTocWithInvalidUser() {
		Toc toc = new Toc(1001, "Toc 1", 60, "freshers");
		Trainer trainer = service.addSigleToc(109, toc);
		assertNull(trainer);
	}

	@Test
	@DisplayName("Add multiple Toc Test")
	void addMultipleTocs() throws TrainerNotFoundException {
		Toc toc1 = new Toc(1, "Sapient", 30, "java");
		Trainer trainer = service.addSigleToc(101, toc1);
		List<Toc> Toc = Arrays.asList(new Toc(11, "sapient", 30, "java"), new Toc(12, "genpact", 22, "java"));
		trainer = service.addMultipalToc(101, Toc);
		List<Toc> TocList = trainer.getToc();
		assertEquals(3, TocList.size());
	}

	@Test
	@DisplayName("Add multiple Toc with invalid trainerId Test")
	void addMultipleTocsWithInvalidUser() throws TrainerNotFoundException {

		List<Toc> tocs = Arrays.asList(new Toc(11, "sapient", 30, "java"), new Toc(12, "genpact", 22, "java"));
		assertThrows(TrainerNotFoundException.class, () -> {
			service.addMultipalToc(117, tocs);
		});

	}

	@Test
	@DisplayName("Get MultipleToc for invalid User")
	void getMultipleExperienceWithInvalidUser() throws TrainerNotFoundException {
		assertThrows(TrainerNotFoundException.class, () -> {
			service.getToc(133);
		});
	}

	@Test
	@DisplayName("Get Toc Test")
	void getSingleToc() throws TocNotFoundException, TrainerNotFoundException {
		List<Toc> toc = Arrays.asList(new Toc(11, "sapient", 30, "java"), new Toc(12, "genpact", 22, "java"),
				new Toc(13, "db", 60, "java"));
		service.addMultipalToc(101, toc);
		Toc toc1 = service.singleToc(101, 12);
		assertEquals(12, toc1.getTid());
	}

	@Test
	@DisplayName("Add another multiple Toc Test")
	void addAnotherMultipleTocs() throws TrainerNotFoundException {
		List<Toc> Toc = Arrays.asList(new Toc(11, "sapient", 30, "java"), new Toc(12, "genpact", 22, "java"),
				new Toc(13, "db", 60, "java"));
		Trainer trainer = service.addMultipalToc(101, Toc);
		List<Toc> TocList = trainer.getToc();
		assertEquals(3, TocList.size());
	}

	@Test
	@DisplayName("Get all Tocies ")
	void getAllToc() throws TrainerNotFoundException {
		List<Toc> tocList = service.getToc(101);
		assertNull(tocList);
	}

	@Test
	@DisplayName("Get Toc Test")
	void getToc() throws TocNotFoundException, TrainerNotFoundException {
		assertThrows(TrainerNotFoundException.class, () -> {
			service.singleToc(12, 32);
		});

	}
}