package com.vettodos.model.domain.usecases.util;

public class EntidadeJaExistenteException extends RuntimeException {
    public EntidadeJaExistenteException(String msg) {
        super(msg);
    }
}
