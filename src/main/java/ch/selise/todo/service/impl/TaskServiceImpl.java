package ch.selise.todo.service.impl;

import ch.selise.todo.dao.TaskQuery;
import ch.selise.todo.dao.TaskRepository;
import ch.selise.todo.dto.*;
import ch.selise.todo.entity.Task;
import ch.selise.todo.entity.User;
import ch.selise.todo.exception.ExForbidden;
import ch.selise.todo.exception.ExNotFound;
import ch.selise.todo.service.TaskService;
import ch.selise.todo.service.UserService;
import ch.selise.todo.util.SelisePage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.service.impl
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final UserService userService;

    @SneakyThrows
    @Override
    public Task create(TaskCreateDTO task) {
        //check user existence
        User user = userService.getById(task.getUserId());
        Task currentTask = task.getTask();
        //add the user for the task
        currentTask.setUser(user);
        //crate the task and return it
        return repository.save(currentTask);
    }

    @Override
    public Task update(TaskUpdateDTO task) {
        Task currentTask = getById(task.getId());
        //validate task
        validateTaskUser(currentTask, task.getUserId());
        //update and return task
        return repository.save(task.getTask(currentTask));
    }

    @Override
    public Task updateTaskStatus(TaskStatusUpdateDTO updateDTO) {
        Task currentTask = getById(updateDTO.getId());
        //validate task
        validateTaskUser(currentTask, updateDTO.getUserId());
        currentTask.setCompleted(updateDTO.getCompleted());

        return repository.save(currentTask);
    }

    @Override
    public Page<Task> getUserTask(Long userId, SelisePage paging) {
        return repository.findByUserId(userId, paging.getPageable());
    }

    @Override
    public Page<Task> getUserTaskByStatus(
            Long userId,
            Boolean status,
            SelisePage paging
    ) {
        return repository.findByUserIdAndCompleted(userId, status, paging.getPageable());
    }

    @Override
    public Boolean deleteTask(Long userId, Long taskId) {
        Task currentTask = getById(taskId);
        //validate task
        validateTaskUser(currentTask, userId);
        repository.deleteById(taskId);
        return true;
    }

    @Override
    public Page<Task> get(SelisePage paging) {
//        return repository.findAll(paging.getPageable());
        return repository.findAll(TaskQuery.getQuery(new TaskFilterDTO()), paging.getPageable());
    }

    @Override
    public Page<Task> getByStatus(Boolean status, SelisePage paging) {
        return repository.findByCompleted(status, paging.getPageable());
    }

    boolean firstCall = true;

    @SneakyThrows
    @Override
    @Transactional
    public void create() {
        TaskCreateDTO dto = new TaskCreateDTO();
        dto.setUserId(1L);
        dto.setCompleted(true);
        dto.setDescription("Description");
        create(dto);
        throw new ExNotFound("not found");
    }

    private Task getById(Long id) {
        Task task = repository.findById(id).orElse(null);
        if (task == null)
            throw new ExNotFound("Task not found with id " + id);

        return task;
    }

    private void validateTaskUser(Task task, Long userId) {
        //check task belongs to the user or not
        if (!task.getUser().getId().equals(userId))
            throw new ExForbidden("User is not eligible to make change");
    }
}
