package com.eeze.streaming;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultingTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getVideoLst() throws Exception {

		mockMvc.perform(get("/videos"))
                .andExpect(status().isOk()) // HTTP status 200
                .andExpect(jsonPath("$").isArray());
	}

	@Test
	void getVideoFilterResults() throws Exception {

		mockMvc.perform(get("/videos?director=garcia&releasedYear=2025"))
                .andExpect(status().isOk()) // HTTP status 200
                .andExpect(jsonPath("$").isArray());
	}

	@Test
	void getVideoFilterNoResults() throws Exception {

		mockMvc.perform(get("/videos?director=gar&releasedYear=205"))
                .andExpect(status().isOk()) // HTTP status 200
                .andExpect(jsonPath("$").isArray());
	}

	@Test
	void loadVideo() throws Exception{

		mockMvc.perform(get("/videos/6c327d34-cdff-4cda-9d74-8f927d89dc3a?action=load"))
                .andExpect(status().isOk());// HTTP status 200
	}

	@Test
	void loadVideoNoFound() throws Exception{

		mockMvc.perform(get("/videos/6c327d34-cdff-4cda-9d74-8f927d89dc30?action=load")) // Using a non-existing ID
                .andExpect(status().isNotFound());// HTTP status 404
	}

	@Test
	void playVideo() throws Exception{
		
		mockMvc.perform(get("/videos/6c327d34-cdff-4cda-9d74-8f927d89dc3a?action=play"))
                .andExpect(status().isOk());// HTTP status 200
	}

	@Test
	void getVideoNoAction() throws Exception{

		mockMvc.perform(get("/videos/6c327d34-cdff-4cda-9d74-8f927d89dc3a"))
                .andExpect(status().isBadRequest());// HTTP status 400
	}

	@Test
	void getVideoEngagements() throws Exception{
		mockMvc.perform(get("/videos/6c327d34-cdff-4cda-9d74-8f927d89dc3a/engagements"))
                .andExpect(status().isOk());// HTTP status 200
	}

}
