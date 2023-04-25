package io.github.msimeaor.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ItemPedidoDto {

  private UUID produto;
  private Integer quantidade;

}
