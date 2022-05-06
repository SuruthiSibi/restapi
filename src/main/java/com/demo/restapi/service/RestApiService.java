package com.demo.restapi.service;

import com.demo.restapi.model.StudentModel;

import java.util.List;

public interface RestApiService {

    void createStudentDetails(StudentModel studentModel);
    List<StudentModel> getStudentDetails(String studentName);
}
