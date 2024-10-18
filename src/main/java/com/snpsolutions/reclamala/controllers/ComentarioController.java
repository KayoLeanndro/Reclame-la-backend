package com.snpsolutions.reclamala.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("api/v1/comentario")
public class ComentarioController {
    
    private final ComentarioService comentarioService;

   @Autowired
   public ComentarioController(ComentarioService comentarioService){
        this.comentarioService = comentarioService;
   }

   @PostMapping("/criarComentario")
   public CompletableFuture<ResponseEntity<Comentario>> adicionarComentario(@RequestBody Comentario comentario, String usuarioReferencia){
        return comentarioService.addComentario(comentario, usuarioReferencia)
               .thenApply(result -> ResponseEntity.status(HttpStatus.CREATED).body(comentario))
               .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
   }

    

}
