package com.snpsolutions.reclamala.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.snpsolutions.reclamala.entities.Comentario;
import com.snpsolutions.reclamala.services.ComentarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/comentario")
public class ComentarioController {
    
    private final ComentarioService comentarioService;

    @PostMapping("/criarComentario/{usuarioId}")
    public ResponseEntity<Comentario> criarComentario(@PathVariable Long usuarioId, @RequestBody Comentario comentario){
        Comentario nComentario = comentarioService.salvarComentario(usuarioId, comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nComentario);
    }

    

}
