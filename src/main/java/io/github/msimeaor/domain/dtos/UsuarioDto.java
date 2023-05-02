package io.github.msimeaor.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioDto {

  @NotEmpty(message = "{campo.usuario.login-obrigatorio}")
  private String login;

  @NotEmpty(message = "{campo.usuario.senha-obrigatorio}")
  private String senha;

  @NotNull(message = "{campo.usuario.admin-obrigatorio}")
  private boolean admin;

}
