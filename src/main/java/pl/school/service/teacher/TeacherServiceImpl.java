package pl.school.service.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.exception.exceptions.RecordNotFoundException;
import pl.school.model.dto.TeacherDto;
import pl.school.model.mapper.TeacherMapper;
import pl.school.repository.TeacherRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    @Override
    public TeacherDto saveTeacher(TeacherDto teacherDto) {
        return teacherMapper.toDto(teacherRepository.save(teacherMapper.toNewEntity(teacherDto)));
    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto) {
        return teacherRepository.findById(teacherDto.getId()).map(teacher -> {
            teacher.setName(teacherDto.getName());
            teacher.setSurname(teacherDto.getSurname());
            teacher.setEmail(teacherDto.getEmail());
            teacher.setAge(teacherDto.getAge());
            teacher.setSubject(teacherDto.getSubject());
            teacher.setStudents(teacherMapper.toNewEntity(teacherDto).getStudents());
            return teacherMapper.toDto(teacher);
        }).orElseThrow(() -> new RecordNotFoundException("Teacher", teacherDto.getId()));
    }

    @Override
    public TeacherDto updatePartialTeacher(TeacherDto teacherDto) {
        return teacherRepository.findById(teacherDto.getId()).map(teacher -> {
            if (Objects.nonNull(teacherDto.getName())) {
                teacher.setName(teacherDto.getName());
            }
            if (Objects.nonNull(teacherDto.getSurname())) {
                teacher.setSurname(teacherDto.getSurname());
            }
            if (Objects.nonNull(teacherDto.getAge())) {
                teacher.setAge(teacherDto.getAge());
            }
            if (Objects.nonNull(teacherDto.getEmail())) {
                teacher.setEmail(teacherDto.getEmail());
            }
            if (Objects.nonNull(teacherDto.getSubject())) {
                teacher.setSubject(teacherDto.getSubject());
            }
            if (Objects.nonNull(teacherDto.getStudents())) {
                teacher.setStudents(teacherMapper.toNewEntity(teacherDto).getStudents());
            }
            return teacherMapper.toDto(teacher);
        }).orElseThrow(() -> new RecordNotFoundException("Teacher",teacherDto.getId()));
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream().map(teacherMapper::toDto).collect(Collectors.toList());
    }
}
