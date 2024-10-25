package com.snpsolutions.reclamala.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private Integer matricula;
    private String senha;
}