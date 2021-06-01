package com.cg.tca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tca.entities.Attendance;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.repository.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attdetails;

	@Override
	public Attendance add(Attendance att) {
		return attdetails.save(att);
	}

	@Override
	public Attendance getAttendanceById(int attendanceId) throws ResourceNotFoundException {
		Attendance a = attdetails.findById(attendanceId).orElseThrow(
				() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
		return a;
	}

	@Override
	public List<Attendance> getAllAttendance() {
		return attdetails.findAll();
	}

	@Override
	public boolean deleteAttendanceById(Integer attendanceId) throws ResourceNotFoundException {
		Attendance attendance = attdetails.findById(attendanceId).orElseThrow(
				() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
		attdetails.delete(attendance);
		return true;
	}

	@Override
	public int updateAttendanceById(Integer attendanceId, Attendance atts) throws ResourceNotFoundException {
		Attendance att = attdetails.findById(attendanceId).orElseThrow(
				() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
		att.setInTime(atts.getInTime());
		att.setOffTime(atts.getOffTime());
		att.setFromDate(atts.getFromDate());
		att.setToDate(atts.getToDate());
		att.setStatus(atts.getStatus());
		attdetails.save(att);
		return att.getAttendanceId();
	}

	@Override
	public Attendance saveAttendance(Attendance att) {
		return attdetails.save(att);
	}

}
