package com.snpsolutions.reclamala.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class ComentarioTipo implements Serializable {

    private String comentarioCategoria;

    @JsonCreator
    public ComentarioTipo(@JsonProperty("comentarioTipo") String comentarioTipo) {
        this.comentarioCategoria = comentarioTipo;
    }

    public static final ComentarioTipo INFRAESTRUTURA = new ComentarioTipo("infraestrutura");
    public static final ComentarioTipo ATENDIMENTO = new ComentarioTipo("atendimento");
    public static final ComentarioTipo QUALIDADE_DIDATICA = new ComentarioTipo("qualidade_didatica");
    public static final ComentarioTipo RECURSOS_TECNOLOGICOS = new ComentarioTipo("recursos_tecnologicos");

    @Override
    public String toString() {
        return this.comentarioCategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComentarioTipo)) return false;
        ComentarioTipo that = (ComentarioTipo) o;
        return comentarioCategoria.equals(that.comentarioCategoria);
    }

    @Override
    public int hashCode() {
        return comentarioCategoria.hashCode();
    }
}
