package com.shawn.supervoting.adviser;


import com.shawn.supervoting.exception.DuplicateEntryException;
import com.shawn.supervoting.exception.EntryNotFoundException;
import com.shawn.supervoting.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponse> handleEntryNotFoundException(EntryNotFoundException e){
        return new ResponseEntity<>(
                new StandardResponse(404,e.getMessage(),e), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<StandardResponse> handleDuplicateEntryException(DuplicateEntryException e){
        return new ResponseEntity<>(
                new StandardResponse(409,e.getMessage(),e), HttpStatus.CONFLICT
        );
    }
}
