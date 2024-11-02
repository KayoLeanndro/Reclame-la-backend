package com.snpsolutions.reclamala.application.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snpsolutions.reclamala.domain.dtos.ComentarioDTO;
import com.snpsolutions.reclamala.domain.entities.Comentario;
import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.domain.enums.ComentarioTipo;
import com.snpsolutions.reclamala.domain.repositories.ComentarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Comentario> listarComentarios() {
        return comentarioRepository.findAll();
    }

    public List<Comentario> listarComentariosPorUsuario(Usuario usuario) {
        return comentarioRepository.findByUsuarioComentario(usuario);
    }

    public List<Comentario> listarComentarioPorCategoria(ComentarioTipo comentarioTipo) {
        return comentarioRepository.findByCategoriaComentario(comentarioTipo);
    }

    @Transactional
    public Comentario criarComentario(ComentarioDTO comentarioDTO) {

        Usuario usuario = usuarioService.buscarUsuarioPorMatricula(comentarioDTO.getMatriculaUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado."));

        
        Comentario novoComentario = new Comentario();
        novoComentario.setTituloComentario(comentarioDTO.getTituloComentario());
        novoComentario.setConteudoComentario(comentarioDTO.getConteudoComentario());
        novoComentario.setQtdCurtidas(0);
        novoComentario.setCategoriaComentario(ComentarioTipo.valueOf(comentarioDTO.getCategoriaComentario().toUpperCase()));
        novoComentario.setUsuarioComentario(usuario);
        novoComentario.setDataCriacaoComentario(LocalDateTime.now());

        // Salvar o comentário
        return comentarioRepository.save(novoComentario);

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
