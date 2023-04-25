package io.github.msimeaor.domain.service;

import io.github.msimeaor.domain.entily.Produto;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoService {

  boolean existsByDescricao(String descricao);
  Produto save(Produto produto);
  Optional<Produto> findById(UUID id);
  List<Produto> findAll();
  void deleteById(UUID id);
  List<Produto> findAll(Example example);

}
