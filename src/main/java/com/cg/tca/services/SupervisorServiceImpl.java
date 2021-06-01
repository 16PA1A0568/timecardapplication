package com.cg.tca.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.tca.entities.Supervisor;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.repository.SupervisorRepository;

@Service
public class SupervisorServiceImpl implements SupervisorService {

	@Autowired
	private SupervisorRepository supervisorRepository;

	@Override
	public Supervisor createSupervisor(@RequestBody Supervisor supervisor) {
		return supervisorRepository.save(supervisor);
	}

	@Override
	public Supervisor updateSupervisor(@PathVariable(value = "Id") Integer supervisorId,
			@RequestBody Supervisor supervisorDetails) throws ResourceNotFoundException {
		Supervisor supervisor = supervisorRepository.findById(supervisorId).orElseThrow(
				() -> new ResourceNotFoundException("Supervisor not found for this id :: " + supervisorId));
		supervisor.setSupervisorName(supervisorDetails.getSupervisorName());
		supervisor.setSupervisorNumber(supervisorDetails.getSupervisorNumber());
		supervisor.setSupervisorEmail(supervisorDetails.getSupervisorEmail());
		supervisor.setPassword(supervisorDetails.getPassword());
		supervisor.setUserId(supervisorDetails.getUserId());
		final Supervisor updatedSupervisor = supervisorRepository.save(supervisor);
		return updatedSupervisor;
	}

	@Override
	public boolean deleteSupervisor(Integer supervisorId) throws ResourceNotFoundException {
		Supervisor supervisor = supervisorRepository.findById(supervisorId).orElseThrow(
				() -> new ResourceNotFoundException(" Supervisor not found for this id :: " + supervisorId));
		supervisorRepository.delete(supervisor);

		return true;
	}

	@Override
	public Supervisor getSupervisorById(int supervisorId) throws ResourceNotFoundException {
		if (!supervisorRepository.existsById(supervisorId)) {
			throw new ResourceNotFoundException("Supervisor not found for this id ::" + supervisorId);
		}
		Supervisor e = supervisorRepository.findById(supervisorId).get();
		return e;
	}

	@Override
	public List<Supervisor> getAllSupervisor() {
		return supervisorRepository.findAll();
	}

}
