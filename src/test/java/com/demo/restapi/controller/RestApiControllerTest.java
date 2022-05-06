package com.demo.restapi.controller;

import com.demo.restapi.model.StudentModel;
import com.demo.restapi.service.serviceImpl.RestApiServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestApiController.class)
class RestApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestApiServiceImpl restApiServiceImpl;

    @Test
    void createStudentDetails() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("src/test/resources/test-data.json")));
        this.mockMvc.perform(post("/StudentDetails").content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getStudentDetails() throws Exception {
        final StudentModel studentModel = new StudentModel();
        studentModel.setName("Suruthi");
        when(restApiServiceImpl.getStudentDetails("Suruthi")).thenReturn(Collections.singletonList(studentModel));
        this.mockMvc.perform(get("/StudentDetails/getData?studentName=Suruthi")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Suruthi")));

    }
}
