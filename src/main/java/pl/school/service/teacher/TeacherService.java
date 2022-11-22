package pl.school.service.teacher;

import org.springframework.data.domain.Pageable;
import pl.school.model.dto.TeacherDto;

import java.util.List;

public interface TeacherService {

    TeacherDto saveTeacher(TeacherDto teacherDto);

    TeacherDto updateTeacher(TeacherDto teacherDto);

    TeacherDto updatePartialTeacher(TeacherDto teacherDto);

    void deleteTeacher(Long id);

    List<TeacherDto> getAllTeachers(Integer pageNumber,
                                    Integer pageSize,
                                    String sortBy);
}
