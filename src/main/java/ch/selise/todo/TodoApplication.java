package ch.selise.todo;

import ch.selise.todo.dao.TaskRepository;
import ch.selise.todo.dao.UserRepository;
import ch.selise.todo.entity.Task;
import ch.selise.todo.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.OffsetDateTime;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

    private final UserRepository repository;
    private final TaskRepository taskRepository;

    public TodoApplication(UserRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //add admin user

        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setGender(User.Gender.MALE);
        user.setPassword("111111");
        user.setUsername("admin");
        user.setFirstName("Super");
        user.setLastName("Admin");
        user.setStatus(User.UserStatus.ACTIVE);
        user.setDateOfBirth(OffsetDateTime.now());
        this.repository.save(user);

        //add users
        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setEmail("example" + i + "@gmail.com");
            user.setGender(User.Gender.MALE);
            user.setPassword("111111");
            user.setUsername("user" + i);
            user.setFirstName("User");
            user.setLastName(i + "");
            user.setDateOfBirth(OffsetDateTime.now());
            this.repository.save(user);
            Task task = new Task("Test Task 1 for user " + user.getUsername(), OffsetDateTime.now(), Boolean.FALSE);
            task.setUser(user);
            taskRepository.save(task);

            task = new Task("Test Task 2 for user " + user.getUsername(), OffsetDateTime.now(), Boolean.FALSE);
            task.setUser(user);
            taskRepository.save(task);
        }


    }
}
