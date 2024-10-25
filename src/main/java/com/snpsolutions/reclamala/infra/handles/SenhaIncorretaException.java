package com.snpsolutions.reclamala.infra.handles;

public class SenhaIncorretaException extends RuntimeException {
    public  SenhaIncorretaException(String mensagem) {
        super(mensagem);
    }

}