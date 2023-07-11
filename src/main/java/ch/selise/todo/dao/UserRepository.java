package ch.selise.todo.dao;

import ch.selise.todo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.dao
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Page<User> findAll(Pageable page);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Page<User> findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(String fName, String lName, Pageable pageable);
}
