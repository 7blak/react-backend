package pw.react.backend.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHelper {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHelper.class);

    @ExceptionHandler(value = { InvalidFileException.class })
    public ResponseEntity<ExceptionDetails> handleNotFound(InvalidFileException ex) {
        log.error("Invalid Input Exception: {}", ex.getMessage());
        return new ResponseEntity<>(new ExceptionDetails(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("Resource Not Found Exception: {}", ex.getMessage());
        return new ResponseEntity<>(new ExceptionDetails(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { UnauthorizedException.class})
    public ResponseEntity<ExceptionDetails> handleUnauthorized(UnauthorizedException ex) {
        log.error("Unauthorized Exception: {}", ex.getMessage());
        ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.UNAUTHORIZED, ex.getMessage());
        exceptionDetails.setPath(ex.getPath());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { UserValidationException.class })
    public ResponseEntity<ExceptionDetails> UserValidationException(UserValidationException ex) {
        log.error("User Validation Exception: {}", ex.getMessage());
        ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.BAD_REQUEST, ex.getMessage());
        exceptionDetails.setPath(ex.getResourcePath());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

}
