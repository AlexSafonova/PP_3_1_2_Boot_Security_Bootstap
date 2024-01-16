package ru.kata.spring.boot_security.demo.exception_handling;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
