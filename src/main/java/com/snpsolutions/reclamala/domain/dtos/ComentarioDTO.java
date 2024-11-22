package com.snpsolutions.reclamala.domain.dtos;

import com.snpsolutions.reclamala.domain.enums.ComentarioTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {

    @NotNull
    private Long id;
   
    @NotBlank
    private String tituloComentario;

    @NotBlank
    private String conteudoComentario;

    @NotNull
    private ComentarioTipo categoriaComentario;

    private int qtdCurtidas;

    @NotNull
    private Integer matriculaUsuario;

}
