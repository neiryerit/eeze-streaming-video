package com.eeze.streaming;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testDeleteOK() throws Exception{

        mockMvc.perform(delete("/videos/6c327d34-cdff-4cda-9d74-8f927d89dc3a")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Expect HTTP status 200
    }

    @Test
    void testDeleteNOK() throws Exception{
        
        mockMvc.perform(delete("/videos/6c327d34-cdff-4cda-9d74-8f927d89dc30")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // Expect HTTP status 404
    }

}
