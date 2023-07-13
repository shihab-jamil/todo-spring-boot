package ch.selise.todo.dao;

import ch.selise.todo.dto.TaskFilterDTO;
import ch.selise.todo.entity.Task;
import ch.selise.todo.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author Rhidoy
 * @created 12/07/2023
 * @package ch.selise.todo.dao
 */
public class TaskQuery {

    public static Specification<Task> getQuery(TaskFilterDTO dto) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isNotBlank(dto.getDescription())) {
                predicates.add(cb.equal(root.get("description"), dto.getDescription()));
            }
            Join<Task, User> userJoin = root.join("user");
            if (dto.getUserId() != null) {
                predicates.add(cb.equal(userJoin.get("id"), dto.getUserId()));
            }
            if(dto.getCompleted() != null) {
                predicates.add(cb.equal(root.get("completed"), dto.getCompleted()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
