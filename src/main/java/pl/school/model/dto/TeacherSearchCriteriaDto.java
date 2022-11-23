package pl.school.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherSearchCriteriaDto {

    String name;

    String surname;

}
