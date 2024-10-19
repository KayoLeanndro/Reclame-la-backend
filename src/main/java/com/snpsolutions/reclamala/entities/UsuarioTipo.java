package com.snpsolutions.reclamala.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class UsuarioTipo implements Serializable { 

    private String usuarioTipo;

    @JsonCreator
    public UsuarioTipo(@JsonProperty("usuarioTipo") String usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }

    public static final UsuarioTipo INSTITUICAO = new UsuarioTipo("instituicao");
    public static final UsuarioTipo ALUNO = new UsuarioTipo("aluno");
    public static final UsuarioTipo DESENVOLVEDOR = new UsuarioTipo("desenvolvedor");

    @Override
    public String toString() {
        return this.usuarioTipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioTipo)) return false;
        UsuarioTipo that = (UsuarioTipo) o;
        return usuarioTipo.equals(that.usuarioTipo);
    }

    @Override
    public int hashCode() {
        return usuarioTipo.hashCode();
    }
}
