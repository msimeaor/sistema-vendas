package io.github.msimeaor.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

  @NotBlank
  @Size(max = 50)
  private String nome;

  @NotBlank
  @Size(max = 11)
  private String cpf;

}
