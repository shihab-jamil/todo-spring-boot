package ch.selise.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.entity
 */
@Entity(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private OffsetDateTime dateOfBirth;
    private String firstName;
    private String lastName;
    private Gender gender;
    private UserStatus status = UserStatus.PENDING;
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();

    public User(String username, String email, OffsetDateTime dateOfBirth, String firstName, String lastName, String password, Gender gender) {
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.password = password;
    }

    public User(String username, String email, OffsetDateTime dateOfBirth, String firstName, String lastName, String password, Gender gender, UserStatus status) {
        this(username, email, dateOfBirth, firstName, lastName, password, gender);
        this.status = status;
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    public enum UserStatus {
        ACTIVE,
        PENDING,
        DISABLED
    }
}
