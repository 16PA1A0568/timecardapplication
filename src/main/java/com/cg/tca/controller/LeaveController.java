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

import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.entities.Employee;
import com.cg.tca.entities.Leave;
import com.cg.tca.services.EmployeeService;
import com.cg.tca.services.LeaveService;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;

	@Autowired
	private EmployeeService empSer;

	@PostMapping("/apply/{emp_id}")
	public ResponseEntity<Leave> addLeave(@RequestBody Leave leave, @PathVariable(value = "emp_id") Integer empId)
			throws ResourceNotFoundException {
		Employee employee = empSer.getEmpById(empId);
		if (employee != null)
			leave.setEmployee(employee);
		 leave.setStatus("Pending");
		 leaveService.addLeave(leave);
		return new ResponseEntity<Leave>(HttpStatus.CREATED);
	}

	@GetMapping("/{leaveId}")
	public Leave findLeave(@PathVariable Integer leaveId) throws ResourceNotFoundException {
		return leaveService.findLeave(leaveId);
	}

	@GetMapping("/all")
	List<Leave> findAllLeaves() {
		return leaveService.findByAllLeaves();
	}

	@DeleteMapping("/{leaveId}")
	public ResponseEntity<Integer> removeLeave(@PathVariable Integer leaveId) throws ResourceNotFoundException {
		leaveService.removeLeave(leaveId);
		return new ResponseEntity<Integer>(HttpStatus.OK);
	}

	@PutMapping("/update/{leaveId}")
	public String updateLeaveById(@PathVariable Integer leaveId, @RequestBody Leave leave)
			throws ResourceNotFoundException {
		leave.setStatus("Pending");
		leaveService.updateLeaveById(leaveId, leave);
		return "Leave Updated";

	}

}
