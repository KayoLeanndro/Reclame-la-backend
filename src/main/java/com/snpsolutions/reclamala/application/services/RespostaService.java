package com.snpsolutions.reclamala.application.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snpsolutions.reclamala.domain.dtos.RespostaDTO;
import com.snpsolutions.reclamala.domain.entities.Comentario;
import com.snpsolutions.reclamala.domain.entities.Resposta;
import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.domain.repositories.ComentarioRepository;
import com.snpsolutions.reclamala.domain.repositories.RespostaRepository;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<RespostaDTO> listarComentarios() {
        List<Resposta> comentarios = respostaRepository.findAll();
        return comentarios.stream()
                          .map(this::toRespostaDTO)
                          .collect(Collectors.toList());
    }

    public List<RespostaDTO> listarRespostasPorUsuario(Usuario usuario) {
        List<Resposta> respostas = respostaRepository.findByUsuario(usuario); 
        return respostas.stream()
                        .map(this::toRespostaDTO)
                        .collect(Collectors.toList());
    }

    private RespostaDTO toRespostaDTO(Resposta resposta) {
        RespostaDTO dto = new RespostaDTO(); 
        dto.setConteudoResposta(resposta.getConteudoResposta());
        dto.setMatriculaUsuario(resposta.getUsuario().getMatricula()); 
        return dto;
    }

    @Transactional
public Resposta criarRespostaParaUmComentario(Long comentarioId, RespostaDTO respostaDTO) {

    Usuario usuario = usuarioService.buscarUsuarioPorMatricula(respostaDTO.getMatriculaUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado."));

    Comentario comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new RuntimeException("Comentario não encontrado."));

    Resposta novaResposta = new Resposta();
    novaResposta.setConteudoResposta(respostaDTO.getConteudoResposta());
    novaResposta.setUsuario(usuario);
    novaResposta.setComentario(comentario); 
    novaResposta.setDataCriacaoResposta(LocalDateTime.now());

    return respostaRepository.save(novaResposta);
}

    public Resposta atualizarRespostaDeUmComentario(Long respostaId, Resposta respostaAtualizada) {
        return respostaRepository.findById(respostaId).map(resposta -> {
            resposta.setConteudoResposta(respostaAtualizada.getConteudoResposta());
            resposta.setQtdCurtidas(respostaAtualizada.getQtdCurtidas());
            resposta.setDataCriacaoResposta(respostaAtualizada.getDataCriacaoResposta());
            return respostaRepository.save(resposta);
        }).orElseThrow(() -> new RuntimeException("Resposta não encontrada"));
    }

    @Transactional
    public void excluirResposta(Long respostaId) {
        respostaRepository.deleteById(respostaId);
    }

    @Transactional
    public void curtirResposta(Long respostaId) {
        respostaRepository.findById(respostaId).ifPresent(resposta -> {
            resposta.setQtdCurtidas(resposta.getQtdCurtidas() + 1);
            respostaRepository.save(resposta);
        });
    }
}
