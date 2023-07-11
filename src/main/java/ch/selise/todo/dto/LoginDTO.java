package ch.selise.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
