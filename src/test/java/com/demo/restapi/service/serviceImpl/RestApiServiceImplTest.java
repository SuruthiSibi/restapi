package com.demo.restapi.service.serviceImpl;

import com.demo.restapi.document.Student;
import com.demo.restapi.document.Subject;
import com.demo.restapi.model.StudentModel;
import com.demo.restapi.model.SubjectModel;
import com.demo.restapi.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestApiServiceImplTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    RestApiServiceImpl restApiServiceImpl;

    @Test
    void createStudentDetailsTest() {
        //Given
        StudentModel studentModel = new StudentModel();
        studentModel.setName("Suruthi");
        studentModel.setAge(15);
        studentModel.setAddress("India");
        final SubjectModel subjectModel = new SubjectModel();
        subjectModel.setName("Maths");
        subjectModel.setMarks(99);
        studentModel.setSubjectModels(Collections.singletonList(subjectModel));

        //When
        restApiServiceImpl.createStudentDetails(studentModel);

        //Then
        final ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(studentRepository).save(argumentCaptor.capture());

        final Student student = argumentCaptor.getValue();
        assertEquals("Suruthi", student.getName());
        assertEquals("India", student.getAddress());
        assertEquals(15, student.getAge());

        final Subject subject = student.getSubjects().get(0);
        assertEquals("Maths", subject.getName());
        assertEquals(99, subject.getMarks());
    }

    @Test
    void getStudentDetailsTest() {
        //Given
        String studentName = "Suruthi";

        Student student = new Student();
        student.setName("Suruthi");
        student.setAge(15);
        student.setAddress("India");
        final Subject subject = new Subject();
        subject.setName("Maths");
        subject.setMarks(99);
        student.setSubjects(Collections.singletonList(subject));
        Mockito.when(studentRepository.findByName(studentName)).thenReturn(Collections.singletonList(student));

        //When
        final List<StudentModel> studentDetails = restApiServiceImpl.getStudentDetails(studentName);

        //Then
        Mockito.verify(studentRepository).findByName(studentName);
        final StudentModel studentModel = studentDetails.get(0);
        assertEquals(15, studentModel.getAge());
        assertEquals("India", studentModel.getAddress());

        final SubjectModel subjectModel = studentModel.getSubjectModels().get(0);
        assertEquals("Maths", subjectModel.getName());
        assertEquals(99, subjectModel.getMarks());



    }
}
