package com.cg.tca.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.cg.tca.entities.Employee;
import com.cg.tca.entities.TimeCard;
import com.cg.tca.services.EmployeeService;
import com.cg.tca.services.SupervisorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;
	@MockBean
	private SupervisorService supService;

	@Test
	void getEmployeeByIdTest() throws Exception {
		Employee e = new Employee();

		Mockito.when(employeeService.getEmpById(Mockito.anyInt())).thenReturn(e);
		mockMvc.perform(get("/api/employee/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void createEmployeeTest() throws Exception {

		Employee e = new Employee();
		e.setEmployeeName("rahul");
		e.setEmployeeEmail("rahul@gmail.com");

		Mockito.when(employeeService.createEmployee(Mockito.any())).thenReturn(e);
		mockMvc.perform(post("/api/employee/create").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(e)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void DeleteEmployeeByIdTest() throws Exception {

		String URI = "/api/employee/delete/3";
		Employee e = new Employee();
		e.setEmployeeId(3);
		e.setEmployeeName("amrutha");
		e.setEmployeeEmail("ammu@gmail.com");
		e.setPhoneNumber("9550355319");
		ArrayList<Employee> checklist = new ArrayList<>();

		employeeService.deleteEmployeeById(e.getEmployeeId());
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

	}

}
