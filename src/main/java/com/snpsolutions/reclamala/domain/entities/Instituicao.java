package com.snpsolutions.reclamala.domain.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instituicoes")
public class Instituicao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instituicao_id")
    private Long id;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "email", nullable = false)
    private  String email;

    @Column(name = "telefone", nullable = false, unique = false)
    private String telefone;

    @Column(name = "nome", nullable = false, unique = false)
    private String nome;

    @Column(name = "password", nullable = false)
    private String password;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Instituicao other = (Instituicao) obj;
        return Objects.equals(id, other.id);
    }
}
