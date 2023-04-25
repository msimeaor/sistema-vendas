package io.github.msimeaor.domain.service;

import io.github.msimeaor.domain.entily.Cliente;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteService {

  boolean existsByCpf(String cpf);
  Optional<Cliente> findById(UUID id);
  Cliente save(Cliente cliente);
  void deleteById(UUID id);
  List<Cliente> findAll(Example example);

}
