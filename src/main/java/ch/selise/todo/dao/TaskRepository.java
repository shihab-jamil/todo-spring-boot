package ch.selise.todo.dao;

import ch.selise.todo.entity.Task;
import jakarta.persistence.LockModeType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.dao
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Page<Task> findByUserId(Long userId, Pageable pageable);
    Page<Task> findByUserId(Specification<Task> query, Pageable pageable);

    Page<Task> findByUserIdAndCompleted(Long userId, Boolean status, Pageable pageable);

    Page<Task> findAll(Pageable pageable);
    Page<Task> findAll(Specification<Task> query, Pageable pageable);

    @Query(
            value = "select t from Task t where t.completed = :status",
            countQuery = "select count(1) from Task where completed = :status"
    )
    Page<Task> findByCompleted(Boolean status, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Task findTaskById(@NotNull Long id);
}
