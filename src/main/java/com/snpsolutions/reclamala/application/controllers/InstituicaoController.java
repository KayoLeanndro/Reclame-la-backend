package com.snpsolutions.reclamala.application.controllers;

import com.snpsolutions.reclamala.domain.dtos.LoginRequestInstituicaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snpsolutions.reclamala.application.services.InstituicaoService;
import com.snpsolutions.reclamala.domain.dtos.InstituicaoDTO;
import com.snpsolutions.reclamala.domain.entities.Instituicao;
import com.snpsolutions.reclamala.infra.config.ApiResponse;
import com.snpsolutions.reclamala.infra.handles.SenhaIncorretaException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/v1/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    @PostMapping("/cadastrarInstituicao")
    @Operation(summary = "Cadastro de instituição", description = "Realiza o cadastro de uma instituição")
    public ResponseEntity<ApiResponse> cadastrarInstituicao(@RequestBody InstituicaoDTO instituicaoDTO) {
        try {
            Instituicao instituicao = instituicaoService.cadastrarInstituicao(instituicaoDTO);
            ApiResponse response = new ApiResponse("Instituição cadastrada com sucesso.", true);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse("Erro ao cadastrar instituição: " + e.getMessage(), false);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            System.out.println(e);
            ApiResponse response = new ApiResponse("Erro interno do servidor.", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/loginInstituicao")
    @Operation(summary = "Login de instituição", description = "Realiza login de uma instituição")
    public ResponseEntity<ApiResponse> loginInstituicao(@RequestBody LoginRequestInstituicaoDTO loginRequest) {
        try {
            Instituicao instituicao = instituicaoService.loginInstituicao(loginRequest.getCnpj(), loginRequest.getSenha());
            ApiResponse response = new ApiResponse("Login bem-sucedido.", true, loginRequest);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse("Instituição não encontrada: " + e.getMessage(), false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (SenhaIncorretaException e) {
            ApiResponse response = new ApiResponse("Senha incorreta.", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("Erro interno do servidor.", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



}
