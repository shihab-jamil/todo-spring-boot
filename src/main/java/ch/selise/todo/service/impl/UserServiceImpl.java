package ch.selise.todo.service.impl;

import ch.selise.todo.dao.UserRepository;
import ch.selise.todo.dto.LoginDTO;
import ch.selise.todo.dto.UserCreateDTO;
import ch.selise.todo.dto.UserUpdateDTO;
import ch.selise.todo.entity.User;
import ch.selise.todo.exception.ExBadRequest;
import ch.selise.todo.exception.ExDataExist;
import ch.selise.todo.exception.ExForbidden;
import ch.selise.todo.exception.ExNotFound;
import ch.selise.todo.service.UserService;
import ch.selise.todo.util.SelisePage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.service.impl
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public org.springframework.data.domain.Page<User> get(SelisePage paging) {
        if (paging.getFilter() != null)
            return repository.findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(paging.getFilter()+'%', paging.getFilter()+'%', paging.getPageable());
        return repository.findAll(paging.getPageable());
    }

    @Override
    public User get(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new ExNotFound("User not found for user name " + username));
    }

    @Override
    public User create(UserCreateDTO user) {
        //validate user
        validateUser(user);

        //check user already exist for username or email
        if (repository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).isPresent())
            throw new ExDataExist("User already exist with username or email");

        //crate the user and return it
        return repository.save(user.getUser());
    }

    @Override
    public User update(UserUpdateDTO user) {
        User currentUser = getById(user.getId());
        return repository.save(user.getUser(currentUser));
    }

    @Override
    public User getById(Long userId) {
        User user = repository.findById(userId).orElse(null);
        if (user == null)
            throw new ExNotFound("User not found with id " + userId);

        return user;
    }

    @Override
    public User login(LoginDTO loginDTO) {
        User user = getByNameOrEmail(loginDTO.getUsername());
        if (!user.getPassword().equals(loginDTO.getPassword()))
            throw new ExBadRequest("Password don't match");
        if (!user.getStatus().equals(User.UserStatus.ACTIVE))
            throw new ExForbidden("Account is not active");
        return user;
    }

    private void validateUser(UserCreateDTO dto) {
        int years = Period.between(
                        dto.getDateOfBirth().toLocalDate(),
                        LocalDate.now())
                .getYears();
        if (years < 18)
            throw new ExBadRequest("You must be 18 years old");
        //remove space from username
        dto.setUsername(dto.getUsername().replaceAll("\\s", ""));

        if (!dto.getPassword().equals(dto.getRepeatPassword()))
            throw new ExBadRequest("Password did not matched");
    }

    private User getByNameOrEmail(String nameOrEmail) {
        return repository
                .findByUsernameOrEmail(nameOrEmail, nameOrEmail)
                .orElseThrow(() -> new ExNotFound("User not found"));
    }
}
