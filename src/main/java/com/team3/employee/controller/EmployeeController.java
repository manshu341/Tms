package com.team3.employee.controller;

/**
 * @author Satyaa
 * @see Employee Controller -> EmployeeEndpoints
 * @since February, 2022
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team3.employee.beans.Employee;
import com.team3.employee.exception.EmployeeNotFoundException;
import com.team3.employee.services.EmployeeServices;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeServices services;

	/**
	 * @see Verifying weather service is live or not
	 * @return validation
	 */
	@GetMapping("/health")
	public String sayHello() {
		return "Service is up";
	}

	/**
	 * @see Add new employee endpoint
	 * @param employee
	 * @return employee ResponseEntity
	 */
	@PostMapping("/employee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee retEmployee = services.saveEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(retEmployee);
	}

	/**
	 * @see update existing employee endpoint
	 * @param employee
	 * @return employee ResponseEntity
	 */
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee retEmployee = services.updateEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(retEmployee);
	}

	/**
	 * @see Get all employees endpoint
	 * @return list of all employees
	 */
	@GetMapping("/employee")
	public List<Employee> getEmployees() {
		return services.getAllEmployees();
	}

	/**
	 * @see get employee profile by employeeId endpoint
	 * @param id -> employeeId
	 * @return employee
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable Integer id) throws EmployeeNotFoundException {
		Employee retEmployee = services.getEmployeeByIdAsObject(id);
		if (retEmployee == null) {

			throw new EmployeeNotFoundException("Sorry Employee not found");

		}
		return retEmployee;

	}

	/**
	 * @see Update Employee name by there id endpoint
	 * @param id   -> employeeId
	 * @param name -> employee Updated name
	 * @return -> Employee ResponseEntity
	 */
	@PutMapping("/employee/{id}/{name}")
	public ResponseEntity<Employee> UpdateNameById(@PathVariable Integer id, @PathVariable String name) {
		Employee retEmployee = services.updateEmployeeNameById(id, name);
		return ResponseEntity.status(HttpStatus.CREATED).body(retEmployee);
	}

}
