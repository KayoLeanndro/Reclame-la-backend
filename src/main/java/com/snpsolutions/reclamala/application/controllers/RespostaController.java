package com.snpsolutions.reclamala.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.snpsolutions.reclamala.application.services.RespostaService;
import com.snpsolutions.reclamala.domain.dtos.RespostaDTO;
import com.snpsolutions.reclamala.domain.entities.Resposta;
import com.snpsolutions.reclamala.domain.entities.Usuario;

@RestController
@RequestMapping("/api/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @GetMapping
    public ResponseEntity<List<RespostaDTO>> listarRespostas() {
        List<RespostaDTO> respostas = respostaService.listarComentarios();
        return ResponseEntity.ok(respostas);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<RespostaDTO>> listarRespostasPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        List<RespostaDTO> respostas = respostaService.listarRespostasPorUsuario(usuario);
        return ResponseEntity.ok(respostas);
    }

    @PostMapping("/criarResposta/{idComentario}")
    public ResponseEntity<Resposta> criarResposta(@PathVariable Long idComentario, @RequestBody RespostaDTO respostaDTO) {
        Resposta novaResposta = respostaService.criarRespostaParaUmComentario(idComentario, respostaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaResposta);
    }

    @PutMapping("/{respostaId}")
    public ResponseEntity<Resposta> atualizarResposta(
            @PathVariable Long respostaId,
            @RequestBody Resposta respostaAtualizada) {
        Resposta resposta = respostaService.atualizarRespostaDeUmComentario(respostaId, respostaAtualizada);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{respostaId}")
    public ResponseEntity<Void> excluirResposta(@PathVariable Long respostaId) {
        respostaService.excluirResposta(respostaId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{respostaId}/curtir")
    public ResponseEntity<Void> curtirResposta(@PathVariable Long respostaId) {
        respostaService.curtirResposta(respostaId);
        return ResponseEntity.ok().build();
    }
}
