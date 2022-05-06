package com.demo.restapi.controller;

import com.demo.restapi.model.StudentModel;
import com.demo.restapi.service.RestApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/StudentDetails")
public class RestApiController {

    private final RestApiService restapiService;

    @PostMapping
    public void createStudentDetails(@RequestBody StudentModel studentModel) {
        restapiService.createStudentDetails(studentModel);
    }

    @GetMapping("/getData")
    public List<StudentModel> getStudentDetails(@RequestParam("studentName") String studentName){
        return restapiService.getStudentDetails(studentName);
    }
}
