package com.bruno.events.Controllers;

import com.bruno.events.exceptions.ApiErrors;
import com.bruno.events.exceptions.ColaboradorJaEstaRegistrado;
import com.bruno.events.exceptions.ColaboradorNaoEncontrado;
import com.bruno.events.exceptions.EventoNaoEncontrado;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        List<String> erros = e
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ApiErrors(erros);
    }

    @ExceptionHandler(ColaboradorJaEstaRegistrado.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors colaboradorJaEstaRegistrado(ColaboradorJaEstaRegistrado e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(ColaboradorNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors colaboradorNaoEncontrado(ColaboradorNaoEncontrado e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(EventoNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors eventoNaoEncontrado(EventoNaoEncontrado e){
        return new ApiErrors(e.getMessage());
    }
}
