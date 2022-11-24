package pl.school.service.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.school.exception.exceptions.RecordNotFoundException;
import pl.school.model.dto.student.StudentDetailsDto;
import pl.school.model.dto.teacher.TeacherDto;
import pl.school.model.dto.teacher.TeacherSearchCriteriaDto;
import pl.school.model.entity.Student;
import pl.school.model.entity.Teacher;
import pl.school.model.mapper.StudentMapper;
import pl.school.model.mapper.TeacherMapper;
import pl.school.repository.StudentRepository;
import pl.school.repository.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    private final StudentMapper studentMapper;

    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public TeacherDto saveTeacher(TeacherDto teacherDto) {
        return teacherMapper.toDto(teacherRepository.save(teacherMapper.toNewEntity(teacherDto)));
    }

    @Override
    @Transactional
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
    @Transactional
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
        }).orElseThrow(() -> new RecordNotFoundException("Teacher", teacherDto.getId()));
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDto> getAllTeachers(Integer pageNumber,
                                           Integer pageSize,
                                           String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Teacher> teachers = teacherRepository.findAll(pageable);
        return teachers.getContent().stream().map(teacherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TeacherDto addStudentToList(TeacherDto teacherDto, Long id) {
        return teacherRepository.findById(teacherDto.getId()).map(teacher -> {
            teacher.getStudents().add(studentRepository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("Student", id)));
            return teacherMapper.toDto(teacher);
        }).orElseThrow(() -> new RecordNotFoundException("Teacher", teacherDto.getId()));
    }

    @Override
    @Transactional
    public TeacherDto removeStudentFromList(TeacherDto teacherDto, Long id) {
        return teacherRepository.findById(teacherDto.getId()).map(teacher -> {
            teacher.getStudents().remove(studentRepository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("Student", id)));
            return teacherMapper.toDto(teacher);
        }).orElseThrow(() -> new RecordNotFoundException("Teacher", teacherDto.getId()));
    }

    @Override
    public List<TeacherDto> getTeachersByCriteria(TeacherSearchCriteriaDto criteriaDto) {
        Specification<Teacher> specification = new TeacherSpecification(criteriaDto);
        List<Teacher> teachers = teacherRepository.findAll(specification);
        return teachers.stream().map(teacherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Set<StudentDetailsDto> getAssignedStudents(TeacherDto teacherDto) {
        return teacherRepository.findById(teacherDto.getId()).map(teacher -> {
            Set<Student> students = teacherMapper.toNewEntity(teacherDto).getStudents();
            return students.stream().map(studentMapper::toDetailsDto).collect(Collectors.toSet());
        }).orElseThrow(() -> new RecordNotFoundException("Teacher", teacherDto.getId()));
    }
}

