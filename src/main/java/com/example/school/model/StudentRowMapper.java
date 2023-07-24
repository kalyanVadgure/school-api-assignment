/*
 * You can use the following import statements
 *
 * import java.sql.ResultSet;
 * import java.sql.SQLException;
 * import org.springframework.jdbc.core.RowMapper;
 *
 */

import org.springframework.jdbc.core.RowMapper;
import com.example.school.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getInt("studentId"));
        student.setStudentName(resultSet.getString("studentName"));
        student.setGender(resultSet.getString("gender"));
        student.setStandard(resultSet.getInt("standard"));
        return student;
    }
}
