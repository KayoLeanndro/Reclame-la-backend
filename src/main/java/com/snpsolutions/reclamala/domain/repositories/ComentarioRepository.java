package com.snpsolutions.reclamala.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snpsolutions.reclamala.domain.entities.Comentario;
import com.snpsolutions.reclamala.domain.entities.Usuario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByUsuarioComentario(Usuario usuario);
}
