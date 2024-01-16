package ru.kata.spring.boot_security.demo.exception_handling;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String message) {
        super(message);
    }
}
