package io.github.msimeaor.domain.repository;

import io.github.msimeaor.domain.entily.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItensPedidos extends JpaRepository<ItemPedido, UUID> {

}
