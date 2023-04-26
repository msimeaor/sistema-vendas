package io.github.msimeaor.domain.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDto {

  @NotEmpty(message = "O campo descrição é obrigatório!")
  private String descricao;

  @NotNull(message = "O campo preço é obrigatório!")
  @Digits(integer = 20, fraction = 2)
  private BigDecimal preco;

}
