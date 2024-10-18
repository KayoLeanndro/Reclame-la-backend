package com.snpsolutions.reclamala.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.snpsolutions.reclamala.entities.Comentario;

@Service
public class ComentarioService {

    private final Firestore firestore;

    @Autowired
    public ComentarioService(Firestore firestore) {
        this.firestore = firestore;
    }

    public CompletableFuture<Comentario> addComentario(Comentario comentario, String usuarioId) {
        CompletableFuture<Comentario> future = new CompletableFuture<>();

        DocumentReference usuarioRef = firestore.collection("usuarios").document(usuarioId);

        comentario.setUsuarioComentario(usuarioRef);

        ApiFuture<DocumentReference> addedDocRef = firestore.collection("comentarios").add(comentario);
        
        addedDocRef.addListener(() -> {
            try {
                DocumentReference docRef = addedDocRef.get(); 
                comentario.setId(docRef.getId()); 
                future.complete(comentario);
            } catch (InterruptedException | ExecutionException e) {
                future.completeExceptionally(e);
            }
        }, Runnable::run);

        return future;
    }


    public CompletableFuture<List<Comentario>> listarComentarios() {
        CompletableFuture<List<Comentario>> future = new CompletableFuture<>();

        CollectionReference comentariosCollection = firestore.collection("comentarios");

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = comentariosCollection.get();

        querySnapshotApiFuture.addListener(() -> {
            try {
                List<Comentario> comentarios = new ArrayList<>();
                for (var document : querySnapshotApiFuture.get().getDocuments()) {
                    Comentario comentario = document.toObject(Comentario.class);
                    comentario.setId(document.getId());
                    comentarios.add(comentario);
                }
                future.complete(comentarios); 
            } catch (InterruptedException | ExecutionException e) {
                future.completeExceptionally(e);
            }
        }, Runnable::run);

        return future;
    }
}
