package com.snpsolutions.reclamala.application.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snpsolutions.reclamala.domain.entities.Comentario;
import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.domain.enums.ComentarioTipo;
import com.snpsolutions.reclamala.domain.repositories.ComentarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> listarComentarios() {
        return comentarioRepository.findAll();
    }

    public List<Comentario> listarComentariosPorUsuario(Usuario usuario) {
        return comentarioRepository.findByUsuarioComentario(usuario);
    }

    public List<Comentario> listarComentarioPorCategoria(ComentarioTipo comentarioTipo){
        return comentarioRepository.buscarComentarioPorCategoria(comentarioTipo);

    }

    @Transactional
    public Comentario salvarComentario(Comentario comentario) {
        comentario.setDataCriacaoComentario(java.time.LocalDateTime.now());
        comentario.setQtdCurtidas(0); 
        return comentarioRepository.save(comentario);
    }

    @Transactional
    public Comentario atualizarComentario(Long id, Comentario comentarioAtualizado) {
        return comentarioRepository.findById(id).map(comentario -> {
            comentario.setTituloComentario(comentarioAtualizado.getTituloComentario());
            comentario.setConteudoComentario(comentarioAtualizado.getConteudoComentario());
            comentario.setCategoriaComentario(comentarioAtualizado.getCategoriaComentario());
            return comentarioRepository.save(comentario);
        }).orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
    }

    @Transactional
    public void excluirComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    @Transactional
    public void curtirComentario(Long id) {
        comentarioRepository.findById(id).ifPresent(comentario -> {
            comentario.setQtdCurtidas(comentario.getQtdCurtidas() + 1);
            comentarioRepository.save(comentario);
        });
    }
}
