package io.github.msimeaor.domain.repository;

import io.github.msimeaor.domain.entily.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface Pedidos extends JpaRepository<Pedido, UUID> {

  @Query("select p from Pedido p left join p.itensPedidos where p.id= :id")
  public Optional<Pedido> getPedidoByIdFetchItensPedidos(@Param("id") UUID id);

}
