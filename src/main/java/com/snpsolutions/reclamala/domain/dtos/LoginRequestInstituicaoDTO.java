package com.snpsolutions.reclamala.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestInstituicaoDTO {
    private String cnpj;
    private String senha;
}