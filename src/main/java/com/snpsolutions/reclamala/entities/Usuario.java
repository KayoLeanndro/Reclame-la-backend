package com.snpsolutions.reclamala.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "tipo_usuario", nullable = false) 
    private String tipoUsuario; 

    public UsuarioTipo getTipoUsuario() {
        return new UsuarioTipo(tipoUsuario);
    }

    public void setTipoUsuario(UsuarioTipo tipoUsuario) {
        this.tipoUsuario = tipoUsuario.getUsuarioTipo();
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
        Usuario other = (Usuario) obj;
        return Objects.equals(id, other.id);
    }
}
