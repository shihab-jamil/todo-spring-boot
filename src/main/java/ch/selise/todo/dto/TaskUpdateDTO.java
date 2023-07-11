package ch.selise.todo.dto;

import ch.selise.todo.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TaskUpdateDTO extends TaskCreateDTO {
    @JsonIgnore
    private Long id;


    @JsonIgnore
    public Task getTask(Task task) {
        task.setDescription(getDescription());
        if (getDueDate() != null)
            task.setDueDate(getDueDate());
        if (getCompleted() != null)
            task.setCompleted(getCompleted());
        return task;
    }
}
