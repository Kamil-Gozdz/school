package pl.school.service.teacher;

import pl.school.model.dto.student.StudentDetailsDto;
import pl.school.model.dto.student.StudentDto;
import pl.school.model.dto.teacher.TeacherDto;
import pl.school.model.dto.teacher.TeacherSearchCriteriaDto;

import java.util.List;
import java.util.Set;

public interface TeacherService {

    TeacherDto saveTeacher(TeacherDto teacherDto);

    TeacherDto updateTeacher(TeacherDto teacherDto);

    TeacherDto updatePartialTeacher(TeacherDto teacherDto);

    void deleteTeacher(Long id);

    List<TeacherDto> getAllTeachers(Integer pageNumber,
                                    Integer pageSize,
                                    String sortBy);

    TeacherDto addStudentToList(TeacherDto teacherDto, Long id);

    TeacherDto removeStudentFromList(TeacherDto teacherDto, Long id);

    List<TeacherDto> getTeachersByCriteria(TeacherSearchCriteriaDto criteriaDto);

    Set<StudentDetailsDto> getAssignedStudents(TeacherDto teacherDto);


}
