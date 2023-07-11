package ch.selise.todo.controller;

import ch.selise.todo.dto.TaskCreateDTO;
import ch.selise.todo.dto.TaskResponseDTO;
import ch.selise.todo.dto.TaskStatusUpdateDTO;
import ch.selise.todo.dto.TaskUpdateDTO;
import ch.selise.todo.entity.Task;
import ch.selise.todo.service.TaskService;
import ch.selise.todo.util.SelisePage;
import ch.selise.todo.util.SelisePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/tasks")
public class TaskController {
    private final TaskService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO create(
            @PathVariable Long userId,
            @RequestBody TaskCreateDTO createDTO
    ) {
        createDTO.setUserId(userId);
        return TaskResponseDTO.get(service.create(createDTO));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SelisePageResponse<TaskResponseDTO> get(
            @PathVariable Long userId,
            @Nullable @RequestParam Boolean completed,
            SelisePage paging
    ) {
        Page<Task> data;
        if (completed == null)
            data = service.getUserTask(userId, paging);

        else data = service.getUserTaskByStatus(userId, completed, paging);

        return paging.getResponse(
                data.getContent()
                        .stream()
                        .map(TaskResponseDTO::get)
                        .toList(),
                data);
    }

    @PutMapping("/{taskId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskResponseDTO update(
            @PathVariable Long userId,
            @PathVariable Long taskId,
            @RequestBody TaskUpdateDTO updateDTO
    ) {
        updateDTO.setId(taskId);
        updateDTO.setUserId(userId);
        return TaskResponseDTO.get(service.update(updateDTO));
    }

    @PutMapping("/{taskId}/complete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskResponseDTO markAsComplete(
            @PathVariable Long userId,
            @PathVariable Long taskId
    ) {
        return TaskResponseDTO.get(service.updateTaskStatus(
                new TaskStatusUpdateDTO(true, userId, taskId)
        ));
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean delete(
            @PathVariable Long userId,
            @PathVariable Long taskId
    ) {
        return service.deleteTask(userId, taskId);
    }

}
