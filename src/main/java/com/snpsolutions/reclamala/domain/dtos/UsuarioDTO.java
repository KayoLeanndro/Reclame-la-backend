package com.snpsolutions.reclamala.domain.dtos;

import com.snpsolutions.reclamala.domain.entities.Usuario;
import com.snpsolutions.reclamala.domain.enums.CursoTipo;
import com.snpsolutions.reclamala.domain.enums.Instituicao;
import com.snpsolutions.reclamala.domain.enums.UsuarioTipo;

import jakarta.validation.constraints.Email;
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

    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "E-mail deve ser válido.")
    private String email;

    @NotBlank(message = "Nome de usuário é obrigatório.")
    @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre 3 e 20 caracteres.")
    private String username;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;

    @NotNull(message = "Tipo de usuário é obrigatório.")
    private UsuarioTipo tipoUsuario;

    @NotNull(message = "Curso é obrigatório.")
    private CursoTipo cursoTipo;

    @NotNull(message = "Instituição é obrigatória.")
    private Instituicao instituicao;

    /**
     * Converte o DTO para a entidade Usuario.
     *
     * @return A entidade Usuario correspondente.
     */
    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setMatricula(this.matricula);
        usuario.setUsuarioCpf(this.usuarioCpf);
        usuario.setEmail(this.email);
        usuario.setUsername(this.username);
        usuario.setPassword(this.password); 
        usuario.setTipoUsuario(this.tipoUsuario);
        usuario.setCursoTipo(this.cursoTipo); 
        usuario.setInstituicao(this.instituicao); 
        return usuario;
    }
}
