package com.cg.tca.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import com.cg.tca.entities.Supervisor;
import com.cg.tca.services.SupervisorService;
import com.cg.tca.services.TimeCardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(SupervisorController.class)
class SupervisorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SupervisorService supervisorService;
	@MockBean
	private TimeCardService tcs;

	@Test
	void getSupervisorByIdTest() throws Exception {
		Supervisor sup = new Supervisor();

		Mockito.when(supervisorService.getSupervisorById(Mockito.anyInt())).thenReturn(sup);
		mockMvc.perform(get("/api/supervisor/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void createSupervisorTest() throws Exception {

		Supervisor sup = new Supervisor();
		sup.setSupervisorName("Pravallika");
		sup.setSupervisorEmail("Pravallika@gmail.com");

		Mockito.when(supervisorService.createSupervisor(Mockito.any())).thenReturn(sup);
		mockMvc.perform(post("/api/supervisor/create").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(sup))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void DeleteSupervisorByIdTest() throws Exception {

		String URI = "/api/supersior/delete/3";
		Supervisor sup = new Supervisor();
		sup.setSupervisorName("Pravallika");
		sup.setSupervisorEmail("pravallika@gmail.com");
		sup.setSupervisorNumber("8096993577");
		ArrayList<Supervisor> checklist = new ArrayList<>();

		supervisorService.deleteSupervisor(sup.getSupervisorId());
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
	}

}
