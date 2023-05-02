package io.github.msimeaor.domain.entily;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
  @NotEmpty(message = "LOGIN NÃO ENCONTRADO!")
  private String login;

  @Column(nullable = false, name = "SENHA", length = 50)
  private String senha;

  private boolean admin;

}
