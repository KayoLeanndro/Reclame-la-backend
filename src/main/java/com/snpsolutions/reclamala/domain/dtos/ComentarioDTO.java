package com.snpsolutions.reclamala.domain.dtos;

import com.snpsolutions.reclamala.domain.enums.ComentarioTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {
   
    @NotBlank
    private String tituloComentario;

    @NotBlank
    private String conteudoComentario;

    @NotNull
    private ComentarioTipo categoriaComentario;

    @NotNull
    private Integer matriculaUsuario;

}
