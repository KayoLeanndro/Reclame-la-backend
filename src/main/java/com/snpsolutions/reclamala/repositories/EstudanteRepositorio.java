package com.snpsolutions.reclamala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snpsolutions.reclamala.models.Estudante;

public interface EstudanteRepositorio extends JpaRepository<Estudante, Long>{
    
    public Estudante acharEstudantePorMatricula(Integer matricula);

    
}
