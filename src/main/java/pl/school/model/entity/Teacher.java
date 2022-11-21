package pl.school.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.school.model.enums.SubjectEnum;

import javax.persistence.*;
import java.util.Set;

@Getter
@Builder
@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private SubjectEnum subject;

    @ManyToMany(mappedBy = "teachers")
    private Set<Student> students;
}
