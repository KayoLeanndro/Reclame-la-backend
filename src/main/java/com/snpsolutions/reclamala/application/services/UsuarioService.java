package com.snpsolutions.reclamala.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

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

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@souunit\\.com\\.br$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Transactional
    public Usuario cadastrarUsuarioAluno(UsuarioDTO usuarioDTO) {

        if (usuarioRepository.existsByMatricula(usuarioDTO.getMatricula())) {
            throw new UsuarioJaCadastradoException(
                    "Usuário com matrícula " + usuarioDTO.getMatricula() + " já cadastrado.");
        }

        if (!emailEhValido(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("Email inválido. O email deve ser do domínio @souunit.com.br.");
        }
        if (usuarioRepository.existsByUsuarioCpf(usuarioDTO.getUsuarioCpf())) {
            throw new UsuarioJaCadastradoException("Usuário com CPF " + usuarioDTO.getUsuarioCpf() + " já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setMatricula(usuarioDTO.getMatricula());
        usuario.setUsuarioCpf(usuarioDTO.getUsuarioCpf());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(criptografarSenha(usuarioDTO.getPassword()));

        if (usuarioDTO.getTipoUsuario() != UsuarioTipo.ALUNO && usuarioDTO.getTipoUsuario() != UsuarioTipo.ADMINISTRADOR) {
            throw new UsuarioTipoDiferenteException("Tipo do Usuario " + usuarioDTO.getTipoUsuario()
                    + " não é aceitável. Tipos aceitáveis são: ALUNO e ADMINISTRADOR.");
        }
        
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        usuario.setInstituicao(usuarioDTO.getInstituicao());
        usuario.setCursoTipo(usuarioDTO.getCursoTipo());
        usuario.setEmail(usuarioDTO.getEmail());

        return usuarioRepository.save(usuario);
    }

    public String criptografarSenha(String senha) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(senha);
    }

    public boolean verificarSenha(String senha, String hash) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(senha, hash);
    }

    public boolean emailEhValido(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public Usuario loginUsuario(Integer matricula, String senha) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByMatricula(matricula);

        if (!usuarioEncontrado.isPresent()) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado com a matrícula: " + matricula);
        }

        Usuario usuario = usuarioEncontrado.get();

        if (!this.verificarSenha(senha, usuario.getPassword())) {
            throw new SenhaIncorretaException("Senha incorreta para o usuário: " + matricula);
        }

        return usuario;
    }

    public Optional<Usuario> buscarUsuarioPorMatricula(Integer matricula) {
        return usuarioRepository.findByMatricula(matricula);
    }

}
