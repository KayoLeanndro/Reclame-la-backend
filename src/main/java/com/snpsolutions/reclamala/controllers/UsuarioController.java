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
    public CompletableFuture<ResponseEntity<Usuario>> adicionarUsuario(@RequestBody Usuario usuario){
        return usuarioService.addUsuario(usuario)
                             .thenApply(result -> ResponseEntity.status(HttpStatus.SC_CREATED).body(usuario))
                             .exceptionally(ex -> ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build());
    }

}
