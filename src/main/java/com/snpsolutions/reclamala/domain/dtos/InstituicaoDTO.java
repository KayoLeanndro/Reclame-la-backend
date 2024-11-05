package com.snpsolutions.reclamala.domain.dtos;

import com.snpsolutions.reclamala.domain.entities.Instituicao;
import com.snpsolutions.reclamala.domain.enums.CursoTipo;
import com.snpsolutions.reclamala.domain.enums.InstituicaoTipo;
import com.snpsolutions.reclamala.domain.enums.UsuarioTipo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstituicaoDTO {

    @NotNull(message = "CNPJ é obrigatório.")
    @Size(min = 14, max = 14, message = "CNPJ deve ter 14 caracteres")
    private String cnpj;

    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "E-mail deve ser válido.")
    private String email;

    @NotBlank(message = "Telefone é obrigatório.")
    @Size(min = 11, max = 11, message = "Telefone deve ter 11 caracteres")
    private String telefone;

    @NotBlank(message = "Telefone é obrigatório.")
    @Size(min = 2, message = "Nome deve ser maior que 2 caracteres.")
    private String nome;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;

}
