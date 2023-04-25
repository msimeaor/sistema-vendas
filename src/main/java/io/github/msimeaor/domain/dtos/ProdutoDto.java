package io.github.msimeaor.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDto {

  @NotBlank
  @Size(max = 100)
  private String descricao;

  @NotNull
  private BigDecimal preco;

}
