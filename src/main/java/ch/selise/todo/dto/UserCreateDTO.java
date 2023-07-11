package ch.selise.todo.dto;

import ch.selise.todo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserCreateDTO extends UserBaseDTO {
    @NotNull
    private OffsetDateTime dateOfBirth;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;

    @JsonIgnore
    public User getUser() {
        return new User(username, email, dateOfBirth, firstName, lastName, password, gender, status);
    }

}
