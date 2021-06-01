package com.cg.tca.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.time.LocalDate;
import java.time.LocalTime;

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

import com.cg.tca.entities.Employee;
import com.cg.tca.entities.Leave;
import com.cg.tca.entities.TimeCard;
import com.cg.tca.services.EmployeeService;
import com.cg.tca.services.LeaveService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = LeaveController.class)
class LeaveControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService empSer;

	@MockBean
	private LeaveService leaveService;

	Employee emp;
	Leave leave;

	@BeforeEach
	void setUp() throws Exception {
		emp = new Employee();
		leave = new Leave();
		emp.setEmployeeId(100);
		emp.setEmployeeName("RAJU");
		emp.setEmployeeEmail("chiku@gmail.com");
		emp.setPhoneNumber("08512518301");
		leave.setStatus("Pending");
		leave.setFromDate(LocalDate.MIN);
		leave.setToDate(LocalDate.MAX);
		leave.setEmployee(emp);
	}

	@AfterEach
	void tearDown() throws Exception {
		emp = null;
		leave = null;
	}

	@Test
	public void testAddLeave() throws Exception {
		String URI = "/api/leave/apply/1";
		String jsonInput = this.converttoJson(leave);

		Mockito.when(leaveService.saveLeave(leave)).thenReturn(leave);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
	}

	@Test
	public void testUpdateLeaveById() throws Exception {
		String URI = "/api/leave/update/1";
		String jsonInput = this.converttoJson(leave);

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 3).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
	}

	@Test
	void testfindLeave() throws Exception {
		Leave Lea = new Leave();
		Lea.setFromDate(LocalDate.MIN);
		Lea.setStatus("pending");
		Lea.setToDate(LocalDate.MAX);

		Mockito.when(leaveService.findLeave(Mockito.anyInt())).thenReturn(Lea);
		mockMvc.perform(get("/api/leave/21")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testRemoveLeave() throws Exception {
		String URI = "/{leaveId}";
		Leave lea = new Leave();
		lea.setLeaveId(2);

		Mockito.when(leaveService.findLeave(2)).thenReturn(lea);
		Mockito.when(leaveService.removeLeave(2)).thenReturn(-1);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 2)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	}

	private String converttoJson(Object manager) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(manager);
	}

}
