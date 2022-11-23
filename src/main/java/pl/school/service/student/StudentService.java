package pl.school.service.student;

import pl.school.model.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto saveStudent(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto);

    StudentDto updatePartialStudent(StudentDto studentDto);

    void deleteStudent(Long id);

    List<StudentDto> getAllStudents(Integer pageNumber,
                                    Integer pageSize, String sortBy);

    StudentDto addTeacherToList(StudentDto studentDto, Long id);

    StudentDto removeTeacherFromList(StudentDto studentDto, Long id);
}
