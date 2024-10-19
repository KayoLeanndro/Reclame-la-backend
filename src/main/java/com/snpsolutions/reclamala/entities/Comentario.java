package com.snpsolutions.reclamala.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comentarios") 
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "titulo_comentario", nullable = false)
    private String tituloComentario;

    @Column(name = "conteudo_comentario", nullable = false)
    private String conteudoComentario;

    @Column(name = "qtd_curtidas", nullable = false)
    private Integer qtdCurtidas;

    @Column(name = "categoria_comentario", nullable = false) 
    private String categoriaComentario; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false) 
    private Usuario usuarioComentario;

    @Column(name = "data_criacao_comentario")
    private LocalDateTime dataCriacaoComentario;

    
    public ComentarioTipo getCategoriaComentario() {
        return new ComentarioTipo(categoriaComentario);
    }

    public void setCategoriaComentario(ComentarioTipo categoriaComentario) {
        this.categoriaComentario = categoriaComentario.getComentarioCategoria();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Comentario other = (Comentario) obj;
        return Objects.equals(id, other.id);
    }
}