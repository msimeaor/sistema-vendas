package io.github.msimeaor.domain.repository;

import io.github.msimeaor.domain.entily.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Produtos extends JpaRepository<Produto, UUID> {

  boolean existsByDescricao(String descricao);

}
