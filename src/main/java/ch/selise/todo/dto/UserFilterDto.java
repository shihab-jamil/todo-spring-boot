package ch.selise.todo.dto;

import lombok.Data;

@Data
public class UserFilterDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
