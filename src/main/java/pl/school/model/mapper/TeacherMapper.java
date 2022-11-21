package pl.school.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.school.model.dto.StudentDto;
import pl.school.model.dto.TeacherDto;
import pl.school.model.entity.Student;
import pl.school.model.entity.Teacher;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    private final StudentMapper studentMapper;

    public TeacherDto toDto(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .email(teacher.getEmail())
                .age(teacher.getAge())
                .subject(teacher.getSubject())
                .students(getStudents(teacher.getStudents()))
                .build();
    }

    private Set<StudentDto> getStudents(Set<Student> students) {
        return students.stream().map(studentMapper::toDto).collect(Collectors.toSet());
    }
    public Teacher toNewEntity(TeacherDto teacherDto) {
        return Teacher.builder()
                .id(teacherDto.getId())
                .name(teacherDto.getName())
                .surname(teacherDto.getSurname())
                .email(teacherDto.getEmail())
                .age(teacherDto.getAge())
                .subject(teacherDto.getSubject())
                .students(getStudentsDto(teacherDto.getStudents()))
                .build();
    }

    private Set<Student> getStudentsDto(Set<StudentDto> studentsDto) {
        return studentsDto.stream().map(studentMapper::toNewEntity).collect(Collectors.toSet());
    }
}
