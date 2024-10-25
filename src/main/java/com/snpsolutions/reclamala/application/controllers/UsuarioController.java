package com.snpsolutions.reclamala.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snpsolutions.reclamala.application.services.UsuarioService;
import com.snpsolutions.reclamala.domain.dtos.UsuarioDTO;
import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.infra.config.ApiResponse;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrarUsuarioAluno")
    public ResponseEntity<ApiResponse> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioService.cadastrarUsuarioAluno(usuarioDTO);
            ApiResponse response = new ApiResponse("Usuário cadastrado com sucesso.", true);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse("Erro ao cadastrar usuário: " + e.getMessage(), false);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("Erro interno do servidor.", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
