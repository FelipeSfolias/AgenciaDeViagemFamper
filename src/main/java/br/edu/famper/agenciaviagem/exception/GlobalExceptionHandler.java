package br.edu.famper.agenciaviagem.exception;

import br.edu.famper.agenciaviagem.exception.ErrorResponse;
import br.edu.famper.agenciaviagem.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request){
        ErrorResponse errorDetails = new ErrorResponse(
                new Date(),
                HttpStatus.NOT_FOUND.toString(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globeExceptionHandler(
            Exception ex,
            WebRequest request){
        ErrorResponse errorDetails = new ErrorResponse(
                new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}