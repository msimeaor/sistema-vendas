package io.github.msimeaor.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class ClienteDto {

  @NotEmpty(message = "O campo nome é obrigatório!")
  private String nome;

  @NotEmpty(message = "O campo CPF é obrigatório!")
  @CPF(message = "Insira um CPF válido!")
  private String cpf;

}
