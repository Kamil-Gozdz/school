package pl.school.model.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pl.school.model.dto.student.StudentDetailsDto;
import pl.school.model.dto.teacher.TeacherDetailsDto;
import pl.school.model.dto.teacher.TeacherDto;
import pl.school.model.entity.Student;
import pl.school.model.entity.Teacher;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    private final StudentMapper studentMapper;
    public TeacherMapper(@Lazy StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

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
    public TeacherDetailsDto toDetailsDto(Teacher teacher) {
        return TeacherDetailsDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .build();
    }

    private Set<StudentDetailsDto> getStudents(Set<Student> students) {
        return students.stream().map(studentMapper::toDetailsDto).collect(Collectors.toSet());
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
    public Teacher toDetailsEntity(TeacherDetailsDto teacherDetailsDto) {
        return Teacher.builder()
                .id(teacherDetailsDto.getId())
                .name(teacherDetailsDto.getName())
                .surname(teacherDetailsDto.getSurname())
                .build();
    }

    private Set<Student> getStudentsDto(Set<StudentDetailsDto> studentsDetailsDto) {
        return studentsDetailsDto.stream().map(studentMapper::toDetailsEntity).collect(Collectors.toSet());
    }
}
