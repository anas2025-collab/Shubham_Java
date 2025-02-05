package com.example.studentmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        student.setId(id); // Set the ID of the student to be updated
        return studentRepository.save(student); // Save the updated student
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}