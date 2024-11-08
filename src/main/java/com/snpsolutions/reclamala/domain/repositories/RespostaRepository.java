package com.snpsolutions.reclamala.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snpsolutions.reclamala.domain.entities.Resposta;
import com.snpsolutions.reclamala.domain.entities.Usuario;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
     List<Resposta> findByUsuario(Usuario usuario);   
}
