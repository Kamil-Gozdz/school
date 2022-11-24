package pl.school.model.dto.student;

import lombok.*;
import pl.school.model.dto.teacher.TeacherDetailsDto;
import pl.school.model.enums.FieldEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {


    private Long id;
    @Size(min = 3, message = "Name must be longer than two characters")
    private String name;

    private String surname;
    @Email(message = "Email is  incorrect")
    private String email;
    @Min(value = 19, message = "Age can't be less than 19")
    private Integer age;

    private FieldEnum field;

    private Set<TeacherDetailsDto> teachers;
}
