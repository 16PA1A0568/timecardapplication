package com.cg.tca.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Attendance {

	@Id
	@Column(name = "ATT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attendanceId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_ID")
	private Employee employee;
	@Column(name = "IN_TIME")
	private LocalTime inTime;
	@Column(name = "OFF_TIME")
	private LocalTime offTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "FROM_DATE")
	private LocalDate fromDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "TO_DATE")
	private LocalDate toDate;
	@Column(name = "STATUS")
	private String status;
}