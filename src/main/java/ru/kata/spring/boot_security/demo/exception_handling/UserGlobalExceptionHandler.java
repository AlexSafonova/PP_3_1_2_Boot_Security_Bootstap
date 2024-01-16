package ru.kata.spring.boot_security.demo.exception_handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(UserNotExistException e) {
        UserIncorrectData data = new UserIncorrectData();
        data.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(data);
    }
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(UserAlreadyExistException e) {
        UserIncorrectData data = new UserIncorrectData();
        data.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(data);
    }
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(Exception e) {
        UserIncorrectData data = new UserIncorrectData();
        data.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(data);
    }

}
