package com.demo.restapi.document;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Subject {
    @Id
    private String id;
    private String name;
    private Integer marks;
}
