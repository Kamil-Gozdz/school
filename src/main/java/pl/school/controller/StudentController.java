package pl.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.school.model.dto.StudentDto;
import pl.school.service.student.StudentService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/all")
    public List<StudentDto> getStudents(@RequestParam(defaultValue = "1") Integer pageNumber,
                                        @RequestParam(defaultValue = "2") Integer pageSize,
                                        @RequestParam(defaultValue = "id") String sortBy) {

        return studentService.getAllStudents(pageNumber,pageSize,sortBy);
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

    @PutMapping("/put/teacher/{id}")
    public StudentDto addTeacherToList(@RequestBody StudentDto studentDto, @PathVariable Long id){
        return studentService.addTeacherToList(studentDto,id);
    }

    @DeleteMapping("/delete/teacher/{id}")
    public StudentDto deleteTeacherFromList(@RequestBody StudentDto studentDto, @PathVariable Long id) {
        return studentService.removeTeacherFromList(studentDto, id);
    }
}
