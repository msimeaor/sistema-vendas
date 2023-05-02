package io.github.msimeaor.domain.repository;

import io.github.msimeaor.domain.entily.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Usuarios extends JpaRepository<Usuario, UUID> {



}
