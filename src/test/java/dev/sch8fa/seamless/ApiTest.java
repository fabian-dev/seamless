package dev.sch8fa.seamless;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sch8fa.seamless.domain.NewCompatibility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
                        .content(asJson(newCompatibility)))
                .andExpect(status().isOk());

        //noinspection JsonPathUnknownFunction
        mvc.perform(get("/api/some name/")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @ParameterizedTest
    @CsvSource({
            ",1.0.0,some component,1.0.0",
            "some name,,some component,1.0.0",
            "some name,1.0.0,,1.0.0",
            "some name,1.0.0,some component,",
    })
    void cannotPostInvalidCompatibility(String softwareName, String softwareVersion, String componentName, String componentVersion) throws Exception {

        var invalid = new NewCompatibility(softwareName, softwareVersion, componentName, componentVersion);

        mvc.perform(post("/api/").contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(invalid)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    private static String asJson(NewCompatibility newCompatibility) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(newCompatibility);
    }
}