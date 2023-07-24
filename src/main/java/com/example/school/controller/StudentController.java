/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */
package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.school.model.*;
import com.example.school.repository.*;
import com.example.school.service.*;
import com.example.school.model.Student;


import java.util.List;

@RestController
public class StudentController {

    @Autowired
    public StudentH2Service studentH2Service;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentH2Service.getAllStudents();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentH2Service.addStudent(student);
    }

    @PostMapping("/students/bulk")
    public String addMultipleStudents(@RequestBody List<Student> students) {
        int count = studentH2Service.addMultipleStudents(students);
        return "Successfully " + count + " students added.";
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        Student student = studentH2Service.getStudentById(studentId);
        return student;
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        
        return studentH2Service.updateStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentH2Service.deleteStudent(studentId);
        
    }
}

