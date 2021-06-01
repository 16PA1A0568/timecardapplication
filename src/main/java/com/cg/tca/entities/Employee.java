package com.cg.tca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "emp_id")
	private int employeeId;
	@Column(name = "emp_name")
	private String employeeName;
	@Column(name = "emp_role")
	private String employeeRole;
	@Column(name = "emp_phno")
	private String phoneNumber;
	@Column(name = "EMP_EMAIL")
	private String employeeEmail;
	@Column(name = "password")
	private String password;
	@Column(name = "user_id")
	private String userId;
	@ManyToOne
	@JoinColumn(name = "sup_id")
	@JsonBackReference
	private Supervisor supervisor;

}
