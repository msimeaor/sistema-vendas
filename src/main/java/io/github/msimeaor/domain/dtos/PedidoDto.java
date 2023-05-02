package io.github.msimeaor.domain.dtos;

import io.github.msimeaor.validation.NotEmptyList;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoDto {

  @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
  private UUID cliente;

  @NotEmptyList(message = "{campo.itens-pedido.obrigatorio}")
  private List<ItemPedidoDto> itensPedidos;

}
