package io.github.msimeaor.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoDto {

  private UUID cliente;
  private List<ItemPedidoDto> itensPedidos;

}
