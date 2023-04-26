package io.github.msimeaor.rest.controller;

import io.github.msimeaor.domain.dtos.ProdutoDto;
import io.github.msimeaor.domain.entily.Produto;
import io.github.msimeaor.domain.service.impl.ProdutoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/produto")
public class ProdutoController {

  private final ProdutoServiceImpl produtoServiceImpl;

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody @Valid ProdutoDto produtoDto) {
    if (produtoServiceImpl.existsByDescricao(produtoDto.getDescricao())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto já existe!");
    }
    Produto produto = new Produto();
    BeanUtils.copyProperties(produtoDto, produto);
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoServiceImpl.save(produto));
  }

  @GetMapping
  public ResponseEntity<Object> getAll() {
    List<Produto> listaProdutos = produtoServiceImpl.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(listaProdutos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable UUID id) {
    Optional<Produto> produtoOptional = buscarProdutoPorId(id);
    if (produtoOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não econtrado!");
  }

  @GetMapping("/buscar")
  public ResponseEntity find(Produto filtro) {
    ExampleMatcher exampleMatcher = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example example = Example.of(filtro, exampleMatcher);
    List<Produto> listaProdutos = produtoServiceImpl.findAll(example);
    return ResponseEntity.status(HttpStatus.OK).body(listaProdutos);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteById(@PathVariable UUID id) {
    Optional<Produto> produtoOptional = buscarProdutoPorId(id);
    if (produtoOptional.isPresent()) {
      produtoServiceImpl.deleteById(id);
      return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid ProdutoDto produtoDto) {
    Optional<Produto> produtoOptional = buscarProdutoPorId(id);
    if (produtoOptional.isPresent()) {
      Produto produto = new Produto();
      BeanUtils.copyProperties(produtoDto, produto);
      produto.setId(produtoOptional.get().getId());
      return ResponseEntity.status(HttpStatus.OK).body(produtoServiceImpl.save(produto));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
  }


  private Optional<Produto> buscarProdutoPorId(UUID id) {
    Optional<Produto> produtoOptional = produtoServiceImpl.findById(id);
    return produtoOptional;
  }

}
