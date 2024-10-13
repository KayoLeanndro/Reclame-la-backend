package com.snpsolutions.reclamala.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snpsolutions.reclamala.entities.Comentario;
import com.snpsolutions.reclamala.entities.Usuario;
import com.snpsolutions.reclamala.repositories.ComentarioRepository;
import com.snpsolutions.reclamala.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComentarioService {
    
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;

    public Comentario salvarComentario(Long usuarioId, Comentario comentario){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                          .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        comentario.setUsuarioComentario(usuario);

        return comentarioRepository.save(comentario);
    }

    public List<Comentario> listarComentarios(List<Comentario> listaComentarios){
        return comentarioRepository.findAll();
    }

    
    


}
