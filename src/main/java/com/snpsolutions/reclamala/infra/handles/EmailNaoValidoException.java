package com.snpsolutions.reclamala.infra.handles;

public class EmailNaoValidoException extends RuntimeException {
    public EmailNaoValidoException(String mensagem) {
        super(mensagem);
    }
}
