package dev.husein.quinots.controller;

import dev.husein.quinots.exception.QuinotsException;
import dev.husein.quinots.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.client.HttpServerErrorException;


@ControllerAdvice
public class ExceptionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionsController.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMissingQueryParamException(MissingServletRequestParameterException e) {
        ErrorResponse error = new ErrorResponse("Missing request parameters");
        LOGGER.error(error.getErrMsg());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuinotsException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidQueryParamException(QuinotsException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage());
        LOGGER.error(error.getErrMsg());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(NumberFormatException e) {
        ErrorResponse error = new ErrorResponse("Enter a correct numerical value");
        LOGGER.error(error.getErrMsg());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(EmptyResultDataAccessException e) {
        ErrorResponse error = new ErrorResponse("Note with this ID doesn't exist.");
        LOGGER.error(error.getErrMsg());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException e) {
        ErrorResponse error = new ErrorResponse("Incorrect request body," + e.getMessage());
        LOGGER.error(error.getErrMsg());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> requestHandlingNoHandlerFound(NoHandlerFoundException e) {
        ErrorResponse error = new ErrorResponse("Incorrect Path.");
        LOGGER.error(error.getErrMsg());
        return new ResponseEntity<>(error,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handle(HttpServerErrorException e) {
        ErrorResponse error = new ErrorResponse("Internal server error, " + e.getMessage());
        LOGGER.error(error.getErrMsg());
        return new ResponseEntity<>(error,  HttpStatus.NOT_FOUND);
    }


}
