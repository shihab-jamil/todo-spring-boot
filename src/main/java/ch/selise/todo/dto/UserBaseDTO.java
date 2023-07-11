package ch.selise.todo.dto;

import ch.selise.todo.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class UserBaseDTO {
    @NotBlank
    @Size(min = 5)
    protected String username;
    protected String email;
    @NotBlank
    protected String firstName;
    @NotBlank
    protected String lastName;
    protected User.Gender gender = User.Gender.MALE;
    protected User.UserStatus status = User.UserStatus.PENDING;
}
