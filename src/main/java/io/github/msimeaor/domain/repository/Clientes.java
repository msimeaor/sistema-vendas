package io.github.msimeaor.domain.repository;

import io.github.msimeaor.domain.entily.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface Clientes extends JpaRepository<Cliente, UUID> {

    List<Cliente> findByNomeContainsIgnoreCase(String nome);
    boolean existsById(UUID id);

    @Query("select c from Cliente c left join fetch c.listaPedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") UUID id);

    boolean existsByCpf(String cpf);

}
