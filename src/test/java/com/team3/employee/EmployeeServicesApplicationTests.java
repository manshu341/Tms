package com.team3.employee;

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

import com.team3.employee.beans.Employee;
import com.team3.employee.repo.EmployeeReposatory;
import com.team3.employee.services.EmployeeServices;

@SpringBootTest
class EmployeeServicesApplicationTests {

	@Autowired
	private EmployeeServices service;

	@MockBean
	private EmployeeReposatory repo;

	@BeforeEach
	public void setUp() {
		System.out.println("+++++++++-----------------------------------------------------------------+++++++++");
		Optional<Employee> user = Optional
				.of(new Employee(101, "Satya", "pass", "satyaa@gmail.com", 931728121));
		List<Employee> list = Arrays
				.asList(new Employee(101, "Satya", "pass", "satyaa@gmail.com", 931728121));

		// mocked object
		when(repo.findById(101)).thenReturn(user);
		when(repo.findAll()).thenReturn(list);
		when(repo.save(user.get())).thenReturn(user.get());
		when(repo.insert(user.get())).thenReturn(user.get());

	}

	@Test
	@DisplayName("When Employee id is given return valid object")
	void employeeValidReturn() {
		assertEquals(101, service.getEmployeeByIdAsObject(101).getId());
	}

	@Test
	@DisplayName("get all the Employee and list should give you an array of 1 user")
	void getAllEmployee() {
		assertEquals(1, service.getAllEmployees().size());
	}

	@Test
	@DisplayName("get Employee by there name")
	void getEmployeeByName() {
		List<Employee> employeeByName = service.getEmployeeByName("Satya");
		assertNotNull(employeeByName);
	}

	@Test
	@DisplayName("save the valid Employee and acknowledge the same")
	void saveEmployeeToTheServer() {
		Employee employee = new Employee(102, "employee 2", "pass", "Trainer@gmail.in", 73239712);

		assertNotNull(service.saveEmployee(employee));
	}

	@Test
	@DisplayName("update Employee data")
	void updateEmployeeToTheServer() {
		Employee employee = new Employee(101, "Updated name", "pass", "Enployee@gmail.in", 73239712);
		Employee updateEmployee = service.updateEmployee(employee);
		assertNotNull(updateEmployee);
	}

	@Test
	@DisplayName("Update Employee name by ID")
	void updateEmployeeNameById() {
		String name = "New Name";
		Employee retEmployee = service.updateEmployeeNameById(101, name);
		assertEquals(name, retEmployee.getName());
	}

	@Test
	@DisplayName("Update Employee by name but invalid employee ")
	void updateEmployeeButEmployeeNotFound() {
		assertNull(service.updateEmployeeNameById(91, "New Name"));
	}

}
