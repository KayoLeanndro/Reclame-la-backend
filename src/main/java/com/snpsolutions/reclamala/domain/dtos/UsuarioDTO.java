package com.snpsolutions.reclamala.domain.dtos;



import com.snpsolutions.reclamala.domain.enums.UsuarioTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {

    @NotNull(message = "Matrícula é obrigatória.")
    private Integer matricula;

    @NotBlank(message = "CPF é obrigatório.")
    private String usuarioCpf;

    @NotBlank(message = "Nome de usuário é obrigatório.")
    @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre 3 e 20 caracteres.")
    private String username;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;

    @NotBlank(message = "Tipo de usuário é obrigatório.")
    private UsuarioTipo tipoUsuario;
}
