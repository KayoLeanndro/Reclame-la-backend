package com.snpsolutions.reclamala.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snpsolutions.reclamala.domain.enums.ComentarioTipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @Column(name = "cmtr_id")
    private Long id;

    @Column(name = "titulo_comentario", nullable = false)
    private String tituloComentario;

    @Column(name = "conteudo_comentario", nullable = false)
    private String conteudoComentario;

    @Column(name = "qtd_curtidas", nullable = false)
    private Integer qtdCurtidas;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_comentario", nullable = false)
    private ComentarioTipo categoriaComentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "usur_id", nullable = false)
    private Usuario usuarioComentario;

    @OneToMany(mappedBy = "comentario", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Resposta> respostaComentario;

    @Column(name = "data_criacao_comentario")
    private LocalDateTime dataCriacaoComentario;

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