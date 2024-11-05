package com.snpsolutions.reclamala.domain.entities;

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
@Table(name = "respostas")
public class Resposta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rspt_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cmtr_id", nullable = false)
    private Comentario comentario;

    @Column(name = "cntd_resposta")
    private String conteudoResposta;

    @Column(name = "qtd_curtidas")
    private Integer qtdCurtidas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usur_id", nullable = false)
    private Usuario usuarioComentario;

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
        Resposta other = (Resposta) obj;
        return Objects.equals(id, other.id);
    }
}