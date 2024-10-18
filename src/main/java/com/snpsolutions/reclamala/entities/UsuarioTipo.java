package com.snpsolutions.reclamala.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UsuarioTipo {
    private String tipo; // Adicionando um campo de instância

    @JsonCreator
    public UsuarioTipo(@JsonProperty("tipo") String tipo) {
        this.tipo = tipo;
    }

    public static final UsuarioTipo INSTITUICAO = new UsuarioTipo("instituicao");
    public static final UsuarioTipo ALUNO = new UsuarioTipo("aluno");
    public static final UsuarioTipo DESENVOLVEDOR = new UsuarioTipo("desenvolvedor");
}
