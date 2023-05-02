package io.github.msimeaor.domain.dtos;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class ClienteDto {

  @NotEmpty(message = "{campo.nome.obrigatorio}")
  private String nome;

  @NotEmpty(message = "{campo.cpf.obrigatorio}")
  @CPF(message = "{campo.cpf.invalido}")
  private String cpf;

}
