package com.snpsolutions.reclamala.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.snpsolutions.reclamala.entities.Usuario;
import lombok.RequiredArgsConstructor;

@Service
public class UsuarioService {
    
  private final Firestore firestore;

  @Autowired
  public UsuarioService(Firestore firestore){
    this.firestore = firestore;
  }

  public CompletableFuture<Usuario> addUsuario(Usuario usuario) {
    // Cria uma referência à coleção 'usuarios' no Firestore
    DocumentReference docRef = firestore.collection("usuarios").document();
    
    // Adiciona o ID gerado ao objeto usuário
    usuario.setId(Long.valueOf(docRef.getId()));
    
    // Salva o usuário no Firestore
    return docRef.set(usuario)
        .thenApply(result -> {
            return usuario;  // Retorna o usuário após ser salvo
        })
        .exceptionally(ex -> {
            ex.printStackTrace();
            return null;  // Lida com erros de forma apropriada
        });
}

  

}
