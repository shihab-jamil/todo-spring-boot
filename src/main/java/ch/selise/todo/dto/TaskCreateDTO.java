package ch.selise.todo.dto;

import ch.selise.todo.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
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
public class TaskCreateDTO {
    @NotBlank
    private String description;
    private OffsetDateTime dueDate;
    private Boolean completed;
    @JsonIgnore
    private Long userId;

    @JsonIgnore
    public Task getTask() {
        return new Task(description, dueDate, completed);
    }
}
