package io.github.msimeaor.domain.service.impl;

import io.github.msimeaor.domain.entily.Cliente;
import io.github.msimeaor.domain.repository.Clientes;
import io.github.msimeaor.domain.service.ClienteService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

  private final Clientes clientes;


  public Optional<Cliente> findById(UUID id) {
    return clientes.findById(id);
  }

  public boolean existsByCpf(String cpf) {
    return clientes.existsByCpf(cpf);
  }

  @Transactional
  public Cliente save(Cliente cliente) {
    return clientes.save(cliente);
  }

  @Transactional
  public void deleteById(UUID id) {
    clientes.deleteById(id);
  }

  public List<Cliente> findAll(Example example) {
    return clientes.findAll(example);
  }

}
