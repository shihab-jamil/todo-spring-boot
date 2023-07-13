package ch.selise.todo.dao;

import ch.selise.todo.dto.TaskFilterDTO;
import ch.selise.todo.dto.UserFilterDto;
import ch.selise.todo.entity.Task;
import ch.selise.todo.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class UserQuery {
    public static Specification<User> getQuery(UserFilterDto dto) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isNotBlank(dto.getFirstName())) {
                predicates.add(cb.like(root.get("firstName"), "%"+dto.getFirstName()+"%"));
            }
            if (isNotBlank(dto.getLastName())) {
                predicates.add(cb.like(root.get("lastName"), "%"+dto.getLastName()+"%"));
            }
            if (isNotBlank(dto.getUsername())) {
                predicates.add(cb.equal(root.get("username"), dto.getUsername()));
            }
            if (isNotBlank(dto.getUsername())) {
                predicates.add(cb.equal(root.get("email"), dto.getEmail()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
