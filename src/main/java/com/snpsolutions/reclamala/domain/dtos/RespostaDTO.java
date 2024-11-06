package com.snpsolutions.reclamala.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaDTO {

    @NotNull
    private Long idComentario;

    @NotBlank
    private String conteudoResposta;

    @NotNull
    private Integer matriculaUsuario;

}
