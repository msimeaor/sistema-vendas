package io.github.msimeaor.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ItemPedidoDto {

  @NotNull(message = "O campo produto é obrigatório!")
  private UUID produto;

  @NotNull(message = "O campo quantidade é obrigatório!")
  private Integer quantidade;

}
