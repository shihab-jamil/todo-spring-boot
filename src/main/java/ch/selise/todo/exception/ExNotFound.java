package ch.selise.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.exception
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExNotFound extends RuntimeException {

    public ExNotFound(String message) {
        super(message);
    }
}
