/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */
package com.example.school.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.school.repository.*;
import com.example.school.model.*;
import com.example.school.service.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

   
    public int nextStudentId = 1;

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = db.query("SELETE * FROM STUDENT", new StudentRowMapper());
        ArrayList<Student> students = new ArrayList<>(studentList);

        return students;
    }

    @Override
    public Student getStudentById(int studentId) {
        
        try {
            Student student = db.queryForObject("SELETE * FROM STUDENT WHERE studentId = ?", new StudentRowMapper(), studentId);
            return student;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        
    }

    @Override
    public Student addStudent(Student student) {
        student.setStudentId(nextStudentId);
        db.update("INSERT INTO STUDENT(studentName, gender, standard) values(?,?, ?)", student.getStudentName(), student.getGender(), student.getStandard());
        Student savedStudent = db.queryForObject("select* from school where studentName=?", new StudentRowMapper(), student.getStudentName());
        nextStudentId += 1;

        return savedStudent;
    }

    @Override
    public int addMultipleStudents(List<Student> students) {
        int count = 0;
        for (Student student : students) {
            student.setStudentId(nextStudentId);
            db.update("INSERT INTO STUDENT(studentName, gender, standard) values(?,?, ?)", student.getStudentName(), student.getGender(), student.getStandard());
            Student savedStudent = db.queryForObject("select* from school where studentName=?", new StudentRowMapper(), student.getStudentName());
            nextStudentId += 1;
            count++;
        }
        return count;
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        if(student.getStudentName() != null) {
            db.update("Update student set studentName=? Where studentId = ?", student.getStudentName(), student.getStudentId());
        }

        if(student.getGender() != null) {
            db.update("Update student set gender=? Where studentId = ?", student.getGender(), student.getStudentId());
        }

        if(student.getStandard() != null) {
            db.update("Update student set standard=? Where studentId = ?", student.getStandard(), student.getStudentId());
        }

        return getStudentById(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("DELETE FROM STUDENT WHERE studentId=?", studentId);
    }
}
