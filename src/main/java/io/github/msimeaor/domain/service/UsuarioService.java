package io.github.msimeaor.domain.service;

import io.github.msimeaor.domain.entily.Usuario;

public interface UsuarioService {

  boolean existsByLogin(String login);
  Usuario save(Usuario usuario);

}
