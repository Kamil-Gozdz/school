package pl.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.school.model.dto.StudentDto;
import pl.school.service.student.StudentService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/all")
    public List<StudentDto> getStudents(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }

    @PostMapping("/post")
    public StudentDto createStudent(@RequestBody @Valid StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }

    @PutMapping("/put")
    public StudentDto updateStudent(@RequestBody @Valid StudentDto studentDto){
        return studentService.updateStudent(studentDto);
    }
    @PatchMapping("/patch")
    public StudentDto updatePartialStudentData(@RequestBody @Valid StudentDto studentDto){
        return studentService.updatePartialStudent(studentDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

}
