package pl.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private Integer age;

    private Set<StudentDto> students;
}
