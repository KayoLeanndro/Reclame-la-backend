package com.snpsolutions.reclamala.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import com.snpsolutions.reclamala.enums.CategoriaComentario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "comentarios")
public class Comentario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmtr_id")
    private long id;

    @Column(name = "titulo_cmtr", nullable = false, length = 45)
    private String tituloComentario;

    @Column(name = "ctud_comentario", nullable = false, length = 255)
    private String conteudoComentario;

    @Column(name = "qtd_curtidas")
    private Integer qtdCurtidas;

    @Enumerated(EnumType.STRING)
    @Column(name = "cmtr_categoria", nullable = false)
    private CategoriaComentario categoriaComentario;

    @Column(name = "usur_comentario", nullable = false)
    private Usuario usuarioComentario;

    @Column(name = "dt_hr_cria_comentario")
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
