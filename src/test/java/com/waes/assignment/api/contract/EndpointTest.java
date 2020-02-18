package com.waes.assignment.api.contract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
public class EndpointTest {

    private final String BASE_PATH = "/v1/diff/{id}";
    private final String CONTENT_LEFT = "eyJiZmRydyI6IHRydWV9";
    private final String CONTENT_RIGHT = "eyJhbmRyaSI6IHRydWV9";

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldAddDiffWithLeftAndRightContentAndResultEqual() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/left", 1L)
                .content(CONTENT_LEFT)
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/right", 1L)
                .content(CONTENT_LEFT)
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .get(BASE_PATH, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("EQUAL")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddDiffWithLeftAndRightDifferentContentAndResultNotEqual() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/left", 1L)
                .content(CONTENT_LEFT)
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/right", 1L)
                .content(CONTENT_RIGHT)
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .get(BASE_PATH, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("NOT_EQUAL")))
                .andExpect(jsonPath("$.details", hasSize(2)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddDiffWithDifferentContentSizesAndResultDifferentSize() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/left", 1L)
                .content(CONTENT_LEFT)
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/right", 1L)
                .content("eyJmb28iOiAiYmFyIn0=")
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .get(BASE_PATH, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("DIFFERENT_SIZES")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailWithBothDiffWithoutContent() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/left", 1L))
                .andExpect(status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/right", 1L))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldFailWithInvalidBase64Content() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/left", 1L)
                .content("eyJhbmRyaSI6IHRydWV9f")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Not a valid base64 content")))
                .andExpect(status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders
                .put(BASE_PATH + "/right", 1L)
                .content("eyJhbmRyaSI6IHRydWV9f")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Not a valid base64 content")))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldNotFoundDiff() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(BASE_PATH, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("None diff found with provided ID")))
                .andExpect(status().isNotFound());
    }


}
