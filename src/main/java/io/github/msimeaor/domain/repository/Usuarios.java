package io.github.msimeaor.domain.repository;

import io.github.msimeaor.domain.entily.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface Usuarios extends JpaRepository<Usuario, UUID> {

  @Query("select u from Usuario u where u.login = :login")
  Optional<Usuario> findUsuarioByLogin(@Param("login") String login);

  boolean existsByLogin(String login);

}
