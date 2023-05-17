package io.github.msimeaor.domain.entily;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USUARIO")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, name = "LOGIN", length = 50)
  @NotEmpty(message = "{campo.usuario.login-obrigatorio}")
  private String login;

  @Column(nullable = false, name = "SENHA", length = 100)
  @NotEmpty(message = "{campo.usuario.senha-obrigatorio}")
  private String senha;

  @NotNull(message = "{campo.usuario.admin-obrigatorio}")
  private boolean admin;

}
