package pl.school.model.dto.teacher;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDetailsDto {

    Long id;

    String name;

    String surname;
}
