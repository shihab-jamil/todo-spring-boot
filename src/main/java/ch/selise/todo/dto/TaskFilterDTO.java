package ch.selise.todo.dto;

import lombok.Data;

/**
 * @author Rhidoy
 * @created 12/07/2023
 * @package ch.selise.todo.dto
 */
@Data
public class TaskFilterDTO {
    private String description;
    private Long userId;
    private Boolean completed;

}
