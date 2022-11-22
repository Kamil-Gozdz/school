package pl.school.service.student;

import org.springframework.data.domain.Pageable;
import pl.school.model.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto saveStudent(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto);

    StudentDto updatePartialStudent(StudentDto studentDto);

    void deleteStudent(Long id);

    List<StudentDto> getAllStudents(Pageable pageable);


}
