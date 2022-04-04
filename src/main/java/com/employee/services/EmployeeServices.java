package com.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.beans.Employee;
import com.employee.repo.EmployeeReposatory;

/**
 * @author Satyaa
 * @see Employee Services
 * @since February, 2022
 */
@Service
public class EmployeeServices {

	@Autowired
	private EmployeeReposatory employeeReposatory;

	/**
	 * @see Add new Employee in db
	 * @param employee
	 * @return saved employee 
	 */
	public Employee saveEmployee(Employee employee) {
		employeeReposatory.insert(employee);
		return employee;
	}

	/**
	 * @see Update existing employee in db
	 * @param employee
	 * @return updated employee 
	 */
	public Employee updateEmployee(Employee employee) {
		employeeReposatory.save(employee);
		return employee;
	}

	/**
	 * @see Get all employees from database 
	 * @return list of all employees
	 */
	public List<Employee> getAllEmployees() {
		return employeeReposatory.findAll();
	}

	/**
	 * @see Update employee name by Id
	 * @param id -> employeeId
	 * @param name -> employee new Name
	 * @return updated employee data
	 */
	public Employee updateEmployeeNameById(Integer id, String name) {
		Optional<Employee> byId = employeeReposatory.findById(id);
		if (byId.isPresent()) {
			Employee employee = byId.get();
			employee.setName(name);

			employeeReposatory.save(employee);
			return employee;
		}
		return null;
	}

	/**
	 * @see get employee by there name 
	 * @param name -> employeeName 
	 * @return all employees with name
	 */
	public List<Employee> getEmployeeByName(String name) {
		return employeeReposatory.findByName(name);
	}

	/**
	 * @see get employee by there id
	 * @param id -> employeeId
	 * @return employee
	 */
	public Employee getEmployeeByIdAsObject(Integer id) {
		Optional<Employee> byId = employeeReposatory.findById(id);
		return byId.isPresent() ? byId.get() : null;
	}

}
