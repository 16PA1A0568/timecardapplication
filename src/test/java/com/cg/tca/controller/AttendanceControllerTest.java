package com.cg.tca.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cg.tca.entities.Attendance;
import com.cg.tca.entities.Employee;
import com.cg.tca.entities.Leave;
import com.cg.tca.entities.TimeCard;
import com.cg.tca.services.AttendanceService;
import com.cg.tca.services.EmployeeService;
import com.cg.tca.services.TimeCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AttendanceController.class)
class AttendanceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AttendanceService attSer;

	@MockBean
	private EmployeeService empSer;

	Employee emp;
	Attendance att;

	@BeforeEach
	void setUp() throws Exception {
		emp = new Employee();
		att = new Attendance();
		emp.setEmployeeId(100);
		emp.setEmployeeName("RAJU");
		emp.setEmployeeEmail("chiku@gmail.com");
		emp.setPhoneNumber("08512518301");
		att.setFromDate(LocalDate.MIN);
		att.setToDate(LocalDate.MAX);
		att.setInTime(LocalTime.MIN);
		att.setOffTime(LocalTime.MAX);
		att.setStatus("pending");
		att.setEmployee(emp);
	}

	@AfterEach
	void tearDown() throws Exception {
		emp = null;
		att = null;
	}

	@Test
	public void testAddAttendanceByEmpId() throws Exception {
		String URI = "/api/attendance/saveattendance/1";
		String jsonInput = this.converttoJson(att);

		Mockito.when(attSer.saveAttendance(att)).thenReturn(att);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
	}

	@Test
	void testgetAttendanceById() throws Exception {
		Attendance att = new Attendance();
		att.setFromDate(LocalDate.MIN);
		att.setToDate(LocalDate.MAX);
		att.setInTime(LocalTime.MIN);
		att.setOffTime(LocalTime.MAX);
		att.setStatus("pending");
		Mockito.when(attSer.getAttendanceById(Mockito.anyInt())).thenReturn(att);
		mockMvc.perform(get("/api/attendance/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testUpdateAttendanceById() throws Exception {

		String URI = "/api/timecard/timecardedit/1";

		String jsonInput = this.converttoJson(att);

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 3).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
	}

	@Test
	public void testDeleteAttendance() throws Exception {

		String URI = "/api/attendance/delete/1";
		Attendance att = new Attendance();
		att.setEmployee(emp);
		att.setFromDate(LocalDate.of(2020, 05, 17));
		att.setToDate(LocalDate.of(2020, 05, 18));
		att.setInTime(LocalTime.of(9, 03));
		att.setOffTime(LocalTime.of(18, 35));
		att.setStatus("pending");
		ArrayList<TimeCard> checklist = new ArrayList<>();

		String jsonInput = this.converttoJson(true);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

	}

	private String converttoJson(Object manager) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(manager);
	}

}
