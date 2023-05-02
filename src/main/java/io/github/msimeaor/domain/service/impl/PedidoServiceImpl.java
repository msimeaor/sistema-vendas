package io.github.msimeaor.domain.service.impl;

import io.github.msimeaor.domain.dtos.ItemPedidoDto;
import io.github.msimeaor.domain.dtos.PedidoDto;
import io.github.msimeaor.domain.entily.Cliente;
import io.github.msimeaor.domain.entily.ItemPedido;
import io.github.msimeaor.domain.entily.Pedido;
import io.github.msimeaor.domain.entily.Produto;
import io.github.msimeaor.domain.repository.Clientes;
import io.github.msimeaor.domain.repository.ItensPedidos;
import io.github.msimeaor.domain.repository.Pedidos;
import io.github.msimeaor.domain.repository.Produtos;
import io.github.msimeaor.domain.service.PedidoService;
import io.github.msimeaor.exceptions.ExceptionPersonalizada;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

  private final Pedidos pedidos;
  private final Clientes clientes;
  private final Produtos produtos;
  private final ItensPedidos itensPedidos;

  @Transactional
  public Pedido save(PedidoDto pedidoDto) {
    UUID clienteID = pedidoDto.getCliente();
    Cliente cliente = clientes.findById(clienteID)
        .orElseThrow(() -> new ExceptionPersonalizada("ID do cliente não encontrado!"));

    Pedido pedido = new Pedido();
    pedido.setData_pedido(LocalDate.now());
    pedido.setCliente(cliente);

    List<ItemPedido> listaItensPedidos = converterItens(pedido, pedidoDto.getItensPedidos());
    pedido.setTotal(calcularValorTotalCompra(listaItensPedidos));

    pedidos.save(pedido);
    itensPedidos.saveAll(listaItensPedidos);

    pedido.setItensPedidos(listaItensPedidos);
    return pedido;
  }

  private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDto> itens) {
    if (itens.isEmpty()) {
      throw new ExceptionPersonalizada("Não é possível salvar um pedido sem itens");
    }

    return itens.stream()
        .map(itemDto -> {

          UUID produtoID = itemDto.getProduto();
          Produto produto = produtos.findById(produtoID)
              .orElseThrow(() -> new ExceptionPersonalizada("ID do produto não encontrado!" + produtoID));

          ItemPedido itemPedido = new ItemPedido();
          itemPedido.setQuantidade(itemDto.getQuantidade());
          itemPedido.setPedido(pedido);
          itemPedido.setProduto(produto);
          return itemPedido;

        }).collect(Collectors.toList());
  }

  private BigDecimal calcularValorTotalCompra(List<ItemPedido> listaItensPedidos) {
    BigDecimal bufferValorTotal = BigDecimal.ZERO;

    for(ItemPedido item : listaItensPedidos) {
      Integer quantidadesPedidas = item.getQuantidade();
      BigDecimal valorUnitarioPorProduto = item.getProduto().getPreco();
      BigDecimal valorTotalItem = valorUnitarioPorProduto.multiply(new BigDecimal(quantidadesPedidas));

      bufferValorTotal = bufferValorTotal.add(valorTotalItem);
    }

    return bufferValorTotal;
  }

  public Optional<Pedido> getPedidoByIdFetchItensPedidos(UUID id) {
    return pedidos.getPedidoByIdFetchItensPedidos(id);
  }
}
