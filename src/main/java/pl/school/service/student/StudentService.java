package pl.school.service.student;

import pl.school.model.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDto saveStudent(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto);

    StudentDto updatePartialStudent(StudentDto studentDto);

    void deleteStudent(Long id);

    List<StudentDto> getAllStudents();


}
