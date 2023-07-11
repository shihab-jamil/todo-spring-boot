package ch.selise.todo.dto;

import ch.selise.todo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserUpdateDTO {
    @JsonIgnore
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private User.Gender gender;
    private User.UserStatus status;

    @JsonIgnore
    public User getUser(User currentUser) {
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        if (gender != null)
            currentUser.setGender(gender);
        if (status != null)
            currentUser.setStatus(status);
        return currentUser;
    }
}
