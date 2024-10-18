package com.snpsolutions.reclamala.controllers;

import java.util.concurrent.CompletableFuture;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.snpsolutions.reclamala.entities.Usuario;
import com.snpsolutions.reclamala.services.UsuarioService;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService comentarioService){
        this.usuarioService = comentarioService;
    }

    @PostMapping("/criarUsuario")
    public CompletableFuture<ResponseEntity<Usuario>> addUsuario(@RequestBody Usuario usuario){
        if (usuario == null) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }

        return usuarioService.addUsuario(usuario)
                             .thenApply(result -> ResponseEntity.status(HttpStatus.SC_CREATED).body(result)) 
                             .exceptionally(ex -> {
                                 ex.printStackTrace();
                                 return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
                             });
    }
}


