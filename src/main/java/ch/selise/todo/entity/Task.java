package ch.selise.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.entity
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    private OffsetDateTime dueDate;
    private Boolean completed;

    @ManyToOne(cascade={CascadeType.DETACH})
    private User user;

    public Task(String description, OffsetDateTime dueDate, Boolean completed) {
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }
}
