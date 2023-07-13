package ch.selise.todo.controller;

import ch.selise.todo.dto.TaskResponseDTO;
import ch.selise.todo.dto.UserCreateDTO;
import ch.selise.todo.dto.UserResponseDTO;
import ch.selise.todo.entity.Task;
import ch.selise.todo.entity.User;
import ch.selise.todo.service.TaskService;
import ch.selise.todo.service.UserService;
import ch.selise.todo.util.SelisePage;
import ch.selise.todo.util.SelisePageResponse;
import jakarta.validation.Valid;
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
@RequestMapping("/admin/")
public class AdminController {
    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("users")
    public SelisePageResponse<UserResponseDTO> get(SelisePage paging) {
        Page<User> data = userService.get(paging);
        return paging.getResponse(
                data.getContent()
                        .stream()
                        .map(UserResponseDTO::new)
                        .toList(),
                data);
    }

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO create(@Valid @RequestBody UserCreateDTO createDTO) {
        return new UserResponseDTO(userService.create(createDTO));
    }

    @GetMapping("tasks")
    @ResponseStatus(HttpStatus.OK)
    public SelisePageResponse<TaskResponseDTO> get(
            @Nullable @RequestParam Boolean completed,
            SelisePage paging
    ) {
        Page<Task> data;
        if (completed == null)
            data = taskService.get(paging);
        else data = taskService.getByStatus(completed, paging);

        return paging.getResponse(
                data.getContent()
                        .stream()
                        .map(TaskResponseDTO::get)
                        .toList(),
                data);
    }

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void get(){
        taskService.create();

    }

}
