package com.snpsolutions.reclamala.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.google.cloud.firestore.DocumentReference;
import com.snpsolutions.reclamala.enums.CategoriaComentario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comentario implements Serializable {

    private String id; 

    private String tituloComentario;

    private String conteudoComentario;

    private Integer qtdCurtidas;

    private CategoriaComentario categoriaComentario;

    private DocumentReference usuarioComentario; 

    private LocalDateTime dataCriacaoComentario;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comentario other = (Comentario) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
