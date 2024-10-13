package com.snpsolutions.reclamala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snpsolutions.reclamala.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByMatricula(Integer matricula);
}
