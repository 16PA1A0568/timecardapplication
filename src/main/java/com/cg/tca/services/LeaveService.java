package com.cg.tca.services;

import java.util.List;

import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.entities.Leave;

public interface  LeaveService {
	
	public Leave saveLeave(Leave leave);

	public Leave findLeave(int leaveId) throws ResourceNotFoundException;

	int removeLeave(int leaveId) throws ResourceNotFoundException;
	
	List<Leave>findByAllLeaves();
	
	public int updateLeaveById(Integer leaveId, Leave lea) throws ResourceNotFoundException;

	public Leave addLeave(Leave leave);
      
}