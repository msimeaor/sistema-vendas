package io.github.msimeaor.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoItemPedidoDto {

  private String descricaoProduto;
  private BigDecimal precoUnitario;
  private Integer quantidade;

}
