package com.demo.restapi.repository;

import com.demo.restapi.document.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {

    List<Student> findByName(String name);
}
