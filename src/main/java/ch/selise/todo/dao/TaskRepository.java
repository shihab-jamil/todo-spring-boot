package ch.selise.todo.dao;

import ch.selise.todo.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Task> findByUserIdAndCompleted(Long userId, Boolean status, Pageable pageable);

    Page<Task> findAll(Pageable pageable);

    Page<Task> findByCompleted(Boolean status, Pageable pageable);
}
