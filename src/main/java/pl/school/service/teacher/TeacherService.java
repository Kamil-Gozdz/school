package pl.school.service.teacher;

import org.springframework.data.domain.Pageable;
import pl.school.model.dto.TeacherDto;
import pl.school.model.dto.TeacherSearchCriteriaDto;

import java.util.List;

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


}
