package io.github.msimeaor.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoDto {

  @NotNull(message = "O campo cliente é obrigatório!")
  private UUID cliente;

  private List<ItemPedidoDto> itensPedidos;

}
