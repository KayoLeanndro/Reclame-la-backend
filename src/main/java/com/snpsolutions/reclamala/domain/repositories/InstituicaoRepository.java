package com.snpsolutions.reclamala.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snpsolutions.reclamala.domain.entities.Instituicao;

import java.util.Optional;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    boolean existsByCnpj(String cnpj);

    Optional<Instituicao> findByCnpj(String cnpj);
}
