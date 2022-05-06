package com.demo.restapi.model;

import lombok.Data;

import java.util.List;

@Data
public class StudentModel {
    private String name;
    private Integer age;
    private String address;
    private List<SubjectModel> subjectModels;
}
