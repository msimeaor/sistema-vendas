package io.github.msimeaor.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDto {

  private String descricao;
  private BigDecimal preco;

}
