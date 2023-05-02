package io.github.msimeaor.domain.dtos;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDto {

  @NotEmpty(message = "{campo.descricao.obrigatorio}")
  private String descricao;

  @NotNull(message = "{campo.preco.obrigatorio}")
  @Digits(integer = 20, fraction = 2)
  private BigDecimal preco;

}
