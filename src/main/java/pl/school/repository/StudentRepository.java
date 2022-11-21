package pl.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.school.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
