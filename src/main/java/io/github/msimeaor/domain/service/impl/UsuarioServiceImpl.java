package io.github.msimeaor.domain.service.impl;

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

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

  private final PasswordEncoder passwordEncoder;
  private final Usuarios usuarios;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    if (!username.equals("FULANO")) {
      throw new UsernameNotFoundException("USUARIO NAO ENCONTRADO!");
    }

    return User
      .builder()
      .username("FULANO")
      .password(passwordEncoder.encode("123"))
      .roles("USER", "ADMIN")
      .build();
  }

}
