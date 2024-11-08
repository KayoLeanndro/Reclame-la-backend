package com.snpsolutions.reclamala.application.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.snpsolutions.reclamala.domain.dtos.ComentarioDTO;
import com.snpsolutions.reclamala.domain.entities.Comentario;
import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.domain.enums.ComentarioTipo;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import com.snpsolutions.reclamala.application.services.ComentarioService;
import com.snpsolutions.reclamala.application.services.UsuarioService;

@RestController
@RequestMapping("api/v1/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listarComentarios")
    @Operation(summary = "Listar Comentarios", description = "Realiza o retorna uma lista de comentarios")
    public ResponseEntity<List<ComentarioDTO>> listarComentarios() {
        List<ComentarioDTO> comentarios = comentarioService.listarComentarios();
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/buscarComentarioPorcategoria")
    @Operation(summary = "Buscar Comentario por categoria", description = "Realiza uma busca de comentarios de uma determinada categorias")
    public ResponseEntity<List<Comentario>> buscarComentarioPorCategoria(@RequestParam ComentarioTipo tipoComentario) {
        List<Comentario> comentarios = comentarioService.listarComentarioPorCategoria(tipoComentario);
        return comentarios.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(comentarios);
    }

    @GetMapping("/usuario/{matricula}")
    @Operation(summary = "Listar comentario por usuario", description = "Realiza uma busca pelo comentario de um usuario")
    public ResponseEntity<List<ComentarioDTO>> listarComentariosPorUsuario(@PathVariable Integer matricula) {
        Usuario usuario = usuarioService.buscarUsuarioPorMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<ComentarioDTO> comentarios = comentarioService.listarComentariosPorUsuario(usuario);
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping("/criarComentario")
    @Operation(summary = "Criar Comentario", description = "Realiza a criacao de um comentario, Categoria aceite INFRAESTRUTURA,\r\n" + //
                "    ENSINO,\r\n" + //
                "    SUPORTE,\r\n" + //
                "    OUTRO")
    
    public ResponseEntity<Comentario> criarComentario(@Valid @RequestBody ComentarioDTO comentarioDTO) {
        Comentario novoComentario = comentarioService.criarComentario(comentarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoComentario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar comentario", description = "Realiza uma atualizacao de comentario")
    public ResponseEntity<Comentario> atualizarComentario(@PathVariable Long id,
            @RequestBody Comentario comentarioAtualizado) {
        Comentario comentario = comentarioService.atualizarComentario(id, comentarioAtualizado);
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir comentario", description = "Exclui o comentario")
    public ResponseEntity<Void> excluirComentario(@PathVariable Long id) {
        comentarioService.excluirComentario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/curtir")
    @Operation(summary = "Curtir comentario", description = "Curte o comentario")
    public ResponseEntity<Void> curtirComentario(@PathVariable Long id) {
        comentarioService.curtirComentario(id);
        return ResponseEntity.noContent().build();
    }
}
