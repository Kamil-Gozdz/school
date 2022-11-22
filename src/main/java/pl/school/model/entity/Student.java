package pl.school.model.entity;

import lombok.*;
import pl.school.model.enums.FieldEnum;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private FieldEnum field;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers;
}
