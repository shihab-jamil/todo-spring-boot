package ch.selise.todo.dto;

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
public class TaskStatusUpdateDTO {
    @NotBlank
    private Boolean completed;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private Long id;
}
