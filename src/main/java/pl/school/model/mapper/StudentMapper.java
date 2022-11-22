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
public class StudentMapper {

    private final TeacherMapper teacherMapper;

    public StudentDto toDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .email(student.getEmail())
                .age(student.getAge())
                .field(student.getField())
                .teachers(getTeachers(student.getTeachers()))
                .build();
    }

    private Set<TeacherDto> getTeachers(Set<Teacher> teachers) {
        return teachers.stream().map(teacherMapper::toDto).collect(Collectors.toSet());
    }

    public Student toNewEntity(StudentDto studentDto) {
        return Student.builder()
                .id(studentDto.getId())
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .email(studentDto.getEmail())
                .age(studentDto.getAge())
                .field(studentDto.getField())
                .teachers(getTeachersDto(studentDto.getTeachers()))
                .build();
    }

    private Set<Teacher> getTeachersDto(Set<TeacherDto> teachersDto) {
        return teachersDto.stream().map(teacherMapper::toNewEntity).collect(Collectors.toSet());
    }
}
