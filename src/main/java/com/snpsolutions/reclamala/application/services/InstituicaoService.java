package com.snpsolutions.reclamala.application.services;

import com.snpsolutions.reclamala.domain.repositories.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

import com.snpsolutions.reclamala.domain.dtos.InstituicaoDTO;
import com.snpsolutions.reclamala.domain.entities.Instituicao;
import com.snpsolutions.reclamala.domain.enums.InstituicaoTipo;
import com.snpsolutions.reclamala.domain.repositories.InstituicaoRepository;
import com.snpsolutions.reclamala.infra.handles.SenhaIncorretaException;
import com.snpsolutions.reclamala.infra.handles.UsuarioJaCadastradoException;
import com.snpsolutions.reclamala.infra.handles.UsuarioNaoEncontradoException;
import com.snpsolutions.reclamala.infra.handles.UsuarioTipoDiferenteException;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@souunit\\.com\\.br$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Transactional
    public Instituicao cadastrarInstituicao(InstituicaoDTO instituicaoDTO) {

        if (instituicaoRepository.existsByCnpj(instituicaoDTO.getCnpj())) {
            throw new UsuarioJaCadastradoException(
                    "Instituição com cnpj " + instituicaoDTO.getCnpj() + " já cadastrado.");
        }

        if (!emailEhValido(instituicaoDTO.getEmail())) {
            throw new IllegalArgumentException("Email inválido. O email deve ser do domínio @souunit.com.br.");
        }

        Instituicao instituicao = new Instituicao();
        instituicao.setCnpj(instituicaoDTO.getCnpj());
        instituicao.setNome(instituicaoDTO.getNome());
        instituicao.setTelefone(instituicaoDTO.getTelefone());
        instituicao.setPassword(criptografarSenha(instituicaoDTO.getPassword()));
        instituicao.setEmail(instituicaoDTO.getEmail());

        return instituicaoRepository.save(instituicao);
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

    public Instituicao loginInstituicao(String cnpj, String senha) {
        Optional<Instituicao> instituicaoEncontrado = instituicaoRepository.findByCnpj(cnpj);

        if (!instituicaoEncontrado.isPresent()) {
            throw new UsuarioNaoEncontradoException("Instituição não encontrada com o cnpj: " + cnpj);
        }

        Instituicao instituicao = instituicaoEncontrado.get();

        if (!this.verificarSenha(senha, instituicao.getPassword())) {
            throw new SenhaIncorretaException("Senha incorreta para a instituição: " + cnpj);
        }

        return instituicao;
    }

    public Optional<Instituicao> buscarInstituicaoPorCnpj(String cnpj) {
        return instituicaoRepository.findByCnpj(cnpj);
    }

}
