package com.snpsolutions.reclamala.application.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.snpsolutions.reclamala.domain.entities.Comentario;
import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.domain.enums.ComentarioTipo;

import io.swagger.v3.oas.annotations.Operation;

import com.snpsolutions.reclamala.application.services.ComentarioService;

@RestController
@RequestMapping("api/v1/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentarios() {
        List<Comentario> comentarios = comentarioService.listarComentarios();
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Cadastro de usuario", description = "Realiza o cadastro de um usuário do tipo aluno")
    public ResponseEntity<Comentario> buscarComentarioPorCategoria(@PathVariable ComentarioTipo tipoComentario) {
        Optional<Comentario> comentario = comentarioService.buscarComentarioPorCategoria(tipoComentario);
        return comentario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Comentario>> listarComentariosPorUsuario(@PathVariable Usuario usuarioId) {
        List<Comentario> comentarios = comentarioService.listarComentariosPorUsuario(usuarioId);
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping
    public ResponseEntity<Comentario> criarComentario(@RequestBody Comentario comentario) {
        Comentario novoComentario = comentarioService.salvarComentario(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoComentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizarComentario(@PathVariable Long id, @RequestBody Comentario comentarioAtualizado) {
        Comentario comentario = comentarioService.atualizarComentario(id, comentarioAtualizado);
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirComentario(@PathVariable Long id) {
        comentarioService.excluirComentario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/curtir")
    public ResponseEntity<Void> curtirComentario(@PathVariable Long id) {
        comentarioService.curtirComentario(id);
        return ResponseEntity.noContent().build();
    }
}
