package com.cg.tca.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.tca.entities.Employee;
import com.cg.tca.exception.ResourceNotFoundException;

@Service
public interface EmployeeService {

	Employee updateEmployee(Integer employeeId, Employee employeeDetails) throws ResourceNotFoundException;

	boolean deleteEmployeeById(Integer employeeId) throws ResourceNotFoundException;

	Employee getEmpById(int empId) throws ResourceNotFoundException;

	Employee createEmployee(Employee employee);

	List<Employee> getAllEmployee();

	Employee save(Employee employee);

}
