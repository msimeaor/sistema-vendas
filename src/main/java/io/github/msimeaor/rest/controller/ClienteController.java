package io.github.msimeaor.rest.controller;

import io.github.msimeaor.domain.dtos.ClienteDto;
import io.github.msimeaor.domain.entily.Cliente;
import io.github.msimeaor.domain.service.impl.ClienteServiceImpl;
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
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteServiceImpl clienteServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity getClienteById(@PathVariable UUID id) {
        Optional<Cliente> clienteOptional = clienteServiceImpl.findById(id);
        if(verificarSeClienteExiste(clienteOptional)) {
            return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ClienteDto clienteDto) {
        if (clienteServiceImpl.existsByCpf(clienteDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já existente!");
        }

        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteServiceImpl.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        Optional<Cliente> clienteOptional = clienteServiceImpl.findById(id);
        if(verificarSeClienteExiste(clienteOptional)) {
            clienteServiceImpl.deleteById(clienteOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body("CLiente deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody @Valid ClienteDto clienteDto) {
        Optional<Cliente> clienteOptional = clienteServiceImpl.findById(id);
        if (clienteOptional.isPresent()) {
            var cliente = new Cliente();
            BeanUtils.copyProperties(clienteDto, cliente);
            cliente.setId(clienteOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(clienteServiceImpl.save(cliente));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
    }

    @GetMapping
    public ResponseEntity find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Cliente> listaClientes = clienteServiceImpl.findAll(example);
        return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
    }

    private boolean verificarSeClienteExiste(Optional<Cliente> cliente) {
        return (cliente.isPresent());
    }

}
