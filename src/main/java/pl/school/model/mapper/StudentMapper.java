package pl.school.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.school.model.dto.student.StudentDetailsDto;
import pl.school.model.dto.student.StudentDto;
import pl.school.model.dto.teacher.TeacherDetailsDto;
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
    public StudentDetailsDto toDetailsDto(Student student) {
        return StudentDetailsDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .build();
    }

    private Set<TeacherDetailsDto> getTeachers(Set<Teacher> teachers) {
        return teachers.stream().map(teacherMapper::toDetailsDto).collect(Collectors.toSet());
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
    public Student toDetailsEntity(StudentDetailsDto studentDetailsDto) {
        return Student.builder()
                .id(studentDetailsDto.getId())
                .name(studentDetailsDto.getName())
                .surname(studentDetailsDto.getSurname())
                .build();
    }

    private Set<Teacher> getTeachersDto(Set<TeacherDetailsDto> teachersDetailsDto) {
        return teachersDetailsDto.stream().map(teacherMapper::toDetailsEntity).collect(Collectors.toSet());
    }
}
