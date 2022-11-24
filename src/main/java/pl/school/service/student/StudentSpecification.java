package pl.school.service.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import pl.school.model.dto.student.StudentSearchCriteriaDto;
import pl.school.model.entity.Student;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class StudentSpecification implements Specification<Student> {

    private final StudentSearchCriteriaDto criteriaDto;

    @Override
    public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(teacherNameLike(root, criteriaBuilder),
                teacherSurnameLike(root, criteriaBuilder));
    }
    private Predicate teacherNameLike(Root<Student> root,CriteriaBuilder criteriaBuilder) {
        return nonNull(criteriaDto.getName()) ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + criteriaDto.getName().toLowerCase() + "%") :
                alwaysTruePredicate(criteriaBuilder);
    }
    private Predicate teacherSurnameLike(Root<Student> root,CriteriaBuilder criteriaBuilder) {
        return nonNull(criteriaDto.getSurname()) ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("surname")),
                        "%" + criteriaDto.getSurname().toLowerCase() + "%") :
                alwaysTruePredicate(criteriaBuilder);
    }
    private Predicate alwaysTruePredicate(CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

}
