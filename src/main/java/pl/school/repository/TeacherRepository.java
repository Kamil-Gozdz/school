package pl.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.school.model.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
