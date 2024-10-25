package com.snpsolutions.reclamala.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snpsolutions.reclamala.domain.entities.Comentario;
import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.domain.enums.ComentarioTipo;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Optional<Comentario> buscarComentarioPorCategoria(ComentarioTipo comentarioTipo);

    List<Comentario> findByUsuarioComentario(Usuario usuario);
}
