package com.snpsolutions.reclamala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snpsolutions.reclamala.entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
}
