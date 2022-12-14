package pl.school.service.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.school.exception.exceptions.RecordNotFoundException;
import pl.school.model.dto.student.StudentDto;
import pl.school.model.dto.student.StudentSearchCriteriaDto;
import pl.school.model.dto.teacher.TeacherDetailsDto;
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
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final TeacherMapper teacherMapper;

    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public StudentDto saveStudent(StudentDto studentDto) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toNewEntity(studentDto)));
    }

    @Override
    @Transactional
    public StudentDto updateStudent(StudentDto studentDto) {
        return studentRepository.findById(studentDto.getId()).map(student -> {
            student.setName(studentDto.getName());
            student.setSurname(studentDto.getSurname());
            student.setEmail(studentDto.getEmail());
            student.setAge(studentDto.getAge());
            student.setField(studentDto.getField());
            student.setTeachers(studentMapper.toNewEntity(studentDto).getTeachers());
            return studentMapper.toDto(student);
        }).orElseThrow(() -> new RecordNotFoundException("Student", studentDto.getId()));
    }

    @Override
    @Transactional
    public StudentDto updatePartialStudent(StudentDto studentDto) {
        return studentRepository.findById(studentDto.getId()).map(student -> {
            if (Objects.nonNull(studentDto.getName())) {
                student.setName(studentDto.getName());
            }
            if (Objects.nonNull(studentDto.getSurname())) {
                student.setSurname(studentDto.getSurname());
            }
            if (Objects.nonNull(studentDto.getAge())) {
                student.setAge(studentDto.getAge());
            }
            if (Objects.nonNull(studentDto.getEmail())) {
                student.setEmail(studentDto.getEmail());
            }
            if (Objects.nonNull(studentDto.getField())) {
                student.setField(studentDto.getField());
            }
            if (Objects.nonNull(studentDto.getTeachers())) {
                student.setTeachers(studentMapper.toNewEntity(studentDto).getTeachers());
            }
            return studentMapper.toDto(student);
        }).orElseThrow(() -> new RecordNotFoundException("Student", studentDto.getId()));
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDto> getAllStudents(Integer pageNumber,
                                           Integer pageSize,
                                           String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Student> students = studentRepository.findAll(pageable);
        return students.getContent().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentDto addTeacherToList(StudentDto studentDto, Long id) {
        return studentRepository.findById(studentDto.getId()).map(student -> {
            student.getTeachers().add(teacherRepository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("Teacher", id)));
            return studentMapper.toDto(student);
        }).orElseThrow(() -> new RecordNotFoundException("Student", studentDto.getId()));
    }

    @Override
    @Transactional
    public StudentDto removeTeacherFromList(StudentDto studentDto, Long id) {
        return studentRepository.findById(studentDto.getId()).map(student -> {
            student.getTeachers().remove(teacherRepository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("Teacher", id)));
            return studentMapper.toDto(student);
        }).orElseThrow(() -> new RecordNotFoundException("Student", studentDto.getId()));
    }

    @Override
    public List<StudentDto> getStudentsByCriteria(StudentSearchCriteriaDto criteriaDto) {
        Specification<Student> specification = new StudentSpecification(criteriaDto);
        List<Student> students = studentRepository.findAll(specification);
        return  students.stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Set<TeacherDetailsDto> getAssignedTeachers(StudentDto studentDto) {
        return studentRepository.findById(studentDto.getId()).map(student -> {
             Set<Teacher> teachers = studentMapper.toNewEntity(studentDto).getTeachers();
            return teachers.stream().map(teacherMapper::toDetailsDto).collect(Collectors.toSet());
        }).orElseThrow(() -> new RecordNotFoundException("Student", studentDto.getId()));
    }
}