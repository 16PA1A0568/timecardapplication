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

import com.cg.tca.entities.Employee;
import com.cg.tca.entities.TimeCard;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.services.EmployeeService;
import com.cg.tca.services.TimeCardService;

@RestController
@RequestMapping("/api/timecard")
public class TimeCardController {

	@Autowired
	private TimeCardService tcs;

	@Autowired
	private EmployeeService empSer;

	@PostMapping("/timecardentry/{emp_id}")
	public TimeCard createTimeCard(@RequestBody TimeCard tca, @PathVariable(value = "emp_id") Integer empId)
			throws ResourceNotFoundException {
		Employee employee = empSer.getEmpById(empId);
		if (employee != null)
			tca.setEmployee(employee);
		tca.setStatus("Pending");
		return tcs.saveTimeEntry(tca);
	}

	@GetMapping("/gettimecard/{id}")
	public TimeCard getTimeCardById(@PathVariable(value = "id") Integer tcId) {
		return tcs.getTimeCard(tcId);
	}

	@PutMapping("/timecardedit/{tc_id}")
	public Integer editTimeCard(@PathVariable("tc_id") Integer id, @RequestBody TimeCard tcard)
			throws ResourceNotFoundException {
		return tcs.updateEntries(id, tcard);
	}

	@DeleteMapping("/timecarddelete/{id}")
	public Boolean deleteTimeCard(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		return tcs.removeEntry(id);
	}

	@GetMapping("/timecards")
	public List<TimeCard> getAllEntries() {
		List<TimeCard> timecard = tcs.displayAll();
		return timecard;
	}

}
