package sandbox.components.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sandbox.components.store.InvalidRecordException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidRecordException.class)
  public ResponseEntity<Outcome<String>> handle(InvalidRecordException e) {
    Outcome<String> result = Outcome.failure(e.getMessage());
    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }
}
