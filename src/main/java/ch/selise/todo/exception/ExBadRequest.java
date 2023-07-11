package ch.selise.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.exception
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExBadRequest extends RuntimeException {

    public ExBadRequest(String message) {
        super(message);
    }
}
