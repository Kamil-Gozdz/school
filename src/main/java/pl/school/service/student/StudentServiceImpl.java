package pl.school.service.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.model.dto.StudentDto;
import pl.school.model.mapper.StudentMapper;
import pl.school.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

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
        }).orElseThrow(RuntimeException::new);
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
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }
}
