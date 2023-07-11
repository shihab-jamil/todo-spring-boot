package ch.selise.todo.dto;

import ch.selise.todo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserResponseDTO extends UserBaseDTO {
    private Long id;
    private LocalDate dateOfBirth;

    public UserResponseDTO(User user) {
        super(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getGender(), user.getStatus());
        this.id = user.getId();
        this.dateOfBirth = user.getDateOfBirth().toLocalDate();
    }
}
