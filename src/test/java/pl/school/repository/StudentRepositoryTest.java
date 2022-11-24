package pl.school.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.school.model.entity.Student;

import javax.persistence.EntityManager;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void shouldFindAllPresidentsWhenSearchForAll() {
        //given
        testEntityManager.persist(Student.builder()
                .name("Mike")
                .surname("Tyson")
                .build());
        //when
        List<Student> students = studentRepository.findAll();

        //then
        Assertions.assertEquals(1, students.size());
    }


}
