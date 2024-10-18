package com.snpsolutions.reclamala.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firebaseDatabase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("firebase-service-account.json"); // Altere o caminho para o arquivo JSON

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://reclama-la.firebaseio.com/") // Altere conforme sua URL do Firebase
                .build();

        // Inicializa o FirebaseApp se ainda não estiver inicializado
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        return FirestoreOptions.getDefaultInstance().getService();
    }
}
