package io.github.msimeaor.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoPedidoDto {

  private UUID idPedido;
  private String cpfCliente;
  private String nomeCliente;
  private BigDecimal totalPedido;
  private String dataPedido;
  private List<InfoItemPedidoDto> itensPedidos;

}
