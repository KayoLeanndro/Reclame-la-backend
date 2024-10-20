package com.snpsolutions.reclamala.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.snpsolutions.reclamala.repositories.UsuarioRepository;
import com.snpsolutions.reclamala.domain.dtos.UsuarioDTO;
import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.domain.enums.UsuarioTipo;
import com.snpsolutions.reclamala.infra.handles.UsuarioJaCadastradoException;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario cadastrarUsuarioAluno(UsuarioDTO usuarioDTO) {

        if (usuarioRepository.existsByMatricula(usuarioDTO.getMatricula())) {
            throw new UsuarioJaCadastradoException("Usuário com matrícula " + usuarioDTO.getMatricula() + " já cadastrado.");
        }
        if (usuarioRepository.existsByUsuarioCpf(usuarioDTO.getUsuarioCpf())) {
            throw new UsuarioJaCadastradoException("Usuário com CPF " + usuarioDTO.getUsuarioCpf() + " já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setMatricula(usuarioDTO.getMatricula());
        usuario.setUsuarioCpf(usuarioDTO.getUsuarioCpf());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setTipoUsuario(UsuarioTipo.ALUNO);


        return usuarioRepository.save(usuario);
    }


}
