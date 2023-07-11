package ch.selise.todo.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler implements RequestBodyAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String unKnownException(
            Exception exception,
            HandlerMethod handlerMethod,
            HttpServletRequest request) {
        printLog(handlerMethod, request, exception);
        return exception.getMessage();
    }

    @ExceptionHandler(ExForbidden.class)
    @ResponseStatus(FORBIDDEN)
    public String unKnownException(
            ExForbidden exception,
            HandlerMethod handlerMethod,
            HttpServletRequest request) {
        printLog(handlerMethod, request, exception);
        return exception.getMessage();
    }

    @ExceptionHandler(ExBadRequest.class)
    @ResponseStatus(BAD_REQUEST)
    public String unKnownException(
            ExBadRequest exception,
            HandlerMethod handlerMethod,
            HttpServletRequest request) {
        printLog(handlerMethod, request, exception);
        return exception.getMessage();
    }

    @ExceptionHandler(ExDataExist.class)
    @ResponseStatus(CONFLICT)
    public String unKnownException(
            ExDataExist exception,
            HandlerMethod handlerMethod,
            HttpServletRequest request) {
        printLog(handlerMethod, request, exception);
        return exception.getMessage();
    }

    @ExceptionHandler(ExNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public String unKnownException(
            ExNotFound exception,
            HandlerMethod handlerMethod,
            HttpServletRequest request) {
        printLog(handlerMethod, request, exception);
        return exception.getMessage();
    }

    //for handling validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Map<String, String> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HandlerMethod handlerMethod,
            HttpServletRequest request
    ) {
        printLog(handlerMethod, request, exception);
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }

    //for handling enum error
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public Object handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HandlerMethod handlerMethod,
            HttpServletRequest request
    ) {
        printLog(handlerMethod, request, exception);
        String errorDetails = "Unacceptable JSON " + exception.getMessage();

        if (exception.getCause() instanceof InvalidFormatException ifx) {
            if (ifx.getTargetType() != null && ifx.getTargetType().isEnum()) {
                errorDetails = String.format("'%s' for the field: '%s'. The value must be one of: %s.",
                        ifx.getValue(), ifx.getPath().get(ifx.getPath().size() - 1).getFieldName(), Arrays.toString(ifx.getTargetType().getEnumConstants()));
            }
        }
        Map<String, String> errors = new HashMap<>();
        errors.put("Invalid Enum Value", errorDetails);
        return errors;
    }

    private void printLog(HandlerMethod handlerMethod,
                          HttpServletRequest request, Exception exception) {
        Logger.getLogger(RestExceptionHandler.class.getName())
                .info("Error happened " + exception.getMessage());
    }

    @Override
    public boolean supports(@NotNull MethodParameter methodParameter, @NotNull Type targetType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    @NotNull
    public HttpInputMessage beforeBodyRead(@NotNull HttpInputMessage inputMessage, @NotNull MethodParameter parameter, @NotNull Type targetType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return inputMessage;
    }

    @Override
    @NotNull
    public Object afterBodyRead(@NotNull Object body, @NotNull HttpInputMessage inputMessage, @NotNull MethodParameter parameter, @NotNull Type targetType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, @NotNull HttpInputMessage inputMessage, @NotNull MethodParameter parameter, @NotNull Type targetType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
