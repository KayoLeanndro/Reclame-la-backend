package com.snpsolutions.reclamala.services;

import org.springframework.stereotype.Service;

import com.snpsolutions.reclamala.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    

}
