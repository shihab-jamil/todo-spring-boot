package ch.selise.todo.dto;

import ch.selise.todo.entity.Task;

import java.time.LocalDate;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.dto
 */
public interface TaskResponseDTO {
    Long getId();

    String getDescription();

    LocalDate getDueDate();

    Boolean getCompleted();

    TaskUser getUser();

    interface TaskUser {
        Long getId();

        String getName();
    }

    static TaskResponseDTO get(Task task) {
        return new TaskResponseDTO() {
            @Override
            public Long getId() {
                return task.getId();
            }

            @Override
            public String getDescription() {
                return task.getDescription();
            }

            @Override
            public LocalDate getDueDate() {
                return task.getDueDate().toLocalDate();
            }

            @Override
            public Boolean getCompleted() {
                return task.getCompleted();
            }

            @Override
            public TaskUser getUser() {
                if (task.getUser() != null)
                    return new TaskUser() {
                        @Override
                        public Long getId() {
                            return task.getUser().getId();
                        }

                        @Override
                        public String getName() {
                            return task.getUser().getFirstName() + " " + task.getUser().getLastName();
                        }
                    };
                return null;
            }
        };
    }
}
