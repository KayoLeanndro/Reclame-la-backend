package com.snpsolutions.reclamala.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snpsolutions.reclamala.domain.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByMatricula(Integer matricula);

    boolean existsByUsuarioCpf(String usuarioCpf);

    Optional<Usuario> findByMatricula(Integer matricula);
}
