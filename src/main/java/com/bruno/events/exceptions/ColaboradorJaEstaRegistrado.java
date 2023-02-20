package com.bruno.events.exceptions;

public class ColaboradorJaEstaRegistrado extends RuntimeException{
    public ColaboradorJaEstaRegistrado(String message) {
        super(message);
    }
}
