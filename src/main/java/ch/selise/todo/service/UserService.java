package ch.selise.todo.service;

import ch.selise.todo.dto.LoginDTO;
import ch.selise.todo.dto.UserCreateDTO;
import ch.selise.todo.dto.UserUpdateDTO;
import ch.selise.todo.entity.User;
import ch.selise.todo.util.SelisePage;
import org.springframework.data.domain.Page;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.service
 */
public interface UserService {

    Page<User> get(SelisePage paging);

    User get(String username);

    User create(UserCreateDTO user);

    User update(UserUpdateDTO user);

    User getById(Long userId);

    User login(LoginDTO loginDTO);
}
