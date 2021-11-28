package dev.husein.quinots.controller;

import dev.husein.quinots.exception.QuinotsException;
import dev.husein.quinots.model.ResponseError;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handleMissingQueryParamException(MissingServletRequestParameterException e) {
        ResponseError error = new ResponseError("Missing request parameters");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuinotsException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handleInvalidQueryParamException(QuinotsException e) {
        ResponseError error = new ResponseError(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handle(NumberFormatException e) {
        ResponseError error = new ResponseError("Enter a correct numerical value");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handle(EmptyResultDataAccessException e) {
        ResponseError error = new ResponseError("Note with this ID doesn't exist.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handle(HttpMessageNotReadableException e) {
        ResponseError error = new ResponseError("Incorrect request body.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseError> requestHandlingNoHandlerFound(NoHandlerFoundException e) {
        ResponseError error = new ResponseError("Incorrect Path.");
        return new ResponseEntity<>(error,  HttpStatus.NOT_FOUND);
    }


}
