package pl.school.model.dto.student;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDetailsDto {

    Long id;

    String name;

    String surname;
}
