package com.cg.tca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tca.entities.Leave;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.repository.EmployeeRepository;
import com.cg.tca.repository.LeaveRepository;

@Service

public class LeaveServiceImpl implements LeaveService {

	@Autowired
	public LeaveRepository leaveRep;

	@Autowired
	public EmployeeRepository employeeRepository;
	
	@Override
	public Leave addLeave(Leave leave) {
		return leaveRep.save(leave);
	}

	@Override
	public Leave saveLeave(Leave leave) {
		return leaveRep.save(leave);
	}

	@Override
	public Leave findLeave(int leaveId) throws ResourceNotFoundException {
		return leaveRep.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
	}

	@Override
	public int removeLeave(int leaveId) throws ResourceNotFoundException {
		Leave toDelete = leaveRep.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
		if (toDelete != null) {
			leaveRep.deleteById(leaveId);
		}
		return leaveId;
	}

	@Override
	public int updateLeaveById(Integer leaveId, Leave l) throws ResourceNotFoundException {
		Leave leave = leaveRep.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
		leave.setFromDate(l.getFromDate());
		leave.setToDate(l.getToDate());
		leave.setStatus(l.getStatus());
		leaveRep.save(leave);
		return leave.getLeaveId();
	}

	@Override
	public List<Leave> findByAllLeaves() {
		return leaveRep.findAll();
	}

}
