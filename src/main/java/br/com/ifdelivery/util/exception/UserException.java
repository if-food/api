package br.com.ifdelivery.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserException extends RuntimeException {

    public static final String MSG_EMAIL_ALREADY_EXISTS = "Email já cadastrado";

    public UserException(String message) {
        super(message); // Simplified constructor
    }
}