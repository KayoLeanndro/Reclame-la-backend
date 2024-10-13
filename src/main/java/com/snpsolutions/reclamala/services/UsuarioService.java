package com.snpsolutions.reclamala.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.snpsolutions.reclamala.entities.Usuario;
import com.snpsolutions.reclamala.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario){
       if(usuarioRepository.existsByMatricula(usuario.getMatricula())){
            throw new RuntimeException("Matricula: " + usuario.getMatricula()  + " Já existe");
       }
       return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll(); 
    }

    public boolean matriculaExistente(Integer matricula) {
        return usuarioRepository.existsByMatricula(matricula);
    }

}
