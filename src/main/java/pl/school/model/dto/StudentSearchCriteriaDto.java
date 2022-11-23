package pl.school.model.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentSearchCriteriaDto {

    String name;

    String surname;
}
