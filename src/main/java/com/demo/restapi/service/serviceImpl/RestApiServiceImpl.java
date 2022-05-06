package com.demo.restapi.service.serviceImpl;

import com.demo.restapi.document.Student;
import com.demo.restapi.document.Subject;
import com.demo.restapi.model.StudentModel;
import com.demo.restapi.model.SubjectModel;
import com.demo.restapi.repository.StudentRepository;
import com.demo.restapi.service.RestApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestApiServiceImpl implements RestApiService {

    private final StudentRepository studentRepository;

    @Override
    public void createStudentDetails(StudentModel studentModel) {
        Student student = new Student();
        BeanUtils.copyProperties(studentModel, student);
        final List<Subject> subjectList = studentModel.getSubjectModels().stream()
                .map(this::mapSubjectModelToSubject)
                .collect(Collectors.toList());
        student.setSubjects(subjectList);
        studentRepository.save(student);
    }

    @Override
    public List<StudentModel> getStudentDetails(String studentName) {
        List<Student> students = studentRepository.findByName(studentName);
        return students.stream()
                .map(this::mapStudentToStudentModel)
                .collect(Collectors.toList());
    }

    private StudentModel mapStudentToStudentModel(Student student) {
        StudentModel studentModel = new StudentModel();
        BeanUtils.copyProperties(student, studentModel);
        final List<SubjectModel> subjectModelList = student.getSubjects().stream()
                .map(this::mapSubjectToSubjectModel)
                .collect(Collectors.toList());
        studentModel.setSubjectModels(subjectModelList);
        return studentModel;
    }

    private SubjectModel mapSubjectToSubjectModel(Subject subject) {
        SubjectModel subjectModel = new SubjectModel();
        BeanUtils.copyProperties(subject, subjectModel);
        return subjectModel;
    }

    private Subject mapSubjectModelToSubject(SubjectModel subjectModel) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectModel, subject);
        return subject;
    }
}
