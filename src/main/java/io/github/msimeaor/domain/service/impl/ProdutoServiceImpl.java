package io.github.msimeaor.domain.service.impl;

import io.github.msimeaor.domain.entily.Produto;
import io.github.msimeaor.domain.repository.Produtos;
import io.github.msimeaor.domain.service.ProdutoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

  private final Produtos produtos;


  public boolean existsByDescricao(String descricao) {
    return produtos.existsByDescricao(descricao);
  }

  @Transactional
  public Produto save(Produto produto) {
    return produtos.save(produto);
  }

  public Optional<Produto> findById(UUID id) {
    return produtos.findById(id);
  }

  public List<Produto> findAll() {
    return produtos.findAll();
  }

  public List<Produto> findAll(Example example) {
    return produtos.findAll(example);
  }

  @Transactional
  public void deleteById(UUID id) {
    produtos.deleteById(id);
  }

}
