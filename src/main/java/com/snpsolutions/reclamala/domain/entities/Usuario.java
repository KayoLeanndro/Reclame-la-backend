package com.snpsolutions.reclamala.domain.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.snpsolutions.reclamala.domain.enums.CursoTipo;
import com.snpsolutions.reclamala.domain.enums.Instituicao;
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

    @Column(name = "email", nullable = false)
    private  String email;

    @Column(name = "usuario_cpf", nullable = false, unique = true)
    private String usuarioCpf;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false) 
    private UsuarioTipo tipoUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "aluno_curso", nullable = false)
    public CursoTipo cursoTipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "instituicao", nullable = false)
    public Instituicao Instituicao;

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
