package com.cg.tca.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tca.entities.Supervisor;
import com.cg.tca.entities.TimeCard;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.services.SupervisorService;
import com.cg.tca.services.TimeCardService;

@RestController
@RequestMapping("/api/supervisor")
public class SupervisorController {

	@Autowired
	private SupervisorService supervisorService;

	@Autowired
	private TimeCardService tcs;

	@PostMapping("/create")
	public ResponseEntity<Supervisor> createCompanySupervisor(@RequestBody Supervisor supervisor) {
		Supervisor sup = supervisorService.createSupervisor(supervisor);
		return new ResponseEntity<Supervisor>(sup, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteManager(@PathVariable(value = "id") Integer supervisorId)
			throws ResourceNotFoundException {

		boolean supervisor = supervisorService.deleteSupervisor(supervisorId);
		return ResponseEntity.ok(supervisor);
	}

	@GetMapping("/all")
	public List<Supervisor> getAllSupervisor() {
		return supervisorService.getAllSupervisor();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Supervisor> findById(@PathVariable(value = "id") int supervisorId)
			throws ResourceNotFoundException {
		Supervisor sup = supervisorService.getSupervisorById(supervisorId);
		return new ResponseEntity<Supervisor>(sup, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Supervisor> updateSupervisor(@PathVariable(value = "id") Integer supervisorId,
			@RequestBody Supervisor supervisorDetails) throws ResourceNotFoundException {
		Supervisor supervisor = supervisorService.updateSupervisor(supervisorId, supervisorDetails);
		return ResponseEntity.ok(supervisor);
	}
	@GetMapping("/timecards")
	public List<TimeCard> getAllEntries() {
		List<TimeCard> timecard = tcs.displayAll();
		return timecard;
	}

	@PutMapping("/tcedit/{tc_id}")
	public Integer editTimeCard(@PathVariable("tc_id") Integer id, @RequestBody TimeCard tcard)
			throws ResourceNotFoundException {
		return tcs.updateEntries(id, tcard);
	}


}
