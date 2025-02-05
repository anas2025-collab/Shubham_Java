package com.example.studentmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *    GET http://localhost:8081/api/students net::ERR_FAILED 200 (OK)
 *    Uncaught (in promise) TypeError: Failed to fetch
at HTMLButtonElement.fetchStudents ((index):331:36)
 * 
 * The error you're encountering is related to Cross-Origin Resource Sharing (CORS).
 *  When you try to make a request from one origin (in this case, http://localhost:8080 for
 *   the Employee Management application) to another origin (http://localhost:8081 for the 
 *   Student Management application), the browser enforces CORS policies to prevent potentially
 *    harmful requests.

To resolve this issue, you need to configure the Student Management application 
to allow requests from the Employee Management application. Here’s how you can do 
that in a Spring Boot application:
 * 
 * package com.example.studentmanagement;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080") // Allow requests from Employee Management
@RequestMapping("/api/students")
public class StudentController {
    // Your existing methods
}
 * 
 */
@CrossOrigin(origins = "http://localhost:8080") // Allow requests from Employee Management
@RestController
@RequestMapping("/api/students") // Changed to /api/students to avoid conflicts
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    
   
    
    

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}