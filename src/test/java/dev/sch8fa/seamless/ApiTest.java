package dev.sch8fa.seamless;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sch8fa.seamless.domain.NewCompatibility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiTest extends AbstractMongoDbTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void canPostCompatibility() throws Exception {

        var newCompatibility = new NewCompatibility("some name", "1.0.0", "some component", "1.0.0");

        mvc.perform(post("/api/").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newCompatibility)))
                .andExpect(status().isOk())
                .andReturn();
    }
}