package ch.selise.todo.controller;

import ch.selise.todo.dto.LoginDTO;
import ch.selise.todo.dto.UserCreateDTO;
import ch.selise.todo.dto.UserResponseDTO;
import ch.selise.todo.dto.UserUpdateDTO;
import ch.selise.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @GetMapping("/{username}")
    public UserResponseDTO get(@PathVariable String username) {
        return new UserResponseDTO(service.get(username));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO register(@Valid @RequestBody UserCreateDTO createDTO) {
        return new UserResponseDTO(service.create(createDTO));
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponseDTO update(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateDTO updateDTO
    ) {
        updateDTO.setId(userId);
        return new UserResponseDTO(service.update(updateDTO));
    }

    @PostMapping("/login")
    public UserResponseDTO login(@Valid @RequestBody LoginDTO loginDTO) {
        return new UserResponseDTO(service.login(loginDTO));
    }

}
