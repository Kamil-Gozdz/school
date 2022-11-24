package pl.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.school.model.dto.student.StudentDto;
import pl.school.model.dto.student.StudentSearchCriteriaDto;
import pl.school.model.dto.teacher.TeacherDetailsDto;
import pl.school.service.student.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get/all")
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

    @PostMapping("/post/teacher/{id}")
    public StudentDto addTeacherToList(@RequestBody StudentDto studentDto, @PathVariable Long id){
        return studentService.addTeacherToList(studentDto,id);
    }

    @DeleteMapping("/delete/teacher/{id}")
    public StudentDto deleteTeacherFromList(@RequestBody StudentDto studentDto, @PathVariable Long id) {
        return studentService.removeTeacherFromList(studentDto, id);
    }
    @PostMapping("/search")
    public List<StudentDto> searchTeachersByCriteria(@RequestBody StudentSearchCriteriaDto criteriaDto){
        return studentService.getStudentsByCriteria(criteriaDto);
    }
    @GetMapping("/get/teachers")
    public Set<TeacherDetailsDto> getAssignedStudents(@RequestBody StudentDto studentDto){
        return studentService.getAssignedTeachers(studentDto);
    }
}
