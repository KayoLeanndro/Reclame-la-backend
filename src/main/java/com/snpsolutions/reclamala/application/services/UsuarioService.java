package com.snpsolutions.reclamala.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.Optional;

import com.snpsolutions.reclamala.domain.dtos.UsuarioDTO;
import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.domain.enums.UsuarioTipo;
import com.snpsolutions.reclamala.domain.repositories.UsuarioRepository;
import com.snpsolutions.reclamala.infra.handles.SenhaIncorretaException;
import com.snpsolutions.reclamala.infra.handles.UsuarioJaCadastradoException;
import com.snpsolutions.reclamala.infra.handles.UsuarioNaoEncontradoException;
import com.snpsolutions.reclamala.infra.handles.UsuarioTipoDiferenteException;

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
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(criptografarSenha(usuarioDTO.getPassword()));

        if (usuarioDTO.getTipoUsuario() != UsuarioTipo.ALUNO) {
            throw new UsuarioTipoDiferenteException("Tipo do Usuario " + usuarioDTO.getTipoUsuario() 
                                                    + " não é aceitável. Tipo aceitável é: " + UsuarioTipo.ALUNO);
        }
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());

        return usuarioRepository.save(usuario);
    }

    public String criptografarSenha(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(senha);
    }

    public Usuario loginUsuario(Integer matricula, String senha) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByMatricula(matricula);
    
       
        if (!usuarioEncontrado.isPresent()) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado com a matrícula: " + matricula);
        }
    
        Usuario usuario = usuarioEncontrado.get();
    
       
        if (!senha.equals(usuario.getPassword())) { 
            throw new SenhaIncorretaException("Senha incorreta para o usuário: " + matricula);
        }
    
        return usuario;
    }
    
}
