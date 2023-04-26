package io.github.msimeaor.domain.dtos;

import io.github.msimeaor.validation.NotEmptyList;
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

  @NotEmptyList(message = "A lista de itens não pode ser nula")
  private List<ItemPedidoDto> itensPedidos;

}
