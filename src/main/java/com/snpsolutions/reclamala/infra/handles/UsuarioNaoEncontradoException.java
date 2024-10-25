package com.snpsolutions.reclamala.infra.handles;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}