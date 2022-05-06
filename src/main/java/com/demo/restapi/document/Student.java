package com.demo.restapi.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("students")
@Data
public class Student {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String address;
    private List<Subject> subjects;
}
