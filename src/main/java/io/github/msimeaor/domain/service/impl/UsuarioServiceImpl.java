package io.github.msimeaor.domain.service.impl;

import io.github.msimeaor.domain.entily.Usuario;
import io.github.msimeaor.domain.repository.Usuarios;
import io.github.msimeaor.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

  private final PasswordEncoder passwordEncoder;
  private final Usuarios usuarios;


  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    Usuario usuario = usuarios.findUsuarioByLogin(login)
      .orElseThrow(() -> new UsernameNotFoundException("USUARIO NÃO ENCONTRADO!"));

    String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String []{"USER"};

    return User.builder()
      .username(usuario.getLogin())
      .password(passwordEncoder.encode(usuario.getSenha()))
      .roles(roles)
      .build();
  }

}
