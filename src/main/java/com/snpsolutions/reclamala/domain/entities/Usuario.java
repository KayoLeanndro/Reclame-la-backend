package com.snpsolutions.reclamala.domain.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.snpsolutions.reclamala.domain.enums.UsuarioTipo;

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
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricula", nullable = false, unique = true)
    private Integer matricula;

    @Column(name = "usuario_cpf", nullable = false, unique = true)
    private String usuarioCpf;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false) 
    private UsuarioTipo tipoUsuario;

    @OneToMany(mappedBy = "usuarioComentario", fetch = FetchType.LAZY)
    private List<Comentario> comentarios;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(id, other.id);
    }
}
