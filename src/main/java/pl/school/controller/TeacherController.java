package pl.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.school.model.dto.TeacherDto;
import pl.school.service.teacher.TeacherService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/all")
    public List<TeacherDto> getTeachers(@RequestParam(defaultValue = "1") Integer pageNumber,
                                        @RequestParam(defaultValue = "2") Integer pageSize,
                                        @RequestParam(defaultValue = "id") String sortBy) {
        return teacherService.getAllTeachers(pageNumber, pageSize, sortBy);
    }

    @PostMapping("/post")
    public TeacherDto createTeacher(@RequestBody @Valid TeacherDto teacherDto) {
        return teacherService.saveTeacher(teacherDto);
    }

    @PutMapping("/put")
    public TeacherDto updateTeacher(@RequestBody @Valid TeacherDto teacherDto) {
        return teacherService.updateTeacher(teacherDto);
    }

    @PatchMapping("/patch")
    public TeacherDto updatePartialTeacherData(@RequestBody @Valid TeacherDto teacherDto) {
        return teacherService.updatePartialTeacher(teacherDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeacherById(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

    @PutMapping("/put/student/{id}")
    public TeacherDto addStudentToList(@RequestBody @Valid TeacherDto teacherDto, @PathVariable Long id) {
        return teacherService.addStudentToList(teacherDto, id);
    }

    @DeleteMapping("/delete/student/{id}")
    public TeacherDto deleteTeacherFromList(@RequestBody TeacherDto teacherDto, @PathVariable Long id) {
        return teacherService.removeStudentFromList(teacherDto, id);
    }
}
