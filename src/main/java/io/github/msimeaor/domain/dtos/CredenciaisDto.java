package io.github.msimeaor.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredenciaisDto {

  private String login;
  private String senha;

}
