package io.github.msimeaor.rest.controller;

import antlr.Token;
import io.github.msimeaor.domain.dtos.CredenciaisDto;
import io.github.msimeaor.domain.dtos.TokenDto;
import io.github.msimeaor.domain.dtos.UsuarioDto;
import io.github.msimeaor.domain.entily.Usuario;
import io.github.msimeaor.domain.service.impl.UsuarioServiceImpl;
import io.github.msimeaor.exceptions.SenhaInvalidaException;
import io.github.msimeaor.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioServiceImpl usuarioService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

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

  @PostMapping("/auth")
  public TokenDto autenticar(@RequestBody CredenciaisDto credenciaisDto) {
    try {

      Usuario usuario = Usuario.builder()
        .login(credenciaisDto.getLogin())
        .senha(credenciaisDto.getSenha())
        .build();

      UserDetails userAutenticado = usuarioService.autenticar(usuario);
      String tokenUsuario = jwtService.gerarToken(usuario);
      return new TokenDto(usuario.getLogin(), tokenUsuario);

    } catch (SenhaInvalidaException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

  }

}
