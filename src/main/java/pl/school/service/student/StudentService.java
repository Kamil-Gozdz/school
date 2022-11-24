package pl.school.service.student;

import pl.school.model.dto.student.StudentDto;
import pl.school.model.dto.student.StudentSearchCriteriaDto;
import pl.school.model.dto.teacher.TeacherDetailsDto;

import java.util.List;
import java.util.Set;

public interface StudentService {

    StudentDto saveStudent(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto);

    StudentDto updatePartialStudent(StudentDto studentDto);

    void deleteStudent(Long id);

    List<StudentDto> getAllStudents(Integer pageNumber,
                                    Integer pageSize, String sortBy);

    StudentDto addTeacherToList(StudentDto studentDto, Long id);

    StudentDto removeTeacherFromList(StudentDto studentDto, Long id);

    List<StudentDto> getStudentsByCriteria(StudentSearchCriteriaDto criteriaDto);

    Set<TeacherDetailsDto> getAssignedTeachers(StudentDto studentDto);
}
