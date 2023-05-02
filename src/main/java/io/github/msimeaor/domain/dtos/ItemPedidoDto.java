package io.github.msimeaor.domain.dtos;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ItemPedidoDto {

  @NotNull(message = "{campo.codigo-produto.obrigatorio}")
  private UUID produto;

  @NotNull(message = "{campo.quantidade-itens.obrigatorio}")
  private Integer quantidade;

}
