package com.aws.netflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NotAuthorized extends RuntimeException {
    public NotAuthorized(String message){
        super(message);
    }
}
