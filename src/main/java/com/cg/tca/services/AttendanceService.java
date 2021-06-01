package com.cg.tca.services;

import java.util.List;

import com.cg.tca.entities.Attendance;
import com.cg.tca.exception.ResourceNotFoundException;

public interface AttendanceService {

	public List<Attendance> getAllAttendance();

	public int updateAttendanceById(Integer attendanceId, Attendance att) throws ResourceNotFoundException;

	Attendance getAttendanceById(int attendanceId) throws ResourceNotFoundException;

	boolean deleteAttendanceById(Integer attendanceId) throws ResourceNotFoundException;

	public Attendance add(Attendance att);

	public Attendance saveAttendance(Attendance att);
}