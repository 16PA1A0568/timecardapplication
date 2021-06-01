package com.cg.tca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tca.entities.Attendance;
import com.cg.tca.entities.Employee;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.services.AttendanceService;
import com.cg.tca.services.EmployeeService;

@RestController
@RequestMapping("/api/attendance")

public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private EmployeeService empSer;

	@GetMapping("/all")
	public List<Attendance> getAllAttendance() {
		return attendanceService.getAllAttendance();
	}

	@PostMapping("/saveattendance/{emp_id}")
	public Attendance addAttendance(@PathVariable(value = "emp_id") Integer empId, @RequestBody Attendance att)
			throws ResourceNotFoundException {
		Employee employee = empSer.getEmpById(empId);
		if (employee != null)
			att.setEmployee(employee);
		att.setStatus("Pending");
		return attendanceService.saveAttendance(att);
	}

	@GetMapping("/{id}")
	public Attendance getAttendanceById(@PathVariable(value = "id") int attendanceId) throws ResourceNotFoundException {
		return attendanceService.getAttendanceById(attendanceId);
	}

	@PutMapping("/update/{id}")
	public int updateAttendanceById(@PathVariable(value = "id") Integer attendanceId,
			@RequestBody Attendance attendanceDetails) throws ResourceNotFoundException {
		attendanceDetails.setStatus("Pending");
		return attendanceService.updateAttendanceById(attendanceId, attendanceDetails);
	}

	@DeleteMapping("/delete/{id}")
	public Boolean deleteAttendance(@PathVariable(value = "id") Integer attendanceId) throws ResourceNotFoundException {
		Boolean att1 = attendanceService.deleteAttendanceById(attendanceId);

		return att1;
	}
}