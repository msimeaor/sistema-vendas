package io.github.msimeaor.rest.controller;

import io.github.msimeaor.domain.dtos.InfoItemPedidoDto;
import io.github.msimeaor.domain.dtos.InfoPedidoDto;
import io.github.msimeaor.domain.dtos.PedidoDto;
import io.github.msimeaor.domain.entily.ItemPedido;
import io.github.msimeaor.domain.entily.Pedido;
import io.github.msimeaor.domain.service.impl.PedidoServiceImpl;
import io.github.msimeaor.exceptions.ExceptionPersonalizada;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedido")
public class PedidoController {

  private final PedidoServiceImpl pedidoServiceImpl;

  @PostMapping
  public UUID save(@RequestBody @Valid PedidoDto pedidoDto) {
    Pedido pedido = pedidoServiceImpl.save(pedidoDto);
    return pedido.getId();
  }

  @GetMapping("/{id}")
  public InfoPedidoDto getById(@PathVariable UUID id) {
    return pedidoServiceImpl
        .getPedidoByIdFetchItensPedidos(id)
        .map(pedido -> converterPedidoEmInfoPedidoDto(pedido))
        .orElseThrow(() -> new ExceptionPersonalizada("Pedido não encontrado"));
  }

  private InfoPedidoDto converterPedidoEmInfoPedidoDto(Pedido pedido) {
    return InfoPedidoDto.builder()
        .idPedido(pedido.getId())
        .cpfCliente(pedido.getCliente().getCpf())
        .nomeCliente(pedido.getCliente().getNome())
        .totalPedido(pedido.getTotal())
        .dataPedido(pedido.getData_pedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        .itensPedidos(converterItemPedidoEmInfoItemPedidoDto(pedido.getItensPedidos()))
        .build();
  }

  private List<InfoItemPedidoDto> converterItemPedidoEmInfoItemPedidoDto(List<ItemPedido> itensPedido) {
    if (itensPedido.isEmpty()) {
      return Collections.emptyList();
    }

    return itensPedido.stream().map(
        item -> InfoItemPedidoDto.builder()
            .descricaoProduto(item.getProduto().getDescricao())
            .quantidade(item.getQuantidade())
            .precoUnitario(item.getProduto().getPreco())
            .build()
    ).collect(Collectors.toList());
  }

}
