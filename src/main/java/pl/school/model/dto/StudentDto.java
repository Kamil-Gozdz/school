package pl.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
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

    private Set<TeacherDto> teachers;

}
