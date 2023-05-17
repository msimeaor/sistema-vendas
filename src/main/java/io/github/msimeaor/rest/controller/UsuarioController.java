package io.github.msimeaor.rest.controller;

import io.github.msimeaor.domain.dtos.UsuarioDto;
import io.github.msimeaor.domain.entily.Usuario;
import io.github.msimeaor.domain.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioServiceImpl usuarioService;
  private final PasswordEncoder passwordEncoder;

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody @Valid UsuarioDto usuarioDto) {
    if (usuarioService.existsByLogin(usuarioDto.getLogin())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("USUÁRIO JA CADASTRADO!");
    }

    Usuario usuario = new Usuario();
    BeanUtils.copyProperties(usuarioDto, usuario);
    String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
    usuario.setSenha(senhaCriptografada);

    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));

  }

}
