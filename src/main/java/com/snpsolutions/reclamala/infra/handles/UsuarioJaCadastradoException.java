package com.snpsolutions.reclamala.infra.handles;

public class UsuarioJaCadastradoException extends RuntimeException {
    public UsuarioJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
