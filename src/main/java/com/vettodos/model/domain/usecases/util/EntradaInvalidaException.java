package com.vettodos.model.domain.usecases.util;

public class EntradaInvalidaException extends RuntimeException {
    public EntradaInvalidaException(String msg) {
        super(msg);
    }
}
