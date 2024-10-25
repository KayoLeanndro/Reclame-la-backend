package com.snpsolutions.reclamala.infra.handles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.snpsolutions.reclamala.infra.config.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    public ResponseEntity<ApiResponse> handleUsuarioJaCadastrado(UsuarioJaCadastradoException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UsuarioTipoDiferenteException.class)
    public ResponseEntity<ApiResponse> handleUsuarioTipoDiferente(Exception ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EmailNaoValidoException.class)
    public ResponseEntity<ApiResponse> handleEmailNaoValidoException(Exception ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } 

    @ExceptionHandler(EmailNaoValidoException.class)
    public ResponseEntity<ApiResponse> handleSenhaIncorretaException(Exception ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> UsuarioNaoEncontradoException(Exception ex) {
        ApiResponse response = new ApiResponse("Erro interno do servidor.", false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception ex) {
        ApiResponse response = new ApiResponse("Erro interno do servidor.", false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
