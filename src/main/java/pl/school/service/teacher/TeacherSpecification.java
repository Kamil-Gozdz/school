package pl.school.service.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import pl.school.model.dto.teacher.TeacherSearchCriteriaDto;
import pl.school.model.entity.Teacher;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class TeacherSpecification implements Specification<Teacher> {

    private final TeacherSearchCriteriaDto criteriaDto;

    @Override
    public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(teacherNameLike(root, criteriaBuilder),
                teacherSurnameLike(root, criteriaBuilder));
    }
    private Predicate teacherNameLike(Root<Teacher> root,CriteriaBuilder criteriaBuilder) {
        return nonNull(criteriaDto.getName()) ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + criteriaDto.getName().toLowerCase() + "%") :
                alwaysTruePredicate(criteriaBuilder);
    }
    private Predicate teacherSurnameLike(Root<Teacher> root,CriteriaBuilder criteriaBuilder) {
        return nonNull(criteriaDto.getSurname()) ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("surname")),
                        "%" + criteriaDto.getSurname().toLowerCase() + "%") :
                alwaysTruePredicate(criteriaBuilder);
    }
    private Predicate alwaysTruePredicate(CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

}
