package ch.selise.todo.service;

import ch.selise.todo.dto.TaskCreateDTO;
import ch.selise.todo.dto.TaskStatusUpdateDTO;
import ch.selise.todo.dto.TaskUpdateDTO;
import ch.selise.todo.entity.Task;
import ch.selise.todo.util.SelisePage;
import org.springframework.data.domain.Page;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.service
 */
public interface TaskService {
    Task create(TaskCreateDTO task);

    Task update(TaskUpdateDTO task);

    Task updateTaskStatus(TaskStatusUpdateDTO updateDTO);

    Page<Task> getUserTask(Long userId, SelisePage paging);

    Page<Task> getUserTaskByStatus(Long userId, Boolean status, SelisePage paging);

    Boolean deleteTask(Long userId, Long taskId);

    Page<Task> get(SelisePage paging);

    Page<Task> getByStatus(Boolean status, SelisePage paging);
}
