package com.snpsolutions.reclamala.services;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.snpsolutions.reclamala.entities.Usuario;


@Service
public class UsuarioService {
    
  private final Firestore firestore;

  @Autowired
  public UsuarioService(Firestore firestore){
    this.firestore = firestore;
  }

  private <T> CompletableFuture<T> convertToCompletableFuture(ApiFuture<T> apiFuture) {
    CompletableFuture<T> completableFuture = new CompletableFuture<>();
    apiFuture.addListener(() -> {
        try {
            completableFuture.complete(apiFuture.get());
        } catch (Exception e) {
            completableFuture.completeExceptionally(e);
        }
    }, Runnable::run);
    return completableFuture;
}

public CompletableFuture<Usuario> addUsuario(Usuario usuario) {
    DocumentReference docRef = firestore.collection("usuarios").document();
    usuario.setId(docRef.getId());
    
    ApiFuture<WriteResult> futureWriteResult = docRef.set(usuario);

    CompletableFuture<WriteResult> completableFuture = convertToCompletableFuture(futureWriteResult);
    
    return completableFuture.thenApply(writeResult -> usuario)
        .exceptionally(exception -> {
            exception.printStackTrace();
            return null;  
        });
}



  

}
