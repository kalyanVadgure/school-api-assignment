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

import com.example.school.repository.*;
import com.example.school.model.*;
import com.example.school.service.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentH2Service implements StudentRepository {

    private List<Student> studentTable = new ArrayList<>();
    private int nextStudentId = 1;

    @Override
    public List<Student> getAllStudents() {
        return studentTable;
    }

    @Override
    public Student getStudentById(int studentId) {
        for (Student student : studentTable) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    @Override
    public Student addStudent(Student student) {
        student.setStudentId(nextStudentId);
        studentTable.add(student);
        nextStudentId++;
        return student;
    }

    @Override
    public int addMultipleStudents(List<Student> students) {
        int count = 0;
        for (Student student : students) {
            student.setStudentId(nextStudentId);
            studentTable.add(student);
            nextStudentId++;
            count++;
        }
        return count;
    }

    @Override
    public void updateStudent(Student student) {
        for (Student existingStudent : studentTable) {
            if (existingStudent.getStudentId() == student.getStudentId()) {
                existingStudent.setStudentName(student.getStudentName());
                existingStudent.setGender(student.getGender());
                existingStudent.setStandard(student.getStandard());
                break;
            }
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        studentTable.removeIf(student -> student.getStudentId() == studentId);
    }
}
