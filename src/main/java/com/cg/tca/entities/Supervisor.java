package com.cg.tca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "SUPERVISOR")
public class Supervisor {
	@Id
	@Column(name = "SUP_ID")
	private int supervisorId;
	@Column(name = "SUP_NAME")
	private String supervisorName;
	@Column(name = "SUP_PHNO")
	private String supervisorNumber;
	@Column(name = "SUP_EMAIL")
	private String supervisorEmail;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "USER_ID")
	private String userId;

}
