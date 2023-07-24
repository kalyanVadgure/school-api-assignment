// Write your code here
package com.example.school.repository;


import java.util.List;
import com.example.school.model.*;

public interface StudentRepository {
    List<Student> getAllStudents();
    Student getStudentById(int studentId);
    Student addStudent(Student student);
    int addMultipleStudents(List<Student> students);
    void updateStudent(Student student);
    void deleteStudent(int studentId);
}
