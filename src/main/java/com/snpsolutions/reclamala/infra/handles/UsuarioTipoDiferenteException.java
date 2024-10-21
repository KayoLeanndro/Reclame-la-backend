package com.snpsolutions.reclamala.infra.handles;

public class UsuarioTipoDiferenteException extends RuntimeException {
    public UsuarioTipoDiferenteException(String mensagem) {
        super(mensagem);
    }
}
