package com.cg.tca.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Entity
@Data
@Table(name = "LEAVE")
public class Leave {

	@Id
	@Column(name = "LEAVE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leaveId;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "FROM_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate fromDate;
	@Column(name = "TO_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate toDate;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_ID")
	private Employee employee;
}
