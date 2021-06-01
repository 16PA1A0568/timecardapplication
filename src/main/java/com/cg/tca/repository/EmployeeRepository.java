package com.cg.tca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.tca.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}