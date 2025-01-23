package com.eeze.streaming;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.eeze.streaming.domain.Metadata;
import com.eeze.streaming.domain.Performer;
import com.eeze.streaming.domain.dto.VideoReq;
import com.eeze.streaming.util.ActorRole;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateTests {

    @Autowired
    private MockMvc mockMvc;

    private Gson gson = new Gson();

    @Test
    void testUpdateOK() throws Exception {

        // Prepare the request data

        Performer actor1 = new Performer();
        actor1.setName("lolita reeves");
        actor1.setCharacter("the boss");
        actor1.setRole(ActorRole.LEAD.name());
        Performer actor2 = new Performer();
        actor2.setName("pepita");
        actor2.setCharacter("the employee");
        actor2.setRole(ActorRole.SUPPORT.name());
        List<Performer> performers = new ArrayList<>();
        performers.add(actor1);
        performers.add(actor2);

        Metadata metadata = new Metadata();
        metadata.setDirector("carla garcia");
        metadata.setGenre("comedy");
        metadata.setReleasedYear(2025);
        metadata.setRunningTime(5400);
        metadata.setSynopsis("funny reality");
        metadata.setTitle("smile");
        metadata.setPerformers(performers);

        VideoReq videoReq = new VideoReq();
        videoReq.setContent("dummy");
        videoReq.setMetadata(metadata);

        // do the call to the api
        mockMvc.perform(put("/videos/6c327d34-cdff-4cda-9d74-8f927d89dc3a")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(videoReq)))
                .andExpect(status().isOk()); // Expect HTTP status 200

    }

    @Test
    void testUpdateUserError() throws Exception{

        VideoReq videoReq = new VideoReq();
        videoReq.setContent("dummy");

        // do the call to the api
        mockMvc.perform(put("/videos/6c327d34-cdff-4cda-9d74-8f927d89dc3a")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(videoReq)))
                .andExpect(status().isBadRequest()); // Expect HTTP status 400
    }

}
