package com.snpsolutions.reclamala.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaComentario implements Serializable {
    private final String INFRAESTRUTURA = new String("infraestrutura");
    private final String ATENDIMENTO = new String("atendimento");
    private final String QUALIDADE_DIDATICA = new String("qualidade_didatica");
    private final String RECURSOS_TECNOLOGICOS = new String("recursos_tecnologicos");
}
