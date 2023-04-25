package io.github.msimeaor.domain.service;

import io.github.msimeaor.domain.dtos.PedidoDto;
import io.github.msimeaor.domain.entily.Pedido;

import java.util.Optional;
import java.util.UUID;

public interface PedidoService {

  Pedido save(PedidoDto pedidoDto);
  Optional<Pedido> getPedidoByIdFetchItensPedidos(UUID id);

}
